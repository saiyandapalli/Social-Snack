package com.saiyandapalli.vroom;

import java.util.ArrayList;

/** An event between a group of people */
public class Event {

    private DateTime when;
    private ArrayList<User> attendees;

    public Event() {

    }

    private class DateTime {
        private int month, day, year, hour, minute;
        private boolean AM;
    }
}