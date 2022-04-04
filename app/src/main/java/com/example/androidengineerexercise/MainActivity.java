package com.example.androidengineerexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        createAccountBtn.setOnClickListener(
                (__)->{
                    Intent navigateIntent = new Intent();
                    navigateIntent.setClass(MainActivity.this, LoginActivity.class);
                    startActivity(navigateIntent);
                }
        );
    }

    private void initView() {
        createAccountBtn = findViewById(R.id.btn_create_account);
    }


}