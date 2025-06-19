package org.example.serializer;

import java.util.List;

public class Restaurant {
    private String name;
    List<RestaurantMenuItems> items;

    public Restaurant(String name, List<RestaurantMenuItems> items) {
        this.name = name;
        this.items = items;
    }

    public static class RestaurantMenuItems{
        String name;
        float price;

        public RestaurantMenuItems(String name,float price){
            this.name=name;
            this.price=price;
        }
    }
}
