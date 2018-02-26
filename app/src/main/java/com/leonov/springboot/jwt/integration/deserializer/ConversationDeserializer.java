package com.leonov.springboot.jwt.integration.deserializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.leonov.springboot.jwt.integration.domain.ConsultantGroup;
import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.CustomerInformation;
import com.leonov.springboot.jwt.integration.service.ConversationService;
import com.leonov.springboot.jwt.integration.service.CustomerInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ConversationDeserializer extends StdDeserializer<Conversation> {
    @Autowired
    ConversationService service;

    ConversationDeserializer() {
        this(ConsultantGroup.class);
    }

    public ConversationDeserializer(Class<?> vc) {
        super(vc);
    }

    public ConversationDeserializer(JavaType valueType) {
        super(valueType);
    }

    public ConversationDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Conversation deserialize(com.fasterxml.jackson.core.JsonParser jsonParser,
                                           com.fasterxml.jackson.databind.DeserializationContext deserializationContext)
            throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
        Long id = Long.parseLong(jsonParser.getText());
        Conversation one = service.findOne(id);
        return one;
    }
}
