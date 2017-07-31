package com.parse.starter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.LogIn.LogInActivity;
import com.parse.starter.MainFragments.MainActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class FriendsListRecyclerViewAdapter extends RecyclerView
        .Adapter<FriendsListRecyclerViewAdapter
        .DataObjectHolder> {

    private ArrayList<ParseUser> mDataset;
    private static MyClickListener myClickListener;
    private Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView username;
        ImageButton deleteButton;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.friendsNameText);
            username = (TextView) itemView.findViewById(R.id.friendsUsernameText);
            deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);
            Log.i("Friends", "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public FriendsListRecyclerViewAdapter(ArrayList<ParseUser> myDataset, Context context) {
        mDataset = new ArrayList<>();
        mDataset.addAll(myDataset);
        this.context = context;
        Log.i("Adapter", mDataset.toString());
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friendslistrecycleitem, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).get("name").toString());
        holder.username.setText(mDataset.get(position).getUsername());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setTitle("Remove friend");
                builder.setMessage("Are you sure you want to remove " + mDataset.get(position).getUsername()
                        + " from your friends list?");
                // Add the buttons
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final ParseUser deletedUser = mDataset.get(position);
                        mDataset.remove(position);
                        notifyDataSetChanged();
                        ArrayList<String> friendsusernames = new ArrayList<String>();
                        for (ParseUser user : mDataset) {
                            friendsusernames.add(user.getUsername());
                        }
                        ParseUser.getCurrentUser().put("friends", friendsusernames);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(context, deletedUser.getUsername() + " has been removed " +
                                            "from your friends list.", Toast.LENGTH_SHORT).show();
                                } else {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
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

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
