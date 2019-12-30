package com.example.influxdbdemo.service;

import com.example.influxdbdemo.dto.RecordDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RecordRepo extends ElasticsearchRepository<RecordDto, Long> {
}
