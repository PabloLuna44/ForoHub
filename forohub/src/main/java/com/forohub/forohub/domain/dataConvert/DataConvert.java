package com.forohub.forohub.domain.dataConvert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvert {

    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T getData(String json, Class<T> Tclass) {
        try {
            return objectMapper.readValue(json, Tclass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}