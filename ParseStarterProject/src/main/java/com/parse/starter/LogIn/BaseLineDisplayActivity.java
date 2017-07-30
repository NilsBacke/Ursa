package com.parse.starter.LogIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;
import com.parse.starter.MainFragments.MainActivity;
import com.parse.starter.R;

public class BaseLineDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_line_display);

        TextView baselineTextView = (TextView) findViewById(R.id.baselineText);
        baselineTextView.setText(Double.toString((Double) ParseUser.getCurrentUser().get("baseline")));
    }

    public void startButton(View view) {
        Intent intent = new Intent(BaseLineDisplayActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
