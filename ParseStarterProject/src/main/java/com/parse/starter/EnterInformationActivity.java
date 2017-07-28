package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

public class EnterInformationActivity extends AppCompatActivity {

    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_information);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        user = (ParseUser) intent.getSerializableExtra("user");
    }

    public void continueButton(View view) {
        EditText age = (EditText) findViewById(R.id.ageEditText);
        EditText weight = (EditText) findViewById(R.id.weightEditText);
        EditText height = (EditText) findViewById(R.id.heightEditText);
        TextView bmiTextView = (TextView) findViewById(R.id.bmi);

        //Calculate BMI
        double bmi = Double.parseDouble(weight.getText().toString()) * 0.45 /
                Math.pow(Double.parseDouble(height.getText().toString()) * 0.254, 2);

        bmiTextView.setText(Double.toString(bmi));
    }
}
