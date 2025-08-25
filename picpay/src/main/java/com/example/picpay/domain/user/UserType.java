package com.example.picpay.domain.user;

public enum UserType {
    COMMON("common"),
    MERCHANT("merchant");

    private String type;

    UserType(String type){
        this.type=type;
    }

    public String getType(){
        return this.type;
    }
}
