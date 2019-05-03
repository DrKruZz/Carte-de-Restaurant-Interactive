package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Panier extends AppCompatActivity {

    private ArrayList<String> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        ListView lv = findViewById(R.id.lv_panier);

        Intent i = getIntent();

        ArrayList<List<String>> panier = (ArrayList<List<String>>)i.getSerializableExtra("panier");

        MyArrayAdapter arrayAdapter = new MyArrayAdapter(this,android.R.layout.activity_list_item,panier);
        lv.setAdapter(arrayAdapter);
    }

    public void cross(View Sender){
        panier.clear();
        Intent intent = new Intent(Panier.this,MenuActivity.class);
        startActivity(intent);
    }

    private class MyArrayAdapter extends ArrayAdapter<List<String>> {

        public MyArrayAdapter(Context context, int textViewResourceId, ArrayList<List<String>> objects){
            super(context,textViewResourceId,objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.row_custom_list_view_panier, parent, false);

            FloatingActionButton moins = rowView.findViewById(R.id.moins);
            FloatingActionButton plus = rowView.findViewById(R.id.plus);
            TextView name = rowView.findViewById(R.id.name_panier);
            TextView count= rowView.findViewById(R.id.count_panier);
            TextView prix =rowView.findViewById(R.id.prix_panier);

            final List<String> tmp = getItem(position);
            moins.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    TextView q = (TextView) findViewById(R.id.count_panier);
                    int val = 0;
                    try {
                        val = Integer.parseInt(q.getText().toString());
                    } catch (Exception e ) {}
                    val--;
                    if( val < 0)
                        val = 0;
                    q.setText(""+val+"");
                    tmp.set(1,""+val+"");
                }
            });

            plus.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    TextView q = (TextView) findViewById(R.id.count_panier);
                    int val = 0;
                    try {
                        val = Integer.parseInt(q.getText().toString());
                    } catch (Exception e ) {}
                    val++;
                    q.setText(""+val+"");
                    tmp.set(1,""+val+"");
                }
            });

            name.setText(tmp.get(0));
            count.setText(tmp.get(1));
            prix.setText(tmp.get(2)+"€");

            if(convertView != null)
                rowView = convertView;

            return rowView;
        }

    }
}
