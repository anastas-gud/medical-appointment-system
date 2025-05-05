package com.example.api_service_medical.controller;

import com.example.api_service_medical.exception.ReportServiceException;
import com.example.api_service_medical.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Slf4j
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/top-doctors")
    public ResponseEntity<?> getTopDoctorsReport() {
        try {
            return ResponseEntity.ok(reportService.getTopDoctorsReport());
        } catch (ReportServiceException e) {
            log.error("Failed to get top doctors report", e);
            throw e;
        }
    }

    @GetMapping("/appointments-by-day")
    public ResponseEntity<?> getAppointmentsByDayReport() {
        try {
            return ResponseEntity.ok(reportService.getAppointmentsByDayReport());
        } catch (ReportServiceException e) {
            log.error("Failed to get appointments by day report", e);
            throw e;
        }
    }

    @GetMapping("/frequent-patients")
    public ResponseEntity<?> getFrequentPatientsReport() {
        try {
            return ResponseEntity.ok(reportService.getFrequentPatientsReport());
        } catch (ReportServiceException e) {
            log.error("Failed to get frequent patients report", e);
            throw e;
        }
    }
}
