package com.example.api_service_medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final RestTemplate restTemplate;

    @Value("${data.service.url}")
    private String dataServiceUrl;

    public List<Map<String, Object>> getTopDoctorsReport() {
        return getReport("/api/reports/top-doctors");
    }

    public List<Map<String, Object>> getAppointmentsByDayReport() {
        return getReport("/api/reports/appointments-by-day");
    }

    public List<Map<String, Object>> getFrequentPatientsReport() {
        return getReport("/api/reports/frequent-patients");
    }

    private List<Map<String, Object>> getReport(String endpoint) {
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                dataServiceUrl + endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}
