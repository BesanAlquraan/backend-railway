package com.project.backend.controller;

import com.project.backend.dto.UserReportDTO;
import com.project.backend.service.ReportService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // ✅ API واحد لتوليد التقرير كامل
    @GetMapping("/user/{userId}")
    public UserReportDTO getUserReport(@PathVariable Long userId) {
        return reportService.generateReport(userId);
    }
}
