package com.threego.loginactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Fragment_ok extends Fragment {
    ArrayList<DeliveryVO> delivery = new ArrayList<>();
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ok, container, false);

        lv = view.findViewById(R.id.list_ok);

        // DB 연동 필요
       // delivery.add(new DeliveryVO("네네치킨","4.0km","광주 금남로 ","광주 네네치킨 충장점","파닭",24000,2400));
        DeliveryAdapter adapter = new DeliveryAdapter(view.getContext(),R.layout.delivery_list,delivery);
        lv.setAdapter(adapter);

        return view;
    }
}