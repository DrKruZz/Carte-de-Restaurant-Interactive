package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WaiterComingActivity extends AppCompatActivity {

    private static int TIME_OUT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_coming);
        final View myLayout = findViewById(R.id.lv_menu);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(WaiterComingActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        },TIME_OUT);
    }
}
