package com.example;


import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class JSONTest {

    private AirportFunctions af;

    @Before
    public void setUp() throws FileNotFoundException {
        // This is run before every test.
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/delays.json"));
        Gson gson = new Gson();
        AirportInfo[] airports = gson.fromJson(reader, AirportInfo[].class);
        af = new AirportFunctions(airports);
    }

    @Test
    public void testFilterByYear() {
        ArrayList<AirportInfo> airportListActual = af.filterByYear(2012);
        for (AirportInfo ai : airportListActual) {
            assertEquals(2012, ai.getTime().getYear());
        }
    }

    @Test
    public void testFilterByYearMonth() {
        ArrayList<AirportInfo> airportListActual = af.filterByYearMonth(2014, 3);
        for (AirportInfo ai : airportListActual) {
            assertEquals(2014, ai.getTime().getYear());
            assertEquals(3, ai.getTime().getMonth());
        }
    }

    @Test
    public void testFilterByCode() {
        ArrayList<AirportInfo> airportListActual1 = af.filterByAirportCode("EWR");
        for (AirportInfo ai : airportListActual1) {
            assertEquals("EWR", ai.getAirport().getCode());
        }

        ArrayList<AirportInfo> airportListActual2 = af.filterByAirportCode("jfK");
        for (AirportInfo ai : airportListActual2) {
            assertEquals("JFK", ai.getAirport().getCode());
        }
    }

    @Test
    public void testFilterByDelaysMoreThan() {
        ArrayList<AirportInfo> airportListActual = af.filterByMonthlyDelaysMoreThan(2500);
        for (AirportInfo ai : airportListActual) {
            int numDelays = ai.getStatistics().getFlights().getDelayed();
            assertTrue(numDelays > 2500);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidYear() {
        String badResult = af.airportMostDelays(2009);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonth() {
        String badResult = af.airportMostDelays(2010, 13);
    }

    @Test(expected = AssertionError.class)
    public void testInvalidAirportCode() {
        double badResult = af.averageMinutesPerDelay("xyz");
    }

    @Test
    public void testAirportMostDelays() {
        String airportResult_201204 = af.airportMostDelays(2012, 4);
        String airportResult_201309 = af.airportMostDelays(2013, 9);
        assertEquals("ORD", airportResult_201204);
        assertEquals("ATL", airportResult_201309);
    }
}