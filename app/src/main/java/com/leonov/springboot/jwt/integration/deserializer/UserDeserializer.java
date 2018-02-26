package com.leonov.springboot.jwt.integration.deserializer;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.leonov.springboot.jwt.integration.service.UserService;
import com.leonov.springboot.jwt.integration.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class UserDeserializer  extends StdDeserializer<User> {
    @Autowired
    UserService userService;

    UserDeserializer(){
        this(User.class);
    }
    public UserDeserializer(Class<?> vc) {
        super(vc);
    }

    public UserDeserializer(JavaType valueType) {
        super(valueType);
    }

    public UserDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public User deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, com.fasterxml.jackson.databind.DeserializationContext deserializationContext) throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
        Long id = Long.parseLong(jsonParser.getText());
        return userService.findOne(id);
    }
}
