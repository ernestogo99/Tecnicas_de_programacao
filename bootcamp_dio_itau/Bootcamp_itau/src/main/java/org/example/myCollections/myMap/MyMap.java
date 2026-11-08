package org.example.myCollections.myMap;

import org.example.myCollections.myList.UserList;

import java.util.HashMap;
import java.util.Map;

public class MyMap {

    public static void main(String[] args) {
        Map<String, UserList> userMap = new HashMap<>();
        userMap.put("user1", new UserList(1, "Lucas"));
        userMap.put("user2", new UserList(2, "Maria"));
        userMap.put("user3", new UserList(3, "João"));
        System.out.println(userMap);
        System.out.println("================================");
        userMap.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("================================");
        userMap.keySet().forEach(System.out::println);
        System.out.println("================================");
        userMap.values().forEach(System.out::println);
        System.out.println("================================");
        userMap.remove("user2");
        System.out.println("userMap size: " + userMap.size());
        System.out.println("userMap isEmpty: " + userMap.isEmpty());
        System.out.println("userMap containsKey user1: " + userMap.containsKey("user1"));

    }
}
