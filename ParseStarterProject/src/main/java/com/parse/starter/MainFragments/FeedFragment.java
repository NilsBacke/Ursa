package com.parse.starter.MainFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.AddFriendFragment;
import com.parse.starter.FriendsListRecyclerViewAdapter;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends Fragment {

    private static String title = "Feed";

    public static Fragment newInstance(Context context) {
        FeedFragment f = new FeedFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_feed, null);
        getActivity().setTitle(title);

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.composeStatus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setTitle("Write a Status Message");
                final EditText statusEditText = new EditText(getActivity());
                statusEditText.setPadding(5, 0, 5, 0);
                builder.setView(statusEditText);
                // Add the buttons
                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ParseObject status = new ParseObject("Status");
                        status.put("content", statusEditText.getText().toString());
                        status.put("createdBy", ParseUser.getCurrentUser().getUsername());
                        status.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(getActivity(), "Status posted successfully", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return root;
    }

}
