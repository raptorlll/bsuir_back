package com.leonov.springboot.jwt.integration.deserializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.leonov.springboot.jwt.integration.domain.ConsultantGroup;
import com.leonov.springboot.jwt.integration.service.ConsultantGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ConsultantGroupDeserializer extends StdDeserializer<ConsultantGroup> {
    @Autowired
    ConsultantGroupService service;

    ConsultantGroupDeserializer() {
        this(ConsultantGroup.class);
    }

    public ConsultantGroupDeserializer(Class<?> vc) {
        super(vc);
    }

    public ConsultantGroupDeserializer(JavaType valueType) {
        super(valueType);
    }

    public ConsultantGroupDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public ConsultantGroup deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, com.fasterxml.jackson.databind.DeserializationContext deserializationContext) throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
        Long id = Long.parseLong(jsonParser.getText());
        return service.findOne(id);
    }
}
