package com.saiyandapalli.vroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Yash", "Vanvari", "12:00-1:00","/p/g"));
        users.add(new User("Sai", "Yandapalli", "3:00-5:00","/p/g"));
        users.add(new User("Abhi", "Pottabhatula", "9:00 -10:30","/p/g"));
        users.add(new User("Shreyas", "Patanker", "4:00-4:30","/p/g"));
        users.add(new User("Abe", "Lincoln", "6:00-8", "/p/g"));
        users.add(new User("George", "Washington", "9:00-10:00", "/p/g"));
        users.add(new User("Sad", "man", "3:00-4:40","/p/g"));
        users.add(new User("Daddy", "Denero","",""));
        users.add(new User("Daddy", "Denero","",""));
        users.add(new User("Daddy", "Denero","",""));
        users.add(new User("Daddy", "Denero","",""));
        users.add(new User("Daddy", "Denero","",""));
        users.add(new User("Daddy", "Denero","",""));
        users.add(new User("Daddy", "Denero","",""));

        // standard stuff
        RecyclerView recyclerView = findViewById(R.id.userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(this, users));
    }
}