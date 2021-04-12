package com.threego.loginactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Fragment_notice extends Fragment {
    ArrayList<NoticeVO> notice = new ArrayList<>();
    TextView tv_n_title, tv_n_date, tv_contents;
    ListView listView;
    ImageView iv_close;
    ConstraintLayout frame_contents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        tv_n_title = view.findViewById(R.id.tv_n_title);
        tv_n_date = view.findViewById(R.id.tv_n_date);
        tv_contents = view.findViewById(R.id.tv_contents);

        listView = view.findViewById(R.id.list);
        iv_close = view.findViewById(R.id.iv_close);

        frame_contents = view.findViewById(R.id.frame_contents);

        // DB 연동 필요
        notice.add(new NoticeVO("안녕하세요.","2021-04-11","만들었다아아아아앙아아아아ㅏ아아아아ㅏ아아"));

        // 값 변경
        tv_n_title.setText(notice.get(0).getN_title());
        tv_n_date.setText(notice.get(0).getN_date());
        tv_contents.setText(notice.get(0).getN_content());

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}