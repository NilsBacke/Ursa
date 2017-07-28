package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;

import java.util.ArrayList;

public class GenderSelectionActivity extends AppCompatActivity {

    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_selection);
        data = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        data.addAll(getIntent().getStringArrayListExtra("data"));
    }

    public void male(View view) {
        data.add("male");
        goToInformation();
    }

    public void female(View view) {
        data.add("female");
        goToInformation();
    }

    public void goToInformation() {
        Intent intent = new Intent(GenderSelectionActivity.this, EnterInformationActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}
