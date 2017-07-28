package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

public class EnterInformationActivity extends AppCompatActivity {

    ArrayList<String> data;
    EditText ageEditText;
    EditText weightEditText;
    EditText feetEditText;
    EditText inchesEditText;
    TextView bmiTextView;
    double weight;
    double height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_information);
        data = new ArrayList<>();

        ageEditText = (EditText) findViewById(R.id.ageEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        feetEditText = (EditText) findViewById(R.id.feetEditText);
        inchesEditText = (EditText) findViewById(R.id.inchesEditText);
        bmiTextView = (TextView) findViewById(R.id.bmi);
        weight = 0;
        height = 0;

    }

    public void continueButton(View view) {
        data = getIntent().getStringArrayListExtra("data");
        Log.i("DATAAA", data.toString());
        int age = Integer.parseInt(ageEditText.getText().toString());
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double feet = Double.parseDouble(feetEditText.getText().toString());
        double inches = Double.parseDouble(inchesEditText.getText().toString());

        double height = feet * 12 + inches;

        //Calculate BMI
        double bmi = weight * 0.45 / Math.pow(height * 0.254, 2);

        ParseUser user = new ParseUser();
        user.setUsername(data.get(2));
        user.setPassword(data.get(3));
        user.put("name", data.get(0));
        user.setEmail(data.get(1));
        user.put("gender", data.get(4));
        user.put("age", age);
        user.put("weight", weight);
        user.put("height", height);
        user.put("BMI", bmi);

        //Get baseline score
        BaseLine baseLine = new BaseLine();
        final double baseline = Math.round(baseLine.getBaseLineScore(age, bmi)*1000.0)/1000.0;

        user.put("baseline", baseline);
        user.put("score", baseline);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("signup", "Success!");
                    Intent intent = new Intent(EnterInformationActivity.this, BaseLineDisplayActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EnterInformationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void calculateBMI(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        weight = Double.parseDouble(weightEditText.getText().toString());
        height = Double.parseDouble(feetEditText.getText().toString()) * 12 + Double.parseDouble(inchesEditText.getText().toString());;
        double bmi = weight * 0.45 / Math.pow(height * 0.0254, 2);
        bmiTextView.setText("BMI: " + Math.round(bmi * 10)/ 10.0);
    }
}
