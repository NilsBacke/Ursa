package com.parse.starter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class FeedListViewAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<ParseObject> list;
    private Context context;

    public FeedListViewAdapter(ArrayList<ParseObject> list, Context context) {
        this.list = new ArrayList<>();
        this.list = list;
        this.context = context;
        Log.i("Adapter", this.list.toString());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.feeditem, null);

            holder.name = (TextView) view.findViewById(R.id.statusMsgName);
            holder.username = (TextView) view.findViewById(R.id.statusMsgUsername);
            holder.content = (TextView) view.findViewById(R.id.statusMsgContent);
            holder.timestamp = (TextView) view.findViewById(R.id.statusMsgTimestamp);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Log.i("Adapter1", list.get(position).get("createdByName").toString());
        holder.name.setText(list.get(position).get("createdByName").toString());
        holder.username.setText(list.get(position).get("createdBy").toString());
        holder.content.setText(list.get(position).get("content").toString());


        return view;
    }


    private class ViewHolder {
        TextView name;
        TextView username;
        TextView content;
        TextView timestamp;
    }
}



