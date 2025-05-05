package com.example.api_service_medical.producer;

import com.example.api_service_medical.dto.AppointmentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicAppointmentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.appointments}")
    private String appointmentsTopic;

    public TopicAppointmentProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAppointment(AppointmentDto appointmentDto) {
        kafkaTemplate.send(appointmentsTopic,"AppointmentPartitionKey", appointmentDto);
        System.out.println("Отправлено!!!");
    }
}