package com.example.api_service_medical.controller;

import com.example.api_service_medical.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/top-doctors")
    public List<Map<String, Object>> getTopDoctorsReport() {
        return reportService.getTopDoctorsReport();
    }

    @GetMapping("/appointments-by-day")
    public List<Map<String, Object>> getAppointmentsByDayReport() {
        return reportService.getAppointmentsByDayReport();
    }

    @GetMapping("/frequent-patients")
    public List<Map<String, Object>> getFrequentPatientsReport() {
        return reportService.getFrequentPatientsReport();
    }
}
