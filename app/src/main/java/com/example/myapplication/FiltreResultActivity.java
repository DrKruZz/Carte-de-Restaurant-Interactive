package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FiltreResultActivity extends AppCompatActivity {


    ArrayAdapter mAdapter;
    ListView lv;

    private int request_Code = 1;

    private List<String> listFiltre = new ArrayList<>();
    private ArrayList<List<String>> panier;
    private boolean entree;
    private boolean plat;
    private boolean dessert;
    private boolean vege;
    private boolean carni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre_result);
        Context c = this.getApplicationContext();
        Intent intent = getIntent();
        setListFiltre(c,intent);
        lv = (ListView) findViewById(R.id.list_filtre);
        System.out.println(listFiltre);

        this.panier = (ArrayList<List<String>>)intent.getSerializableExtra("panier");

        mAdapter = new ArrayAdapter(FiltreResultActivity.this,
                android.R.layout.simple_list_item_1,
                listFiltre);
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(SearchActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FiltreResultActivity.this,Presentation.class);
                intent.putExtra("name_item",adapterView.getItemAtPosition(i).toString());
                intent.putExtra("panier",panier);
                startActivityForResult(intent,request_Code);
            }
        });

    }

    public void setListFiltre(Context c, Intent intent) {
        String [] noms_plat = c.getResources().getStringArray(R.array.plats_array);

        entree = intent.getExtras().getBoolean("entree");
        plat = intent.getExtras().getBoolean("plat");
        dessert = intent.getExtras().getBoolean("dessert");
        vege = intent.getExtras().getBoolean("vege");
        carni = intent.getExtras().getBoolean("carni");


        if (entree) {
            String[] isEntree = c.getResources().getStringArray(R.array.isEntree);
            for (int i = 0; i < noms_plat.length ; i++) {
                if (isEntree[i].equals("1"))
                    listFiltre.add(noms_plat[i]);
            }
        }

        if (plat) {
            String[] isPlat = c.getResources().getStringArray(R.array.isPlat);
            for (int i = 0; i < noms_plat.length ; i++) {
                if (isPlat[i].equals("1"))
                    listFiltre.add(noms_plat[i]);
            }

        }

        if (dessert) {
            String[] isDessert = c.getResources().getStringArray(R.array.isDessert);
            for (int i = 0; i < noms_plat.length ; i++) {
                if (isDessert[i].equals("1"))
                    listFiltre.add(noms_plat[i]);
            }

        }

        if (listFiltre.isEmpty()) {
            for (int i = 0 ; i < noms_plat.length ; i++)
                listFiltre.add(noms_plat[i]);
        }


        List<String> LVege = new ArrayList<>();
        if (vege) {
            String[] isVege = c.getResources().getStringArray(R.array.isVege);
            for (int i = 0; i < noms_plat.length ; i++) {
                if (isVege[i].equals("1"))
                    LVege.add(noms_plat[i]);
            }

            listFiltre.retainAll(LVege);

        }

        List<String> LCarni = new ArrayList<>();
        if (carni) {
            String[] isCarni = c.getResources().getStringArray(R.array.isCarni);
            for (int i = 0; i < noms_plat.length ; i++) {
                if (isCarni[i].equals("1"))
                    LCarni.add(noms_plat[i]);
            }
            listFiltre.retainAll(LCarni);

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                this.panier = (ArrayList<List<String>>)data.getSerializableExtra("panier");
            }
        }
    }
}
