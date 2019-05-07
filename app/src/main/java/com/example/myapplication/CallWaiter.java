package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CallWaiter extends AppCompatActivity {

    private Button yesButton;
    private Button noButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_waiter);

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

    public void openWaiterComingActivity(){
        Intent intent = new Intent(this, WaiterComingActivity.class);
        startActivity(intent);
    }

    public void openMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
