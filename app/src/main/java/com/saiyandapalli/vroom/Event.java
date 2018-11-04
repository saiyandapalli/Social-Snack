package com.saiyandapalli.vroom;

import java.util.ArrayList;

/** An event between a group of people */
public class Event {

    private DateTime event;
    private ArrayList<User> attendees;
    private ArrayList<Group> groups;
    private User creator;
    private ArrayList<Event> events;


    public Event(DateTime event,User creator) {
        this.event = event;
        this.creator = creator;
        attendees.add(creator);
        this.groups = new ArrayList<>();
        this.events = new ArrayList<>();

    }
    public void addGroup(String x) {
        groups.add(Group.mappy.get(x));

    }
    private class DateTime {
        private int month, day, year, hour, minute;
        private boolean AM;
        public DateTime(int month, int day, int year, int hour, int minute) {
            this.month = month;
            this.day = day;
            this.year = year;
            this.hour = hour;
            this.minute = minute;
        }
    }
}