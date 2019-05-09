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

    private int request_Code = 1;

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
                startActivityForResult(intent,request_Code);
                finish();
            }
        },TIME_OUT);
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("panier",panier);
        setResult(RESULT_OK,data);
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                this.panier = (ArrayList<List<String>>)data.getSerializableExtra("panier");
            }
        }
    }
}
