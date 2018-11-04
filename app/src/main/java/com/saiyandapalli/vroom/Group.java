package com.saiyandapalli.vroom;

import java.util.*;

/** A group of users */
public class Group {

    static HashMap<String, Group> mappy = new HashMap<>();
    static Group RANDOM = new Group();
    private ArrayList<User> members;
    private User organizer;
    private String name;
    private ArrayList<Event> currEvents;



    public Group(User organizer, String name) {
        this.members = new ArrayList<User>();
        this.organizer = organizer;
        this.name = name;
        mappy.put(name, this);

    }
    public Group() {
        this.members = new ArrayList<User>();
    }
    public ArrayList<Event> listEvents() {
        return currEvents;
    }
    public Group(ArrayList<User> membs) {
        this.members.addAll(membs);
    }
    public Group addMember(User u) {
        members.add(u);
    }

    public ArrayList<User> getMembers() {
        return this.members;
    }

    public void addEvent(Event eventx) {
        currEvents.add(eventx);
    }
}
