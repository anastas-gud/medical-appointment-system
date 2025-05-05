package com.example.data_service_medical.config;

import com.example.data_service_medical.dto.AppointmentDto;
import com.example.data_service_medical.model.Appointment;
import com.example.data_service_medical.model.Doctor;
import com.example.data_service_medical.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(DoctorRepository doctorRepository) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
