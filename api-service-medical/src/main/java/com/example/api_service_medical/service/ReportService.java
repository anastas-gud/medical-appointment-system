package com.example.api_service_medical.service;

import com.example.api_service_medical.exception.ReportServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
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
        String url = dataServiceUrl + endpoint;
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ReportServiceException("Data service returned non-success status: " + response.getStatusCode());
        }

        List<Map<String, Object>> result = response.getBody();
        log.debug("Received report data with {} items", result != null ? result.size() : 0);

        return result != null ? result : Collections.emptyList();
    }
}
