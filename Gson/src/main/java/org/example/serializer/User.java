package org.example.serializer;

import com.google.gson.annotations.Expose;

public class User {

    @Expose()
    private String name;
    @Expose(serialize = true,deserialize = false)
    private String email;
    @Expose()
    private int age;
    @Expose()
    private boolean isDeveloper;
    @Expose()
    private UserAdress userAdress;

    public User(String email, String name, int age, boolean isDeveloper,UserAdress userAdress) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.isDeveloper = isDeveloper;
        this.userAdress=userAdress;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", isDeveloper=" + isDeveloper +
                ", userAdress=" + userAdress +
                '}';
    }
}
