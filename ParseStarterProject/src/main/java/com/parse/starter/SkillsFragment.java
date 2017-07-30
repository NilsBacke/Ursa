package com.parse.starter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;


public class SkillsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String title = "Skills";

    public static Fragment newInstance(Context context) {
        SkillsFragment f = new SkillsFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_skills, null);
        getActivity().setTitle(title);

        // For images, see licensing info in raw
        ArrayList<DataObject> list = new ArrayList<>();
        list.add(new DataObject("Running", R.drawable.running));
        list.add(new DataObject("Spinning", R.drawable.spinning));
        list.add(new DataObject("Squats", R.drawable.squats));
        list.add(new DataObject("Box Jumping", R.drawable.boxjumping));
        list.add(new DataObject("Deadlifting", R.drawable.deadlifting));
        list.add(new DataObject("Curling", R.drawable.curling));
        list.add(new DataObject("Bench Press", R.drawable.benchpress));
        list.add(new DataObject("Vertical Leaps", R.drawable.verticalleaps));
        list.add(new DataObject("Body Weight", R.drawable.bodyweight));

        mRecyclerView = (RecyclerView) root.findViewById(R.id.skillsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SkillsRecyclerViewAdapter(list, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((SkillsRecyclerViewAdapter) mAdapter).setOnItemClickListener(new SkillsRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("Skills", " Clicked on Item " + position);
            }
        });
    }
}
