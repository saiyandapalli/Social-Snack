package com.saiyandapalli.vroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saiyandapalli.vroom.R;
import com.saiyandapalli.vroom.User;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.UserViewHolder> {

    // this is needed for the adapter, so we pass it in
    Context context;
    List<User> data;

    ListAdapter(Context context, List<User> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // pretty much just copy paste this
        View rowView = LayoutInflater.from(context).inflate(R.layout.row_view, viewGroup, false);
        // now that we've created the row view, lets put it in the EmailViewHolder so it knows what views to hold
        return new UserViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        // our emailViewHolder contains the subject and body views, now let's set the text
        userViewHolder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        // how many items are there
        return data.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, time;

        public UserViewHolder(@NonNull View itemView) {
            // itemView is the rowView passed in from the onCreateViewHolder
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
        }

        // this code could be in onBindViewHolder but its cleaner to do it like this
        void bind(User user) {
            name.setText(user.fullName());
        }
    }
}
