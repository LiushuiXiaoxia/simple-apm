package com.example.influxdbdemo.controller;

import com.example.influxdbdemo.dto.RecordDto;
import com.example.influxdbdemo.service.ILogtraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    ILogtraceService service;

    ExecutorService executorService = Executors.newFixedThreadPool(30);

    @Autowired
    MqSender mqSender;

    @RequestMapping(value = "/1", method = RequestMethod.POST)
    public String test(@RequestBody RecordDto recordDto) {
        long now = System.currentTimeMillis();
        service.addLogtrace(recordDto);
        log.info("time = " + (System.currentTimeMillis() - now));
        return "ok";
    }

    @RequestMapping(value = "/2", method = RequestMethod.POST)
    public String test2(@RequestBody RecordDto recordDto) {
        long now = System.currentTimeMillis();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                service.addLogtrace(recordDto);
            }
        });
        log.info("time = " + (System.currentTimeMillis() - now));
        return "ok";
    }

    @RequestMapping(value = "/3", method = RequestMethod.POST)
    public String test3(@RequestBody RecordDto recordDto) {
        long now = System.currentTimeMillis();
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                service.addLogtrace(recordDto);
//            }
//        });
        mqSender.send(recordDto);
        log.info("time = " + (System.currentTimeMillis() - now));
        return "ok";
    }
}