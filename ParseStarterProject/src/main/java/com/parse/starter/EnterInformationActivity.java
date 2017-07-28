package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;

public class EnterInformationActivity extends AppCompatActivity {

    ArrayList<String> data;
    EditText ageEditText;
    EditText weightEditText;
    EditText heightEditText;
    TextView bmiTextView;
    double weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_information);
        data = new ArrayList<>();

        ageEditText = (EditText) findViewById(R.id.ageEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        heightEditText = (EditText) findViewById(R.id.heightEditText);
        bmiTextView = (TextView) findViewById(R.id.bmi);
        weight = 0;

        heightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                weight = Double.parseDouble(weightEditText.getText().toString());
                double bmi = weight * 0.45 / Math.pow(Double.parseDouble(s.toString()) * 0.254, 2);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        data.addAll(intent.getStringArrayListExtra("data"));
    }

    public void continueButton(View view) {
        int age = Integer.parseInt(ageEditText.getText().toString());
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());

        //Calculate BMI
        double bmi = weight * 0.45 / Math.pow(height * 0.254, 2);

        ParseUser user = new ParseUser();
        user.put("name", data.get(0));
        user.setEmail(data.get(1));
        user.setUsername(data.get(2));
        user.setPassword(data.get(3));
        user.put("gender", data.get(4));
        user.put("age", age);
        user.put("weight", weight);
        user.put("height", height);
        user.put("BMI", bmi);

        bmiTextView.setText(Double.toString(bmi));
    }
}
