package com.threego.loginactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btn_join, btn_login, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_join = findViewById(R.id.btn_join);
        btn_login = findViewById(R.id.btn_login);
        btn_exit = findViewById(R.id.btn_exit);

        // 회원가입
        btn_join.setOnClickListener(new View.OnClickListener() {    // startActivityForResult 해야한다.
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        // 로그인
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 회원탈퇴
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그 띄우기
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("삭제하기");
                builder.setMessage("삭제하시겠습니까?");
                // PositiveButton ->오른쪽 버튼, NegativeButton -> 왼쪽 버튼

                // 문구, 버튼 클릭시 일어나는 일
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"다음에도 이용해주세요. ^^",Toast.LENGTH_SHORT).show();
                    }
                });
                // 할일 없으면 null
                builder.setNegativeButton("cancle", null);
                builder.show();
            }
        });
    }
}