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

    private int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_pop_up);

        Intent i = getIntent();

        String text = i.getStringExtra("name");
        this.panier = (ArrayList<List<String>>) i.getSerializableExtra("panier");

        TextView tv = (TextView) findViewById(R.id.add_plat);
        tv.setText(text);

        final boolean menu = i.getExtras().getBoolean("menu");

        new Timer().schedule(new TimerTask(){
            public void run() {
                Intent intent;
                if( menu ){
                    intent = new Intent(PopUpActivity.this, MenuActivity.class);
                    intent.putExtra("panier",panier);
                }else {
                    intent = new Intent(PopUpActivity.this, MainActivity.class);
                }
                startActivityForResult(intent,request_Code);
                finish();
            }
        }, 2000 );
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
