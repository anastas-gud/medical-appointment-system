package com.example.api_service_medical.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Appointment {
    private Integer id;
    private String patientName;
    private String patientPhone;
    private LocalDateTime appointmentTime;
    private Integer doctorId;
    private String status;
}