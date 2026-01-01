package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper= new ObjectMapper();
        BankAccount account=new BankAccount(123,"teste");
        try {
            mapper.writeValue(new File("src/main/resources/bankAccount.json"),account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            String asString=mapper.writeValueAsString(account);
            System.out.println(asString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //readvalue

        String jsonString= """
                {
                "id":123,
                "holderName":"name"
                }
                """;

        String jsonArray= """
                [
                 {
                "id":123,
                "holderName":"name"
                },
                 {
                "id":1234,
                "holderName":"name2"
                },
                 {
                "id":1235,
                "holderName":"name3"
                }
                
                ]
                """;

        try {
            BankAccount stringBank= mapper.readValue(jsonString, BankAccount.class);
            System.out.println(stringBank);
            JsonNode jsonRootNode=mapper.readTree(jsonString);
            System.out.println(jsonRootNode.get("holderName").asText());
            //lists
            List<BankAccount> accountsList=mapper.readValue(jsonArray, new TypeReference<List<BankAccount>>() {
            });
            System.out.println(accountsList);


            //array
            mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,true);
            BankAccount[] accounts=mapper.readValue(jsonArray, BankAccount[].class);
            System.out.println(accounts);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}