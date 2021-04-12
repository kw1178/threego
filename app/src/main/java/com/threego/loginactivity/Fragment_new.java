package com.threego.loginactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment_new extends Fragment {
    ArrayList<DeliveryVO> delivery = new ArrayList<>();
    ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        lv = view.findViewById(R.id.list_new);

        // DB 연동 필요
        delivery.add(new DeliveryVO("교촌치킨","3.0km","스마트미디어인재개발원","광주 교촌치킨 충장점","허니레드콤보",20000,2000));
        delivery.add(new DeliveryVO("굽네치킨","1.0km","광주 금남로 ","광주 굽네치킨 충장점","고추바사삭",22000,2200));
        delivery.add(new DeliveryVO("네네치킨","4.0km","광주 금남로 ","광주 네네치킨 충장점","파닭",24000,2400));

        DeliveryAdapter adapter = new DeliveryAdapter(view.getContext(),R.layout.delivery_list,delivery);
        lv.setAdapter(adapter);

        // 리스트 선택 시 MapActivity로 넘어가기, startActivityForResult 필요함
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i =0; i<delivery.size();i++){
                    if (position==i){
                        // fragment에서는 this 대신 getActivity 를 사용한다.
                        Intent intent = new Intent(getActivity(), MapActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });
        return view;
    }
}