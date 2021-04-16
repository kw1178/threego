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

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);

            ImageView src = convertView.findViewById(R.id.adImg);
            TextView title = convertView.findViewById(R.id.adTitle);
            TextView content = convertView.findViewById(R.id.adContent);
            TextView type = convertView.findViewById(R.id.adOn);

            //if (data.get(position).getAdType().equals("pic")) {

                Glide.with(convertView.getContext()).load(data.get(position).getSrc()).
                        into((ImageView) convertView.findViewById(R.id.adImg));
                title.setText(data.get(position).getAdTitle());
                content.setText(data.get(position).getAdContent());
                type.setVisibility(View.GONE);

//            }else{
//                src.setVisibility(View.GONE);
//                title.setVisibility(View.GONE);
//                content.setVisibility(View.GONE);
//                type.setVisibility(View.GONE);
//            }
        }
        return convertView;
    }
}
