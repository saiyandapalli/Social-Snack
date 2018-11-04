package com.saiyandapalli.vroom;

import java.util.*;

/** A group of users */
public class Group {

    static Group RANDOM = new Group("Random");
    private ArrayList<User> members;
    private String groupName;

    public Group(String name) {
        this.members = new ArrayList<User>();
        this.groupName = name;
    }

    public Group(ArrayList<User> membs, String name) {
        this.members.addAll(membs);
        this.groupName = name;
    }

    public ArrayList<User> getMembers() {
        return this.members;
    }
}
