package com.parse.starter.MainFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.AddFriendFragment;
import com.parse.starter.FriendsListRecyclerViewAdapter;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;


public class FriendsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String title = "Friends List";
    private ArrayList<ParseUser> friendslist;

    public static Fragment newInstance(Context context) {
        FriendsFragment f = new FriendsFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_friendslist, null);
        getActivity().setTitle(title);

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.addFriends);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AddFriendFragment()).
                        addToBackStack(null).commit();
            }
        });

        mRecyclerView = (RecyclerView) root.findViewById(R.id.friendsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        final TextView noFriends = (TextView) root.findViewById(R.id.noFriends);
        friendslist = new ArrayList<>();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        if (ParseUser.getCurrentUser().getList("friends") != null) {
            query.whereContainedIn("username", ParseUser.getCurrentUser().getList("friends"));
            query.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> list, ParseException e) {
                    friendslist.addAll(list);
                    Log.i("Friendslist", friendslist.toString());
                    noFriends.setVisibility(View.INVISIBLE);
                    mAdapter = new FriendsListRecyclerViewAdapter(friendslist, getActivity());
                    mRecyclerView.setAdapter(mAdapter);
                }
            });
        } else {
            noFriends.setVisibility(View.VISIBLE);
        }

        mAdapter = new FriendsListRecyclerViewAdapter(friendslist, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FriendsListRecyclerViewAdapter) mAdapter).setOnItemClickListener(new FriendsListRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setTitle(friendslist.get(position).getUsername() + "'s Profile");
                builder.setMessage("Score: " + friendslist.get(position).get("score").toString());
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
                Log.i("Social", " Clicked on Item " + position);
            }
        });
    }

}
