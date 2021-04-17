package com.threego.loginactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeliveryAdapter extends BaseAdapter {
    private Context c;
    private int layout;
    private ArrayList<DeliveryVO> data;
    private LayoutInflater inflater;

    public DeliveryAdapter(Context c, int layout, ArrayList<DeliveryVO> data){
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
        TextView tv_shop = convertView.findViewById(R.id.tv_shop);
        TextView tv_house = convertView.findViewById(R.id.tv_house);
        TextView tv_address = convertView.findViewById(R.id.tv_address);
        TextView tv_shoplocation = convertView.findViewById(R.id.tv_shoplocation);
        TextView tv_price = convertView.findViewById(R.id.tv_allprice);
        TextView tv_call = convertView.findViewById(R.id.tv_call);
        TextView tv_food = convertView.findViewById(R.id.tv_food);
        TextView tv_hometime = convertView.findViewById(R.id.tv_hometime);
        TextView tv_shoptime = convertView.findViewById(R.id.tv_shoptime);


        tv_shop.setText(data.get(position).getDl_shop());
        tv_house.setText(data.get(position).getDl_distoadd());
        tv_address.setText(data.get(position).getDl_address());
        tv_price.setText(data.get(position).getDl_price()+"");
        tv_call.setText(data.get(position).getDl_call()+"");
        tv_food.setText(data.get(position).getDl_food());
        tv_hometime.setText(data.get(position).getDl_dltime());
        tv_shoptime.setText(data.get(position).getDl_cooktime());

        return convertView;
    }
}
