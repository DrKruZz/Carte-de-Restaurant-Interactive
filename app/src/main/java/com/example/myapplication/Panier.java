package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Panier extends AppCompatActivity {

    private ArrayList<String> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
    }

    public void cross(View Sender){
        panier.clear();
        Intent intent = new Intent(Panier.this,MenuActivity.class);
        startActivity(intent);
    }
}
