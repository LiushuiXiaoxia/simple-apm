package com.example.influxdbdemo.controller;

import com.example.influxdbdemo.dto.RecordDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(RecordDto dto) {
        rabbitTemplate.convertAndSend("hello", dto);
    }
}
