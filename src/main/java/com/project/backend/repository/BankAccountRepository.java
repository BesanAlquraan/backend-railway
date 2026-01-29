package com.project.backend.repository;

import com.project.backend.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    boolean existsByAccountNumber(String accountNumber);

    // جلب كل الحسابات لمستخدم معين
    List<BankAccount> findByUserId(Long userId);
}
