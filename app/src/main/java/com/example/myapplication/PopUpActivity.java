package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PopUpActivity extends AppCompatActivity {

    private ArrayList<List<String>> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_pop_up);

        Intent i = getIntent();

        String name = i.getStringExtra("name");
        this.panier = (ArrayList<List<String>>) i.getSerializableExtra("panier");

        TextView tv = (TextView) findViewById(R.id.add_plat);
        tv.setText("Ajout de : \n" + name);

        new Timer().schedule(new TimerTask(){
            public void run() {
                Intent intent = new Intent(PopUpActivity.this, MenuActivity.class);
                intent.putExtra("panier",panier);
                startActivity(intent);
                finish();
            }
        }, 2000 );
    }
}
