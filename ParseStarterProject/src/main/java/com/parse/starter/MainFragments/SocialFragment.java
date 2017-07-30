package com.parse.starter.MainFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.FriendsListRecyclerViewAdapter;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;


public class SocialFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String title = "Social";

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

        mRecyclerView = (RecyclerView) root.findViewById(R.id.friendsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        final ArrayList<ParseUser> userlist = new ArrayList<>();

        ParseQuery<ParseUser> query = ParseUser.getQuery();


        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if (list != null && e == null) {
                    userlist.addAll(list);
                    Log.i("Social", userlist.toString());
                    mAdapter = new FriendsListRecyclerViewAdapter(userlist);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        mAdapter = new FriendsListRecyclerViewAdapter(userlist);

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
