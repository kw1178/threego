package com.threego.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OutputActivity extends AppCompatActivity {
    TextView tv_all_money;
    Button btn_output;
    EditText ed_number, ed_bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        tv_all_money = findViewById(R.id.tv_all_money);
        btn_output = findViewById(R.id.btn_output);
        ed_bank = findViewById(R.id.ed_bank);
        ed_number = findViewById(R.id.ed_number);

        Intent intent = getIntent();
        String all_m = intent.getExtras().getString("allmoney");

        tv_all_money.setText(all_m+"원");

        btn_output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),ed_bank.getText().toString()+", "+ed_number.getText().toString()+"으로 "+tv_all_money.getText().toString()+"출금되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}