package com.vlad.project.service;

import com.vlad.project.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService{

    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;

    @Override
    public void sendEmailMessage(EmailMessage dto) throws ExecutionException, InterruptedException {
        SendResult<String, EmailMessage> resultMessage = kafkaTemplate.send(dto.getTheme().getTopic(),
                dto.getApplicationId().toString(),
                dto)
                .get();

        log.info("topic -> {}", resultMessage.getRecordMetadata().topic());
        log.info("partition -> {}", resultMessage.getRecordMetadata().partition());
        log.info("offset -> {}", resultMessage.getRecordMetadata().offset());

    }
}
