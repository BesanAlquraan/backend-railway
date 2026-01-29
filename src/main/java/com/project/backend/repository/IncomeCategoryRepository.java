package com.project.backend.repository;
import com.project.backend.model.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long> {

    List<IncomeCategory> findByUserId(Long userId);
}
