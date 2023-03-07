package com.project.employeews.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.employeews.model.User;

public interface UsersRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);

}
