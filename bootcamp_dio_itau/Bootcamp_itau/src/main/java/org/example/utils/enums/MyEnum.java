package org.example.utils.enums;

public enum MyEnum {
    FIRST(1, "First"),
    SECOND(2, "Second"),
    THIRD(3, "Third");

    private final int code;
    private final String name;

    MyEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
