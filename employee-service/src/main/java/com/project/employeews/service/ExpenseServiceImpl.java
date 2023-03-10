package com.project.employeews.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.employeews.model.Expense;
import com.project.employeews.model.User;
import com.project.employeews.repository.EmployeeRepository;
import com.project.employeews.repository.ExpenseRepository;
import com.project.employeews.repository.UsersRepository;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

	private ExpenseRepository expenseRepository;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		super();
		this.expenseRepository = expenseRepository;
	}

	@Override
	public Expense addExpense(Expense expense) {
		Expense savedExpense = null;
		if (expense != null) {
			User user = getCurrentUser();
			expense.setUser(user);
			savedExpense = expenseRepository.save(expense);
		}
		return savedExpense;
	}

	private User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	}

	@Override
	public Collection<Expense> getListOfExpenses(int limit) {
		return expenseRepository.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public List<Expense> getExpense(Long id) {
		return expenseRepository.findByUser(id);
//		.orElseThrow(() -> new ResourceNotFoundException("expense doen't exist with id: " + id));
	}

	@Override
	public Expense updateExpense(Expense exp) {
		return expenseRepository.save(exp);
	}

	@Override
	public Boolean deleteExpense(Long id) {
		expenseRepository.deleteById(id);
		return true;
	}

}