package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;

public class GenderSelectionActivity extends AppCompatActivity {

    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_selection);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        user = (ParseUser) intent.getSerializableExtra("user");
    }

    public void male(View view) {
        user.put("gender", "male");
        goToInformation();
    }

    public void female(View view) {
        user.put("gender", "female");
        goToInformation();
    }

    public void goToInformation() {
        Intent intent = new Intent(GenderSelectionActivity.this, EnterInformationActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
