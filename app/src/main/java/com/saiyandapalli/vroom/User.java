package com.saiyandapalli.vroom;

import java.util.ArrayList;

/** A user of the application. */
public class User {
    
    private String firstName;
    private String lastName;
    private ArrayList<Group> groups;
    private ArrayList<Group> creatorOf;
    private ArrayList<Event> listOfEvents;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Group> getCreatorOf() {
        return creatorOf;
    }

    public void setCreatorOf(ArrayList<Group> creatorOf) {
        this.creatorOf = creatorOf;
    }

    public ArrayList<Event> getListOfEvents() {
        return listOfEvents;
    }

    public void setListOfEvents(ArrayList<Event> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }

    public User(String first, String last) {
        this.firstName = first;
        this.lastName = last;
//        addGroup(Group.RANDOM);
//        listOfEvents = new ArrayList<>();
    }

    private String nameify(String s) {
        if (s != null && s.length() != 0) {
            String first = s.substring(0, 1).toUpperCase();
            return first + s.substring(1).toLowerCase();
        }
        throw new IllegalArgumentException();
    }

    public void createGroup(String name) {

    }
    public ArrayList<Group> groupReturn() {
        return groups;
    }
    public void updateEvents() {
        ArrayList<Event> tempEvent= new ArrayList<>();


        for (Group groupx : groups) {
            ArrayList<Event> tempEventList = groupx.listEvents();
            for (Event e : tempEventList) {
                listOfEvents.add(e);
            }
        }
    }
    public ArrayList<Event> userEvents() {
        return listOfEvents;
    }
    public void addGroup(Group g) {
        this.groups.add(g);
    }

    public void addGroups(ArrayList<Group> groups) {
        this.groups.addAll(groups);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String fullName() {
        return this.firstName + " " + this.lastName;
    }

}
