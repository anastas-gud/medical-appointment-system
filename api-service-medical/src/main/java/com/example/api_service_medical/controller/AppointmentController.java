package com.example.api_service_medical.controller;

import com.example.api_service_medical.dto.AppointmentDto;
import com.example.api_service_medical.model.Appointment;
import com.example.api_service_medical.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        appointmentService.createAppointment(appointmentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AppointmentDto>> searchAppointments(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) Integer doctorId,
            @RequestParam(required = false) String status) {

        List<AppointmentDto> appointments = appointmentService.searchAppointments(patientName, doctorId, status);

        return ResponseEntity
                .ok()
                .body(appointments);
    }
}
