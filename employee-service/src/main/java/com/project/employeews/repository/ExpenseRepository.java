package com.project.employeews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.employeews.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query(value = """
			select e from Expense e inner join User u\s
			on e.user.id = u.id\s
			where u.id = :id\s
			""")
//	List<Expense> findByUser(Long id);
	
	
//	@Query("from Expense where user_id=:id")
	List<Expense> findByUser(Long id);
}
