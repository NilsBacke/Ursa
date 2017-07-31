package com.parse.starter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;


public class FriendsListRecyclerViewAdapter extends RecyclerView
        .Adapter<FriendsListRecyclerViewAdapter
        .DataObjectHolder> {

    private ArrayList<ParseUser> mDataset;
    private static MyClickListener myClickListener;





    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView username;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.friendsNameText);
            username = (TextView) itemView.findViewById(R.id.friendsUsernameText);
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

    public FriendsListRecyclerViewAdapter(ArrayList<ParseUser> myDataset) {
        mDataset = new ArrayList<>();
        mDataset.addAll(myDataset);
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
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.name.setText(mDataset.get(position).get("name").toString());
        holder.username.setText(mDataset.get(position).getUsername().toString());
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
