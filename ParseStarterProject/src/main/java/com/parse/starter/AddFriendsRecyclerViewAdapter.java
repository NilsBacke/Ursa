package com.parse.starter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

/**
 * Created by Nils on 7/30/17.
 */

public class AddFriendsRecyclerViewAdapter extends RecyclerView
        .Adapter<AddFriendsRecyclerViewAdapter
        .DataObjectHolder> {

    private ArrayList<ParseUser> mDataset;
    private Context context;
    private ArrayList<String> friendslist;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView username;
        TextView followButton;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.addFriendsNameText);
            username = (TextView) itemView.findViewById(R.id.addFriendsUsernameText);
            followButton = (TextView) itemView.findViewById(R.id.followTextViewButton);
            Log.i("Add Friends", "Adding Listener");
        }

    }

    public AddFriendsRecyclerViewAdapter(ArrayList<ParseUser> myDataset, Context context) {
        mDataset = new ArrayList<>();
        mDataset.addAll(myDataset);
        this.context = context;
        friendslist = new ArrayList<>();
        if (ParseUser.getCurrentUser().getList("friends") != null) {
            friendslist.addAll((ArrayList) ParseUser.getCurrentUser().getList("friends"));
        }
        Log.i("Adapter", mDataset.toString());
    }

    @Override
    public AddFriendsRecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addfriendsrecycleitem, parent, false);
        AddFriendsRecyclerViewAdapter.DataObjectHolder dataObjectHolder = new AddFriendsRecyclerViewAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final AddFriendsRecyclerViewAdapter.DataObjectHolder holder, int position) {
        Log.i("Friends", friendslist.toString());
        holder.name.setText(mDataset.get(position).get("name").toString());
        holder.username.setText(mDataset.get(position).getUsername().toString());

        if (isFriendsWith(ParseUser.getCurrentUser(), mDataset.get(position))) {
            holder.followButton.setText("Following");
            holder.followButton.setBackgroundColor(Color.TRANSPARENT);
        } else {
            holder.followButton.setText("Follow");
        }

        holder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.followButton.getText().toString().matches("Follow")) {
                    friendslist.add(holder.username.getText().toString());
                    ParseUser.getCurrentUser().put("friends", friendslist);
                    Log.i("Friends", ParseUser.getCurrentUser().get("friends").toString());
                    ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(context, holder.username.getText().toString() + " has been added.", Toast.LENGTH_SHORT).show();
                            } else {
                                e.printStackTrace();
                            }
                        }
                    });
                    holder.followButton.setText("Following");
                    holder.followButton.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }

    public void addItem(ParseUser dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // if user1 is following user2
    public boolean isFriendsWith(ParseUser user1, ParseUser user2) {
        ArrayList<String> usersfriends = new ArrayList<>();
        usersfriends.addAll((ArrayList) user1.getList("friends"));
        if (usersfriends.contains(user2.getUsername())) {
            return true;
        } else {
            return false;
        }
    }
}
