package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

/**
 * A class representing an object containing information
 * about major US airports, time (month/year), and delay statistics.
 * Data taken between 01/2010 and 12/2015.
 * Deserialized from <code>delays.json</code>.
 */
public class AirportInfo {

    private Airport airport;
    private Time time;
    private Statistics statistics;

    /**
     * A class representing an airport.
     * Contains the three-letter airport code and full name.
     */
    public class Airport {
        private String code;
        private String name;

        public String getCode() {return code;}
        public String getName() {return name;}
    }

    /**
     * A class representing the date.
     * Contains the month and year in which data was collected.
     */
    public class Time {
        private int month;
        private int year;

        public int getMonth()   {return month;}
        public int getYear()    {return year;}
    }

    /**
     * A class representing flight statistics.
     * Contains inner classes for flights and number of minutes delayed.
     */
    public class Statistics {

        private Flights flights;
        private MinDelay minutesDelayed;

        public class Flights {
            private int cancelled;
            private int delayed;
            private int diverted;
            private int onTime;
            private int total;

            public int getCancelled()   {return cancelled;}
            public int getDelayed()     {return delayed;}
            public int getDiverted()    {return diverted;}
            public int getOnTime()      {return onTime;}
            public int getTotal()       {return total;}
        }

        public class MinDelay {
            private int total;
            public int getTotal() {return total;}
        }

        public MinDelay getMinutesDelayed() {return minutesDelayed;}
        public Flights getFlights()         {return flights;}
    }

    public Airport getAirport()         {return airport;}
    public Time getTime()               {return time;}
    public Statistics getStatistics()   {return statistics;}

    public String toString() {
        return "Airport: " + this.airport.code + " (" + this.airport.name + ")\n" +
                "Date: " + this.time.month + "/" + this.time.year;

    }
}

