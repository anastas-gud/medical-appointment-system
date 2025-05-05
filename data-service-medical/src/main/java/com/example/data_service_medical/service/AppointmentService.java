package com.example.data_service_medical.service;

import com.example.data_service_medical.dto.AppointmentDto;
import com.example.data_service_medical.model.Appointment;
import com.example.data_service_medical.model.Doctor;
import com.example.data_service_medical.repository.AppointmentRepository;
import com.example.data_service_medical.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void createAppointment(AppointmentDto appointmentDto) {
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        appointmentRepository.save(appointmentFromDto(appointmentDto));
    }

    public List<AppointmentDto> searchAppointments(String patientName, Integer doctorId, String status) {
        List<Appointment> appointments = appointmentRepository.findWithFilters(patientName, doctorId, status);
        if (appointments == null || appointments.isEmpty()) {
            return Collections.emptyList();
        }

        return appointments.stream()
                .filter(Objects::nonNull)
                .map(this::appointmentToDto)
                .collect(Collectors.toList());
    }
    public AppointmentDto appointmentToDto(Appointment appointment){
        AppointmentDto appointmentDto = modelMapper.map(appointment, AppointmentDto.class);
        appointmentDto.setDoctorId(appointment.getDoctor().getId());
        return appointmentDto;
    }
    public Appointment appointmentFromDto(AppointmentDto appointmentDto){
        Appointment appointment=modelMapper.map(appointmentDto, Appointment.class);
        if (appointmentDto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
            appointment.setDoctor(doctor);
        }
        return appointment;
    }
}