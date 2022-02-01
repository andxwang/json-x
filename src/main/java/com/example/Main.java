package com.example;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        //File file = new File("src/main/resources/json");
        //There is no need for an adventure class, the example below is just for convenience
        //if you're using jackson. It's also a preview to your next assignment :)
        //Adventure adventure = new ObjectMapper().readValue(file, Adventure.class);
        //Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/delays.json"));
        Gson gson = new Gson();
        AirportInfo[] airports = gson.fromJson(reader, AirportInfo[].class);
        System.out.println(airports[0]);

//        BufferedReader reader2 = new BufferedReader(new FileReader("src/main/resources/testData.json"));
//        FooWithInner targetObject = new Gson().fromJson(reader2, FooWithInner.class);
//        System.out.println(targetObject.intValue);
//        System.out.println(targetObject.strValue);
//        System.out.println(targetObject.innerFoo.name);
    }
}