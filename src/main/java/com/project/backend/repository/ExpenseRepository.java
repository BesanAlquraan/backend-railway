package com.project.backend.repository;

import com.project.backend.model.Expense;
import com.project.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // جلب كل المصاريف لمستخدم محدد
    List<Expense> findByUser(User user);

    // أو حسب Id المستخدم
    List<Expense> findByUserId(Long userId);
}
