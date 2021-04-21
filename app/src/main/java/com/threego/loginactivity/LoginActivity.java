package com.threego.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button btn_join, btn_login;

    StringRequest stringRequest, stringRequest2;
    RequestQueue requestQueue, requestQueue2;

    EditText ed_id, ed_pw;
    Fragment_new fragment_new = new Fragment_new();
    TextView tv_new3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_join = findViewById(R.id.btn_join);
        btn_login = findViewById(R.id.btn_login);

        ed_id = findViewById(R.id.ed_id);
        ed_pw = findViewById(R.id.ed_pw);

        tv_new3 = findViewById(R.id.tv_new3);

        // 회원가입
        btn_join.setOnClickListener(new View.OnClickListener() {    // startActivityForResult 해야한다.
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        // 로그인 통신
        String url = "http://222.102.104.230:8081/threego/login.do";

        requestQueue2 = Volley.newRequestQueue(getApplicationContext());

        stringRequest2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.v("카카카",response);
                    if (response.equals("1")){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("r_id",ed_id.getText().toString());
                        startActivity(intent);
                        finish();
                        Log.v("카카카",ed_id.getText().toString());
                        Toast.makeText(getApplicationContext(),ed_id.getText().toString()+"환영합니다.",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"다시 로그인 해주세요.",Toast.LENGTH_SHORT).show();
                        ed_id.setText("");
                        ed_pw.setText("");
                    }
                }catch (Exception e){

                }
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
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

                return temp;
            }
        };


        // 로그인
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue2.add(stringRequest2);
            }
        });

    }
}