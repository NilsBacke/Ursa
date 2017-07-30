package com.parse.starter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Nils on 7/30/17.
 */

public class AddFriendsRecyclerViewAdapter extends RecyclerView
        .Adapter<AddFriendsRecyclerViewAdapter
        .DataObjectHolder> {

    private ArrayList<ParseUser> mDataset;

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





    public AddFriendsRecyclerViewAdapter(ArrayList<ParseUser> myDataset) {
        mDataset = new ArrayList<>();
        mDataset.addAll(myDataset);
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
        holder.name.setText(mDataset.get(position).get("name").toString());
        holder.username.setText(mDataset.get(position).getUsername().toString());
        holder.followButton.setText("Follow");
        holder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().put("friends", holder.username.getText().toString());
                holder.followButton.setText("Following");
                holder.followButton.setBackgroundColor(Color.TRANSPARENT);
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

}
