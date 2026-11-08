package org.example.myCollections.myList;

public class UserList {
    private int code;
    private String name;

    public UserList(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public UserList(){

    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
