package com.example.api_service_medical.service;

import com.example.api_service_medical.dto.AppointmentDto;
import com.example.api_service_medical.exception.ReportServiceException;
import com.example.api_service_medical.producer.TopicAppointmentProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final TopicAppointmentProducer topicAppointmentProducer;
    private final RestTemplate restTemplate;

    @Value("${data.service.url}")
    private String dataServiceUrl;

    public void createAppointment(AppointmentDto appointmentDto) {
        topicAppointmentProducer.sendAppointment(appointmentDto);
    }

    public List<AppointmentDto> searchAppointments(String patientName, Integer doctorId, String status) {
        String url = UriComponentsBuilder.fromHttpUrl(dataServiceUrl + "/api/appointments")
                .queryParam("patientName", patientName)
                .queryParam("doctorId", doctorId)
                .queryParam("status", status)
                .toUriString();

        ResponseEntity<List<AppointmentDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ReportServiceException("Data service returned non-success status: " + response.getStatusCode());
        }

        List<AppointmentDto> result = response.getBody();
        log.debug("Received report data with {} items", result != null ? result.size() : 0);

        return result != null ? result : Collections.emptyList();
    }
}