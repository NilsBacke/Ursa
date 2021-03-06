package com.parse.starter.FourPillarsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;
import com.parse.starter.R;

public class AgilityFragment extends Fragment {
    private static String title = "Agility";

    public static Fragment newInstance(Context context) {
        AgilityFragment f = new AgilityFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_agility, null);
        getActivity().setTitle(title);

        TextView scoreText = (TextView) root.findViewById(R.id.myScoreText);
        try {
            scoreText.setText(ParseUser.getCurrentUser().get("score").toString());
        } catch (Exception e) {
            e.printStackTrace();
            scoreText.setText("0");
        }
        return root;
    }
}
