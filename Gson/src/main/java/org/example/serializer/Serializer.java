package org.example.serializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public static void main(String[] args) {
        System.out.println("Serializer");

        serializeUser();
        deserializerUser();
        serializeRestaurant();
        deserializeUsers();

    }


    private static void serializeUser(){
        UserAdress adress=new UserAdress("rua","32","fortal","brasil");
        User user= new User("e@gmail.com","ernestogo",23,true,adress);
        // criando um json desse objeto
        Gson gson= new Gson();
        String json=gson.toJson(user);
        System.out.println(json);
    }

    private static void deserializerUser(){
        String userJson="{'name':'ernestogo','email':'e@gmail.com','age':23,'isDeveloper':true}";
        Gson json= new Gson();
        User user= json.fromJson(userJson,User.class);
        System.out.println(user);
    }

    private static void serializeRestaurant(){
        List<Restaurant.RestaurantMenuItems>menuItems=new ArrayList<>();
        Restaurant.RestaurantMenuItems item= new Restaurant.RestaurantMenuItems("meat",200);
        menuItems.add(item);
        menuItems.add(item);
        menuItems.add(item);

        Restaurant restaurant=new Restaurant("cOCO BAMBU",menuItems);

       String json= new Gson().toJson(restaurant);
        System.out.println(json);
    }

    private static void deserializeUsers(){
        String items = "[" +
                "{" +
                "\"name\":\"ernestogo\"," +
                "\"email\":\"e@gmail.com\"," +
                "\"age\":23," +
                "\"isDeveloper\":true," +
                "\"userAdress\":{" +
                "\"street\":\"Rua das Flores\"," +
                "\"houseNumber\":\"101\"," +
                "\"city\":\"Fortaleza\"," +
                "\"country\":\"Brasil\"" +
                "}" +
                "}," +
                "{" +
                "\"name\":\"joana.dev\"," +
                "\"email\":\"joana@gmail.com\"," +
                "\"age\":29," +
                "\"isDeveloper\":true," +
                "\"userAdress\":{" +
                "\"street\":\"Av. Central\"," +
                "\"houseNumber\":\"210\"," +
                "\"city\":\"Recife\"," +
                "\"country\":\"Brasil\"" +
                "}" +
                "}," +
                "{" +
                "\"name\":\"marcos98\"," +
                "\"email\":\"marcos98@gmail.com\"," +
                "\"age\":31," +
                "\"isDeveloper\":false," +
                "\"userAdress\":{" +
                "\"street\":\"Rua do Sol\"," +
                "\"houseNumber\":\"45B\"," +
                "\"city\":\"Salvador\"," +
                "\"country\":\"Brasil\"" +
                "}" +
                "}" +
                "]";


        Gson json=new Gson();

       User[] users= json.fromJson(items,User[].class);

       System.out.println(users);

       // em listas temos que usar um type token

        Type foundListType= new TypeToken<ArrayList<User>>(){}.getType();

        List<User> users1= json.fromJson(items,foundListType);

        System.out.println(users1);




    }



}
