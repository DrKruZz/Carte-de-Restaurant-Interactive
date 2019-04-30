package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Presentation extends AppCompatActivity {

    int id_item;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        this.context = getApplicationContext();

        Intent i_par = getIntent();

        //this.id_item = i_par.getIntExtra("item_pos",-1);
        String n_plat = i_par.getStringExtra("name_item");

        Intent i = getIntent();

        TextView name = findViewById(R.id.nom_plat);
        ImageView img = findViewById(R.id.img_plat);
        TextView desc = findViewById(R.id.desc_plat);
        TextView prix = findViewById(R.id.prix_plat);


        name.setText(n_plat);
        this.id_item = getPos(context,n_plat);
        Field f = null;
        try {
            f = R.drawable.class.getDeclaredField(getImg(context));
            img.setImageResource(f.getInt(f));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        desc.setText(getDesc(context));
        prix.setText(getPrix(context)+"€");
    }

    public int getPos(Context c, String S) {
        String [] ar = c.getResources().getStringArray(R.array.plats_array);
        for (int i = 0 ; i < ar.length ; i++) {
            if (ar[i].equals(S))
                return i;
        }
        return -1;
    }

    public String getDesc(Context c) {
        String[] ar = c.getResources().getStringArray(R.array.desc_array);
        return ar[this.id_item];
    }

    public String getPrix(Context c) {
        String[] ar = c.getResources().getStringArray(R.array.prix_array);
        return ar[this.id_item];
    }

    public String getImg(Context c) {
        String [] ar = c.getResources().getStringArray(R.array.img_array);
        return ar[this.id_item];
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

    public void moinsClicked(View view) {
        TextView q = (TextView) findViewById(R.id.id_quantite);
        int tmp = 0;
        try {
            tmp = Integer.parseInt(q.getText().toString());
        } catch (Exception e ) {}
        tmp--;
        if( tmp < 0)
            tmp = 0;
        q.setText(""+tmp+"");
    }

    public void plusClicked(View view) {
        TextView q = (TextView) findViewById(R.id.id_quantite);
        int tmp = 0;
        try {
            tmp = Integer.parseInt(q.getText().toString());
        } catch (Exception e ) {}
        tmp++;
        q.setText(""+tmp+"");
    }


}
