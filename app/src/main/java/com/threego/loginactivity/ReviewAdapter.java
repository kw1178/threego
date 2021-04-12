package com.threego.loginactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    private Context c;
    private int layout;
    private ArrayList<ReviewVO> data;
    private LayoutInflater inflater;

    public ReviewAdapter(Context c, int layout, ArrayList<ReviewVO> data){
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

        if (convertView==null){
            convertView = inflater.inflate(layout,parent,false);

            // 뷰 홀더 해야 한다. 안그러면 클릭할 때마다 데이터 추가된다.

        }
        TextView tv_r_num = convertView.findViewById(R.id.tv_r_num);
        TextView tv_dl_num = convertView.findViewById(R.id.tv_dl_num);
        TextView tv_r_evaluation = convertView.findViewById(R.id.tv_r_evaluation);

        tv_r_num.setText(data.get(position).getR_num()+"");
        tv_dl_num.setText(data.get(position).getDl_num()+"");
        tv_r_evaluation.setText(data.get(position).getR_evaluation());


        return convertView;
    }
}
