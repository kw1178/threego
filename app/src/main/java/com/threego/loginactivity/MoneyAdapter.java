package com.threego.loginactivity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MoneyAdapter extends BaseAdapter {
    private Context c;
    private int layout;
    private ArrayList<DeliveryVO> data;
    private LayoutInflater inflater;

    public MoneyAdapter(Context c, int layout, ArrayList<DeliveryVO> data){
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
        TextView tv_m_date = convertView.findViewById(R.id.tv_m_date);
        TextView tv_m_money = convertView.findViewById(R.id.tv_m_money);
        TextView tv_m_status = convertView.findViewById(R.id.tv_m_status);

        tv_m_date.setText(data.get(position).getDl_date());
        tv_m_money.setText(data.get(position).getDl_call()+"");
        tv_m_status.setText("콜 수익");

        return convertView;
    }
}
