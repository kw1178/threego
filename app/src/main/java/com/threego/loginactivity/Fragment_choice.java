package com.threego.loginactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class Fragment_choice extends Fragment {
    ArrayList<DeliveryVO> delivery = new ArrayList<>();
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice, container, false);

        lv = view.findViewById(R.id.list_choice);


        // DB 연동 필요
        delivery.add(new DeliveryVO("굽네치킨","1.0km","광주 금남로 ","광주 굽네치킨 충장점","고추바사삭",22000,2200));

        DeliveryAdapter adapter = new DeliveryAdapter(view.getContext(),R.layout.delivery_list,delivery);
        lv.setAdapter(adapter);
        return view;
    }
}