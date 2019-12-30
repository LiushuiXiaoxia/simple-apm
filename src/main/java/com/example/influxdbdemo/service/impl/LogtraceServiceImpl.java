package com.example.influxdbdemo.service.impl;

import com.example.influxdbdemo.dto.RecordDto;
import com.example.influxdbdemo.service.ILogtraceService;
import com.example.influxdbdemo.service.RecordRepo;
import com.google.gson.Gson;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Service
public class LogtraceServiceImpl implements ILogtraceService {

    //    @Autowired
    JestClient jestClient;

    @Autowired
    InfluxDbLogtraceHelper influxDbLogtraceHelper;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    RecordRepo elasticsearchRepository;

    @Override
    public void addLogtrace(RecordDto dto) {
        dto.setId(System.currentTimeMillis());
        dto.setCurrent(new Date());

        influxDbLogtraceHelper.add(dto);

        // toEs(dto);
        // elasticsearchTemplate.createIndex(RecordDto.class);
        RecordDto save = elasticsearchRepository.save(dto);
        log.info("save = {}", save);
    }

    private void toEs(RecordDto dto) {
        Index index = new Index.Builder(dto).index("log_trace2").type("doc").build();
        try {
            DocumentResult result = jestClient.execute(index);
            log.info("result = " + new Gson().toJson(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}