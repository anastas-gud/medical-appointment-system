package com.example.data_service_medical.controller;

import com.example.data_service_medical.dto.AppointmentDto;
import com.example.data_service_medical.model.Appointment;
import com.example.data_service_medical.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDto> searchAppointments(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) Integer doctorId,
            @RequestParam(required = false) String status) {
        return appointmentService.searchAppointments(patientName, doctorId, status);
    }
}