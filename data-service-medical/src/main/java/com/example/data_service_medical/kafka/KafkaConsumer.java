package com.example.data_service_medical.kafka;

import com.example.data_service_medical.dto.AppointmentDto;
import com.example.data_service_medical.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    private final AppointmentService appointmentService;

    @KafkaListener(topics = "${spring.kafka.topic.appointments}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {
                    "spring.json.value.default.type=com.example.data_service_medical.dto.AppointmentDto",
                    "spring.json.trusted.packages=com.example.api_service_medical.dto,com.example.data_service_medical.dto",
                    "spring.json.use.type.headers=false"
            })
    public void consumeAppointment(@Payload AppointmentDto appointmentDto) {
        log.info("Received appointment for patient: {}", appointmentDto.getPatientName());
        System.out.println("Получено!!!");
        appointmentService.createAppointment(appointmentDto);
    }
}