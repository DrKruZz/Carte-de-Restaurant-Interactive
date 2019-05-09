package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Help extends AppCompatActivity {

    private List<List<String>> list = new ArrayList<>();
    private ArrayList<List<String>> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Intent intent = getIntent();

        panier = (ArrayList<List<String>>)intent.getSerializableExtra("panier");
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("panier", panier);
        setResult(RESULT_OK, data);
        finish();
    }
}
