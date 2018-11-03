package com.saiyandapalli.vroom;

import java.util.*;

/** A group of users */
public class Group {

    static Group RANDOM = new Group();
    private ArrayList<User> members;

    public Group() {
        this.members = new ArrayList<User>();
    }

    public Group(ArrayList<User> membs) {
        this.members.addAll(membs);
    }

    public ArrayList<User> getMembers() {
        return this.members;
    }
}
