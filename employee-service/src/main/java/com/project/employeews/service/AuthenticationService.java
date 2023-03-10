package com.project.employeews.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.employeews.config.SecurityConfig;
import com.project.employeews.model.AuthenticationRequest;
import com.project.employeews.model.AuthenticationResponse;
import com.project.employeews.model.RegisterRequest;
import com.project.employeews.model.Role;
import com.project.employeews.model.Token;
import com.project.employeews.model.TokenType;
import com.project.employeews.model.User;
import com.project.employeews.repository.TokenRepository;
import com.project.employeews.repository.UsersRepository;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JWTUtility jWTUtility;

	public AuthenticationResponse register(RegisterRequest request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUserId(UUID.randomUUID().toString());
		user.setRole(Role.USER);

		User savedUser = usersRepository.save(user);
		String jwtToken = jWTUtility.generateJwtToken(savedUser.getEmail());
		saveUserToken(savedUser, jwtToken);
		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		// if user is authenticated this is executed
		User user = usersRepository.findByEmail(request.getEmail()).orElseThrow();
		revokeAllUserTokens(user);
		String token = jWTUtility.generateJwtToken(user.getEmail());
		saveUserToken(user, token);
		return new AuthenticationResponse(token);

	}

	private void saveUserToken(User savedUser, String token) {
		Token newToken = new Token();
		newToken.setExpired(false);
		newToken.setRevoked(false);
		newToken.setToken(token);
		newToken.setTokenType(TokenType.BEARER);
		newToken.setUser(savedUser);

		tokenRepository.save(newToken);
	}

	private void revokeAllUserTokens(User user) {
		List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}
}
