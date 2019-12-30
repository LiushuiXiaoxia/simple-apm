package com.example.influxdbdemo.service.impl;

import com.example.influxdbdemo.dto.RecordDto;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class InfluxDbLogtraceHelper {

    @Autowired
    InfluxDB influxDB;

    String name = "log_trace";

    public void add(RecordDto dto) {
        dto.getFields().put("exec_time", new Random().nextInt(1000));
        log.info("dto = {}", dto);

        influxDB.setDatabase(name);

        if (dto.getFields() != null && dto.getTags() != null) {
            Point.Builder builder = Point.measurement(dto.getMeasurement())
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

            dto.getTags().forEach(builder::tag);
            dto.getFields().forEach(builder::addField);
            builder.tag("ttt", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
            influxDB.write(builder.build());
        }
    }
}