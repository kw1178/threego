package com.threego.loginactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {
    Button btn_join, btn_cancle;
    EditText ed_id, ed_pw, ed_box, ed_name, ed_age, ed_phone, ed_gender;
    StringRequest stringRequest;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btn_join = findViewById(R.id.btn_join);
        btn_cancle = findViewById(R.id.btn_cancle);

        ed_id = findViewById(R.id.ed_id);
        ed_pw = findViewById(R.id.ed_pass);
        ed_box = findViewById(R.id.ed_box);
        ed_name = findViewById(R.id.ed_name);
        ed_age = findViewById(R.id.ed_age);
        ed_phone = findViewById(R.id.ed_phone);
        ed_gender = findViewById(R.id.ed_gender);

        // 회원가입 통신
        String url = "http://222.102.104.230:8081/threego/join.do";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("test",response);
                if (response.equals("")){
                    Toast.makeText(getApplicationContext(),"회원가입 성공 ^^", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"회원가입 실패 ㅜㅜ", Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> temp = new HashMap<>();
                temp.put("r_id",ed_id.getText().toString());
                temp.put("r_pw",ed_pw.getText().toString());
                temp.put("r_age",ed_age.getText().toString());
                temp.put("r_name",ed_name.getText().toString());
                temp.put("r_box",ed_box.getText().toString());
                temp.put("r_gender",ed_gender.getText().toString());
                temp.put("r_phone",ed_phone.getText().toString());
                return temp;
            }
        };


        // 회원가입
        btn_join.setOnClickListener(new View.OnClickListener() {    // 정보전달해야된다.
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest);
                onBackPressed();
            }
        });
        
        // 취소
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}