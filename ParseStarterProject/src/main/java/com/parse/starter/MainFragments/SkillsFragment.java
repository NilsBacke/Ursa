package com.parse.starter.MainFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.starter.FourPillarsFragments.AgilityFragment;
import com.parse.starter.FourPillarsFragments.SpeedFragment;
import com.parse.starter.FourPillarsFragments.StaminaFragment;
import com.parse.starter.FourPillarsFragments.StrengthFragment;
import com.parse.starter.R;
import com.parse.starter.SkillsRecyclerViewAdapter;

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
        list.add(new DataObject("Strength", R.drawable.deadlifting));
        list.add(new DataObject("Agility", R.drawable.boxjumping));
        list.add(new DataObject("Speed", R.drawable.running));
        list.add(new DataObject("Stamina", R.drawable.spinning));

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

                Fragment fragment = null;

                switch (position) {
                    case 0:
                        fragment = new StrengthFragment();
                        break;
                    case 1:
                        fragment = new AgilityFragment();
                        break;
                    case 2:
                        fragment = new SpeedFragment();
                        break;
                    case 3:
                        fragment = new StaminaFragment();
                        break;
                    default:
                        break;
                }
                if (fragment != null) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.skills_frame, fragment);
                    ft.commit();
                }
            }
        });
    }
}
