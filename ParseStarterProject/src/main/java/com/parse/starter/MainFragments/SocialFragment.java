package com.parse.starter.MainFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.AddFriendFragment;
import com.parse.starter.FriendsListRecyclerViewAdapter;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;


public class SocialFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String title = "Friends List";

    public static Fragment newInstance(Context context) {
        SocialFragment f = new SocialFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_social, null);
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

        final ArrayList<ParseUser> friendslist = new ArrayList<>();
        mAdapter = new FriendsListRecyclerViewAdapter(friendslist);

        TextView noFriends = (TextView) root.findViewById(R.id.noFriends);
        if (friendslist.isEmpty()) {
            noFriends.setVisibility(View.VISIBLE);
        } else {
            noFriends.setVisibility(View.INVISIBLE);
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FriendsListRecyclerViewAdapter) mAdapter).setOnItemClickListener(new FriendsListRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("Skills", " Clicked on Item " + position);
            }
        });
    }

}
