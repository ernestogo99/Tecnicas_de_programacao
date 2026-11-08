package org.example.myCollections.myList;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyList {
    private static void vectorTest() {
        var arrayStart= OffsetDateTime.now();
        List<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 100_000_000; i++) {
            arrayList.add(i);
        }
        System.out.println(Duration.between(arrayStart,OffsetDateTime.now()).toMillis());

        //devemos usar o vector em ambiente multi-thread, pois ele é thread-safe, mas em ambiente single-thread, o arrayList é mais performático.
        List<Integer> vectorList = new Vector<>();
        var vectorStart= OffsetDateTime.now();

        for (int i = 0; i < 100_000_000; i++) {
            vectorList.add(i);
        }
        System.out.println(Duration.between(vectorStart,OffsetDateTime.now()).toMillis());

    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");

        System.out.println("List size: " + list.size());
        System.out.println("Item at index 1: " + list.get(1));
        list.forEach(System.out::println);
        List <String> upperCaseList = list.stream().map(String::toUpperCase).toList();
        upperCaseList.forEach(System.out::println);
        list.remove(1);
        System.out.println("List size after removal: " + list.size());
        System.out.println("--------------------------------------");
        List<UserList> userList = new ArrayList<>();
        var user= new UserList(1,"Lucas");
        userList.add(user);
        System.out.println("UserList size: " + userList.size());
        System.out.println("isEmpty: " + userList.isEmpty());
        System.out.println("getLastIndex: " + userList.getLast());
        vectorTest();

    }



}
