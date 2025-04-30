package com.example.data_service_medical.service;
import com.example.data_service_medical.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final AppointmentRepository appointmentRepository;

    public List<Map<String, Object>> getTopDoctorsReport() {
        return appointmentRepository.findTopDoctors().stream()
                .map(result -> Map.of(
                        "doctorName", result[0],
                        "specialization", result[1],
                        "appointmentCount", result[2]
                ))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getAppointmentsByDayReport() {
        return appointmentRepository.countAppointmentsByDay().stream()
                .map(result -> Map.of(
                        "day", result[0],
                        "count", result[1]
                ))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getFrequentPatientsReport() {
        return appointmentRepository.findFrequentPatients().stream()
                .map(result -> Map.of(
                        "patientName", result[0],
                        "appointmentCount", result[1]
                ))
                .collect(Collectors.toList());
    }
}