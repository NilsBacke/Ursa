package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class SkillsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

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

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(list, this);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("Skills", " Clicked on Item " + position);
            }
        });
    }


}
