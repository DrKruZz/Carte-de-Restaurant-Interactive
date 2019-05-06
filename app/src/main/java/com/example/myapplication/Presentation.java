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
import java.util.Iterator;
import java.util.List;

public class Presentation extends AppCompatActivity {

    private ArrayList<List<String>> panier;
    int id_item;
    Context context;
    private String n_plat;
    private int nb = 1;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        this.context = getApplicationContext();

        Intent i_par = getIntent();

        this.n_plat = i_par.getStringExtra("name_item");
        this.panier = (ArrayList<List<String>>)i_par.getSerializableExtra("panier");

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
        price = getPrix(context);
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

    public int ind_panier(){
        Iterator i = panier.iterator();
        List<String> p;
        while(i.hasNext()){
            p = (List<String>) i.next();
            if (p.get(0).equals(n_plat)){
                return panier.indexOf(p);
            }
        }
        return -1;
    }

    public void add_panier(View Sender){
        int ind = ind_panier();
        if(ind >= 0) {
            panier.get(ind).set(1, Integer.toString(Integer.parseInt(panier.get(ind).get(1)) + nb));
        }else{
            List<String> tmp_l = new ArrayList<>();
            tmp_l.add(n_plat);
            tmp_l.add(Integer.toString(nb));
            tmp_l.add(price);
            panier.add(tmp_l);
        }

        Intent intent = new Intent(Presentation.this,PopUpActivity.class);
        intent.putExtra("name","Ajout de : \n " + n_plat);
        intent.putExtra("panier",panier);
        intent.putExtra("menu", true);
        startActivity(intent);
    }

    public void moinsClicked(View view) {
        TextView q = (TextView) findViewById(R.id.id_quantite);
        int tmp = 0;
        try {
            tmp = Integer.parseInt(q.getText().toString());
        } catch (Exception e ) {}
        tmp--;
        if( tmp < 1)
            tmp = 0;
        this.nb = tmp;
        q.setText(""+tmp+"");
    }

    public void plusClicked(View view) {
        TextView q = (TextView) findViewById(R.id.id_quantite);
        int tmp = 0;
        try {
            tmp = Integer.parseInt(q.getText().toString());
        } catch (Exception e ) {}
        tmp++;
        this.nb = tmp;
        q.setText(""+tmp+"");
    }


}
