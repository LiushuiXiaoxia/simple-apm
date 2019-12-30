package com.example.influxdbdemo.service;

import com.example.influxdbdemo.dto.RecordDto;

public interface ILogtraceService {

    void addLogtrace(RecordDto dto);
}
