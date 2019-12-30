package com.example.influxdbdemo.controller;

import com.example.influxdbdemo.dto.RecordDto;
import com.example.influxdbdemo.service.ILogtraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "hello", containerFactory = "customContainerFactory")
public class MqReceiver {

    @Autowired
    ILogtraceService service;

    @RabbitHandler
    public void process(RecordDto dto) {
        log.info("process dto = " + dto);
        service.addLogtrace(dto);
    }
}
