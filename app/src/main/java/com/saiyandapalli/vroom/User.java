package com.saiyandapalli.vroom;

import java.util.ArrayList;

/** A user of the application. */
public class User {

    private String firstName;
    private String lastName;
    private ArrayList<Group> groups;

    public User(String first, String last) {
        this.firstName = nameify(first);
        this.lastName = nameify(last);
        addGroup(Group.RANDOM);
    }

    private String nameify(String s) {
        if (s != null && s.length() != 0) {
            String first = s.substring(0, 1).toUpperCase();
            return first + s.substring(1).toLowerCase();
        }
        throw new IllegalArgumentException();
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
