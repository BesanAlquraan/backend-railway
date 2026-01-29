package com.project.backend.service;

import com.project.backend.model.BankAccount;
import com.project.backend.model.User;
import com.project.backend.repository.BankAccountRepository;
import com.project.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class BankIntegrationService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankIntegrationService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public boolean checkAccountExists(String accountNumber) {
        return accountNumber.startsWith("JO") || accountNumber.startsWith("US");
    }

    public void linkAccount(Long userId, String bankName, String accountNumber) {
        if (bankAccountRepository.existsByAccountNumber(accountNumber)) {
            throw new RuntimeException("Account already linked");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount account = new BankAccount();
        account.setUser(user);
        account.setBankName(bankName);
        account.setAccountNumber(accountNumber);

        Random random = new Random();
        account.setCardNumber(String.valueOf(1000000000000000L + (long)(random.nextDouble() * 8999999999999999L)));
        account.setCardExpiry(LocalDate.of(2028, 12, 31));
        account.setCardCvv(String.format("%03d", random.nextInt(1000)));
        account.setBalance(BigDecimal.ZERO);

        bankAccountRepository.save(account);
    }

    // دالة لجلب حسابات المستخدم
    public List<BankAccount> getUserAccounts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getAccounts();
    }
}
