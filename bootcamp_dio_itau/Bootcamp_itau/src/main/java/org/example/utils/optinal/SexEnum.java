package org.example.utils.optinal;

public enum SexEnum {
    MAN("Homem"),
    WOMAN("Mulher");

    private final String value;

    SexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
