package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class WaiterComingActivity extends AppCompatActivity {


    private static int TIME_OUT = 3000;
    private List<List<String>> list = new ArrayList<>();
    private ArrayList<List<String>> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_coming);
        final View myLayout = findViewById(R.id.lv_menu);

        Intent intent = getIntent();

        panier = (ArrayList<List<String>>)intent.getSerializableExtra("panier");
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(WaiterComingActivity.this, MenuActivity.class);
                intent.putExtra("panier",panier);
                startActivity(intent);
                finish();
            }
        },TIME_OUT);
    }
}
