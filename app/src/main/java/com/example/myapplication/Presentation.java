package com.example.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Presentation extends AppCompatActivity {

    ArrayList<String> selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        Intent i = getIntent();
        selectedItem = i.getStringArrayListExtra("plat");

        TextView name = findViewById(R.id.nom_plat);
        ImageView img = findViewById(R.id.img_plat);
        TextView desc = findViewById(R.id.desc_plat);

        name.setText(selectedItem.get(0));
        img.setImageResource(Integer.parseInt(selectedItem.get(1)));
        desc.setText(selectedItem.get(3));
    }

    public void back(View Sender){
        Intent intent = new Intent(Presentation.this,MenuActivity.class);
        startActivity(intent);
    }

    public void add_panier(View Sender){
        //Intent intent = new Intent(Presentation.this,popup_add_panier.class);
        //intent.putExtra("panier",selectedItem);
        //startActivity(intent);
    }
}
