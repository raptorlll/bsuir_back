package com.leonov.springboot.jwt.integration.deserializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.leonov.springboot.jwt.integration.service.CustomerInformationService;
import com.leonov.springboot.jwt.integration.domain.ConsultantGroup;
import com.leonov.springboot.jwt.integration.domain.CustomerInformation;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class CustomerInformationDeserializer extends StdDeserializer<CustomerInformation> {
    @Autowired
    CustomerInformationService service;

    CustomerInformationDeserializer() {
        this(ConsultantGroup.class);
    }

    public CustomerInformationDeserializer(Class<?> vc) {
        super(vc);
    }

    public CustomerInformationDeserializer(JavaType valueType) {
        super(valueType);
    }

    public CustomerInformationDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public CustomerInformation deserialize(com.fasterxml.jackson.core.JsonParser jsonParser,
                                           com.fasterxml.jackson.databind.DeserializationContext deserializationContext)
            throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
        Long id = Long.parseLong(jsonParser.getText());
        CustomerInformation one = service.findOne(id);
        return one;
    }
}
