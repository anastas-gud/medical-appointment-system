package com.example.api_service_medical.service;

import com.example.api_service_medical.dto.AppointmentDto;
import com.example.api_service_medical.kafka.KafkaProducer;
import com.example.api_service_medical.model.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final KafkaProducer kafkaProducer;
    private final RestTemplate restTemplate;

    @Value("${data.service.url}")
    private String dataServiceUrl;

    public void createAppointment(AppointmentDto appointmentDto) {
        kafkaProducer.sendAppointment(appointmentDto);
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

        return response.getBody();
    }
}