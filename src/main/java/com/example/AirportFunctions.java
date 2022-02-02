package com.example;

import java.util.ArrayList;
import java.util.Arrays;

public class AirportFunctions {

    private ArrayList<AirportInfo> allAirports;

    public AirportFunctions(AirportInfo[] airports) {
        allAirports = new ArrayList<AirportInfo>(Arrays.asList(airports));
    }

    // ================================================================
    // Filtering methods
    // ================================================================

    /**
     * Filter AirportInfo data by year and month
     * @param year
     * @param month
     * @return ArrayList of AirportInfo objects
     */
    public ArrayList<AirportInfo> filterByYearMonth(int year, int month) {
        if (year < 2010 || year > 2015 || month < 1 || month > 12)
            throw new IllegalArgumentException("Invalid date");

        ArrayList<AirportInfo> airportList = new ArrayList<AirportInfo>();
        for (AirportInfo ai : allAirports) {
            if (ai.getTime().getYear() == year && ai.getTime().getMonth() == month) {
                airportList.add(ai);
            }
        }

        if (airportList.size() == 0)
            System.out.println("No result found. Returning empty list.");

        return airportList;
    }

    /**
     * Filter AirportInfo data by year
     * @param year
     * @return ArrayList of AirportInfo objects
     */
    public ArrayList<AirportInfo> filterByYear(int year) {
        if (year < 2010 || year > 2015)
            throw new IllegalArgumentException("Invalid date");

        ArrayList<AirportInfo> airportList = new ArrayList<AirportInfo>();

        for (AirportInfo ai : allAirports) {
            if (ai.getTime().getYear() == year) {
                airportList.add(ai);
            }
        }

        if (airportList.size() == 0)
            System.out.println("No result found. Returning empty list.");

        return airportList;
    }

    /**
     * Filter AirportInfo data by airport code
     * @param code Three-letter airport code, case-insensitive
     * @return ArrayList of AirportInfo objects
     */
    public ArrayList<AirportInfo> filterByAirportCode(String code) {
        ArrayList<AirportInfo> airportList = new ArrayList<AirportInfo>();
        for (AirportInfo ai : allAirports) {
            if (ai.getAirport().getCode().equalsIgnoreCase(code)) {
                airportList.add(ai);
            }
        }

        if (airportList.size() == 0)
            System.out.println("No result found. Returning empty list.");

        return airportList;
    }

    /**
     * Filter AirportInfo data by objects with more than certain number of delays
     * @param delays number of delays
     * @return ArrayList of AirportInfo objects
     */
    public ArrayList<AirportInfo> filterByMonthlyDelaysMoreThan(int delays) {
        ArrayList<AirportInfo> airportList = new ArrayList<AirportInfo>();
        for (AirportInfo ai : allAirports) {
            if (ai.getStatistics().getFlights().getDelayed() > delays) {
                airportList.add(ai);
            }
        }

        if (airportList.size() == 0)
            System.out.println("No result found. Returning empty list.");

        return airportList;
    }

    // ================================================================
    // Analysis methods
    // ================================================================

    /**
     * Find the airport with the most delays in a month
     * @param year
     * @param month
     * @return airport code as string
     */
    public String airportMostDelays(int year, int month) {
        if (year < 2010 || year > 2015 || month < 1 || month > 12)
            throw new IllegalArgumentException("Invalid date");

        String airportCode = "";
        int maxDelays = -1;
        ArrayList<AirportInfo> airportList = filterByYearMonth(year, month);
        for (AirportInfo ai : airportList) {
            int thisAirportDelays = ai.getStatistics().getFlights().getDelayed();
            if (thisAirportDelays > maxDelays) {
                maxDelays = thisAirportDelays;
                airportCode = ai.getAirport().getCode();
            }
        }

        return airportCode;
    }

    /**
     * Find the airport with the most delays in a year
     * @param year
     * @return airport code as string
     */
    public String airportMostDelays(int year) {
        if (year < 2010 || year > 2015)
            throw new IllegalArgumentException("Invalid date");

        String airportCode = "";
        int maxDelays = -1;
        ArrayList<AirportInfo> airportList = filterByYear(year);
        for (AirportInfo ai : airportList) {
            int thisAirportDelays = ai.getStatistics().getFlights().getDelayed();
            if (thisAirportDelays > maxDelays) {
                maxDelays = thisAirportDelays;
                airportCode = ai.getAirport().getCode();
            }
        }

        return airportCode;
    }

    /**
     * Find the airport with the smallest ratio of delays to total flights in a year
     * @param year
     * @return airport code as string
     */
    public String airportLeastDelayRatio(int year) {
        if (year < 2010 || year > 2015)
            throw new IllegalArgumentException("Invalid date");

        double leastRatio = Double.MAX_VALUE;
        String airportCode = "";

        ArrayList<AirportInfo> airportList = filterByYear(year);
        for (AirportInfo ai : airportList) {
            double numDelays = ai.getStatistics().getFlights().getDelayed();
            double totalFlights = ai.getStatistics().getFlights().getTotal();
            if (numDelays == totalFlights) continue; // in case of division by 0
            double thisRatio = numDelays / totalFlights;

            if (thisRatio < leastRatio) {
                airportCode = ai.getAirport().getCode();
                leastRatio = thisRatio;
            }
        }

        // System.out.println(leastRatio);
        return airportCode;
    }

    /**
     * Calculate the average number of delays for an airport per month
     * @param code the three-letter airport code
     * @return a double representing the average monthly delays
     */
    public double averageMonthlyDelays(String code) {
        ArrayList<AirportInfo> airportList = filterByAirportCode(code);
        if (airportList.size() == 0)
            throw new AssertionError("No airports with code " + code);

        double totalNumDelays = 0;
        for (AirportInfo ai : airportList)
            totalNumDelays += ai.getStatistics().getFlights().getDelayed();

        return totalNumDelays / airportList.size();
    }

    /**
     * Calculate the average number of minutes per delay for an airport
     * @param code the three-letter airport code
     * @return a double representing the average minutes per delay
     */
    public double averageMinutesPerDelay(String code) {
        ArrayList<AirportInfo> airportList = filterByAirportCode(code);
        if (airportList.size() == 0)
            throw new AssertionError("No airports with code " + code);

        double totalMinutes = 0;
        double totalNumDelays = 0;
        for (AirportInfo ai : airportList) {
            totalMinutes += ai.getStatistics().getMinutesDelayed().getTotal();
            totalNumDelays += ai.getStatistics().getFlights().getDelayed();
        }

        return totalMinutes / totalNumDelays;
    }
}
