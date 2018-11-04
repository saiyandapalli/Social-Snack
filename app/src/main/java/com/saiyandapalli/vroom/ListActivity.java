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
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));
        users.add(new User("Daddy", "Denero"));

        // standard stuff
        RecyclerView recyclerView = findViewById(R.id.userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(this, users));
    }
}
