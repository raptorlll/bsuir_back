package com.leonov.springboot.jwt.integration.enums;

public enum ConversationStatusesEnum {
    OPEN("OPEN"),
    PAYMENT("PAYMENT"),
    CLOSED("CLOSED");

    private String value;

    ConversationStatusesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
