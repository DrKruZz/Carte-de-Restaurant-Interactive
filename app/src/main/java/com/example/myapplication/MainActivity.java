package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<List<String>> panier = new ArrayList<>();
    private int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switch_screen(View Sender){
        Intent intent = new Intent(MainActivity.this,MenuActivity.class);
        intent.putExtra("panier",panier);
        startActivityForResult(intent,request_Code);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                this.panier = (ArrayList<List<String>>)data.getSerializableExtra("panier");
            }
        }
    }
}
