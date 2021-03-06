package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FiltreActivity extends AppCompatActivity {

    private boolean entree;
    private boolean plat;
    private boolean dessert;
    private boolean vege;
    private boolean carni;

    private int request_Code = 1;

    private ArrayList<List<String>> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre);
        this.entree=false;
        this.plat = false;
        this.dessert = false;
        this.vege = false;
        this.carni = false;
        Intent intent = getIntent();
        panier = (ArrayList<List<String>>)intent.getSerializableExtra("panier");
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("panier",panier);
        setResult(RESULT_OK,data);
        finish();
    }

    public void entree(View Sender) {
        this.entree = !(entree);
    }

    public void plat(View Sender) {
        this.plat = !(plat);
    }

    public void dessert(View Sender) {
        this.dessert = !(dessert);
    }

    public void vege(View Sender) {
        this.vege = !(vege);
    }

    public void carni(View Sender) {
        this.carni = !(carni);
    }

    public void send(View Sender) {
        Intent intent = new Intent(FiltreActivity.this,FiltreResultActivity.class);
        System.out.println("entree"+entree);
        System.out.println("plat"+plat);
        System.out.println("dessert"+dessert);
        System.out.println("vege"+vege);
        System.out.println("carni"+carni);
        intent.putExtra("entree",entree);
        intent.putExtra("plat",plat);
        intent.putExtra("dessert",dessert);
        intent.putExtra("vege",vege);
        intent.putExtra("carni",carni);

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
