package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

/**
 * A class representing an object containing information
 * about the Airport,
 */
public class AirportInfo {

    private Airport airport;
    private Time time;
    private Statistics statistics;

    private class Airport {
        private String code;
        private String name;
        public String toString() {return code;}
    }

    private class Time {
        private String label;
        private int month;
        private String monthName;
        private int year;
    }

    private class Statistics {
        private NumDelays numDelays;
//        private Carriers Carriers;
        private Flights flights;
        private MinDelay minutesDelayed;

        private class NumDelays {
            private int carrier;
            private int lateAircraft;
            private int NAS;
            private int security;
            private int weather;
        }
//        private class Carriers {
//            private String Names;
//            private int total;
//        }
        private class Flights {
            private int cancelled;
            private int delayed;
            private int diverted;
            private int onTime;
            private int total;
        }
        private class MinDelay {
            private int carrier;
            private int lateAircraft;
            private int NAS;
            private int security;
            private int total;
            private int weather;
        }
    }

    public String toString() {
        return "Airport: " + this.airport + "\n" +
                "Date: " + this.time.month + "/" + this.time.year;
    }
}

