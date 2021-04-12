package com.threego.loginactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoticeAdapter extends BaseAdapter {
    private Context c;
    private int layout;
    private ArrayList<NoticeVO> data;
    private LayoutInflater inflater;

    public NoticeAdapter(Context c, int layout, ArrayList<NoticeVO> data){
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
            convertView = inflater.inflate(layout, parent, false);

            // 뷰 홀더 해야 한다. 안그러면 클릭할 때마다 데이터 추가된다.
            
        }

        TextView tv_num = convertView.findViewById(R.id.tv_num);
        TextView tv_title = convertView.findViewById(R.id.tv_title);
        TextView tv_date = convertView.findViewById(R.id.tv_date);

        tv_num.setText(data.get(position).getN_seq()+"");
        tv_title.setText(data.get(position).getN_title());
        tv_date.setText(data.get(position).getN_date());

        return convertView;
    }
}
