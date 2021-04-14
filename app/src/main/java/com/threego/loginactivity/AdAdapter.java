package com.threego.loginactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdAdapter extends BaseAdapter {

    private Context c;
    private int layout;
    private ArrayList<AdVO> data;
    private LayoutInflater inflater;

    public AdAdapter(Context c, int layout, ArrayList<AdVO> data) {
        this.c = c;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = inflater.inflate(layout, parent, false);

            //ImageView src = convertView.findViewById(R.id.adImg);
            //src.setImageResource(data.get(position).getImg());

            Glide.with(convertView.getContext()).load(data.get(position).getSrc()).
                    into((ImageView) convertView.findViewById(R.id.adImg));

            TextView title = convertView.findViewById(R.id.adTitle);
            title.setText(data.get(position).getAdTitle());

            TextView content = convertView.findViewById(R.id.adContent);
            content.setText(data.get(position).getAdContent());

            TextView onGoing = convertView.findViewById(R.id.adOn);
            onGoing.setText(data.get(position).getOnGoing());
        }
        return convertView;
    }
}
