package com.example;


import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

//It may be a good idea to rename/refactor depending on the focus of your assignment.
public class JSONTest {
    @Before
    public void setUp() throws FileNotFoundException {
        // This is run before every test.
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/delays.json"));
        Gson gson = new Gson();
        AirportInfo[] airports = gson.fromJson(reader, AirportInfo[].class);

    }

    @Test
    public void sanityCheck() {
    }
}