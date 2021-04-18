package com.threego.loginactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

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
            // 뷰 홀더 해야 한다. 안그러면 클릭할 때마다 데이터 추가된다.
        ViewHolder holder;
        if (convertView==null){
            convertView = inflater.inflate(layout,parent,false);
            holder = new ViewHolder();
            holder.tv_shop = convertView.findViewById(R.id.tv_shop);    // 가게이름
            holder.tv_price = convertView.findViewById(R.id.tv_allprice); // 가격
            holder.tv_food = convertView.findViewById(R.id.tv_food); // 음식명
            holder.tv_shoplocation = convertView.findViewById(R.id.tv_shoplocation); // 가게주소
            holder.tv_shoptime = convertView.findViewById(R.id.tv_shoptime); // 가게도착시간
            holder.tv_house = convertView.findViewById(R.id.tv_house); // 가게주소 - 고객주소 거리
            holder.tv_address = convertView.findViewById(R.id.tv_c_address); // 고객주소
            holder.tv_call = convertView.findViewById(R.id.tv_call); // 콜비
            holder.tv_hometime = convertView.findViewById(R.id.tv_hometime); // 고객도착시간

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tv_shop.setText(data.get(position).getDl_shop());
        holder.tv_house.setText(data.get(position).getDl_distoadd());
        holder.tv_address.setText(data.get(position).getDl_address());
        holder.tv_price.setText(data.get(position).getDl_price()+"");
        holder.tv_call.setText(data.get(position).getDl_call()+"");
        holder.tv_food.setText(data.get(position).getDl_food());
        holder.tv_hometime.setText(data.get(position).getDl_dltime());
        holder.tv_shoptime.setText(data.get(position).getDl_cooktime());
        holder.tv_shoplocation.setText(data.get(position).getDl_s_lati()+""+data.get(position).getDl_s_longi());

        return convertView;
    }

    // viewholder 설정
    public class ViewHolder{
        TextView tv_shop, tv_house,tv_address, tv_price,tv_call,tv_food,tv_hometime,tv_shoptime, tv_shoplocation;
    }
}
