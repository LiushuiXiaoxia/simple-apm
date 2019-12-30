package com.example.influxdbdemo.dto;

import com.google.gson.Gson;
import io.searchbox.annotations.JestId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Document(indexName = "log_trace3", type = "doc")
@Data
public class RecordDto implements Serializable {

    @Id
    @JestId
    private long id;

    @Field(type = FieldType.Text)
    private String db;

    @Field(type = FieldType.Text)
    private String measurement;

    @Field(type = FieldType.Object)
    private Map<String, String> tags = new HashMap<>();

    @Field(type = FieldType.Object)
    private Map<String, Integer> fields = new HashMap<>();

    @Field(type = FieldType.Date)
    private Date current;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}