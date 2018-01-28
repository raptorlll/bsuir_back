package com.nouhoun.springboot.jwt.integration.deserializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroup;
import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupService;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ConsultantGroupUserDeserializer extends StdDeserializer<ConsultantGroupUser> {
    @Autowired
    ConsultantGroupUserService service;

    ConsultantGroupUserDeserializer() {
        this(ConsultantGroup.class);
    }

    public ConsultantGroupUserDeserializer(Class<?> vc) {
        super(vc);
    }

    public ConsultantGroupUserDeserializer(JavaType valueType) {
        super(valueType);
    }

    public ConsultantGroupUserDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public ConsultantGroupUser deserialize(com.fasterxml.jackson.core.JsonParser jsonParser,
                                           com.fasterxml.jackson.databind.DeserializationContext deserializationContext) throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
        Long id = Long.parseLong(jsonParser.getText());
        ConsultantGroupUser one = service.findOne(id);
        return one;
    }
}
