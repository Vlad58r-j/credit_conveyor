package com.vlad.project.service;

import com.vlad.project.dto.EmailMessage;

import java.util.concurrent.ExecutionException;

public interface KafkaProducerService {

    void sendEmailMessage(EmailMessage dto) throws ExecutionException, InterruptedException;
}
