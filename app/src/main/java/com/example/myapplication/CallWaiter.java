package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class CallWaiter extends AppCompatActivity {

    private Button yesButton;
    private Button noButton;

    private int request_Code = 1;

    private ArrayList<List<String>> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_waiter);

        Intent intent = getIntent();

        panier = (ArrayList<List<String>>)intent.getSerializableExtra("panier");

        yesButton = (Button) findViewById(R.id.btnDialogYes);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWaiterComingActivity();
            }
        });

        noButton = (Button) findViewById(R.id.btnDialogNo);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("panier",panier);
        setResult(RESULT_OK,data);
        finish();
    }

    public void openWaiterComingActivity(){
        Intent intent = new Intent(this, WaiterComingActivity.class);
        intent.putExtra("panier",panier);
        startActivityForResult(intent,request_Code);
    }

    public void openMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
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
