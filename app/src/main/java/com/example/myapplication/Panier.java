package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Panier extends AppCompatActivity {

    private ArrayList<List<String>>  panier;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

       this.lv = findViewById(R.id.lv_panier);

        Intent i = getIntent();

        this.panier = (ArrayList<List<String>>)i.getSerializableExtra("panier");

        MyArrayAdapter arrayAdapter = new MyArrayAdapter(this,android.R.layout.activity_list_item,panier);
        lv.setAdapter(arrayAdapter);
        lv.getChildAt(lv.getFirstVisiblePosition());
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Panier.this,MenuActivity.class);
        intent.putExtra("panier",panier);
        startActivity(intent);
    }

    public void cross(View Sender){
        panier.clear();
        Intent intent = new Intent(Panier.this,MenuActivity.class);
        intent.putExtra("panier",panier);
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
            FloatingActionButton supp_plat = (FloatingActionButton) rowView.findViewById(R.id.supp_plat);
            TextView name = rowView.findViewById(R.id.name_panier);
            TextView count= rowView.findViewById(R.id.count_panier);
            TextView prix =rowView.findViewById(R.id.prix_panier);
            TextView total = findViewById(R.id.total);

            final List<String> tmp = getItem(position);
            final int row_position = position;

            moins.setOnClickListener(new FloatingActionButton.OnClickListener(){
                @Override
                public void onClick(View view) {

                    View v = lv.getChildAt(row_position - lv.getFirstVisiblePosition());
                    TextView nb = (TextView) v.findViewById(R.id.count_panier);
                    TextView p = (TextView) v.findViewById(R.id.prix_panier);

                    int val = 0;
                    try {
                        val = Integer.parseInt(tmp.get(1));
                    } catch (Exception e ) {}
                    val--;
                    if( val < 1)
                        val = 0;
                    nb.setText(""+val+"");
                    int tot = val * Integer.parseInt(tmp.get(2));
                    p.setText(tot+"€");
                    tmp.set(1,""+val+"");
                    notifyDataSetChanged();
                }
            });

            plus.setOnClickListener(new FloatingActionButton.OnClickListener(){

                @Override
                public void onClick(View view) {

                    View v = lv.getChildAt(row_position - lv.getFirstVisiblePosition());
                    TextView nb = (TextView) v.findViewById(R.id.count_panier);
                    TextView p = (TextView) v.findViewById(R.id.prix_panier);


                    int val = 0;
                    try {
                        val = Integer.parseInt(tmp.get(1));
                    } catch (Exception e ) {}
                    val++;
                    nb.setText(""+val+"");
                    int tot = val * Integer.parseInt(tmp.get(2));
                    p.setText(tot+"€");
                    tmp.set(1,""+val+"");
                    notifyDataSetChanged();
                }
            });

            supp_plat.setOnClickListener(new FloatingActionButton.OnClickListener(){

                @Override
                public void onClick(View view) {
                    panier.remove(tmp);
                    notifyDataSetChanged();
                    System.out.println("__________________");
                    System.out.println(panier);
                    System.out.println("__________________");
                }
            });
            name.setText(tmp.get(0));
            count.setText(tmp.get(1));
            float tot = Float.parseFloat(tmp.get(1)) * Float.parseFloat(tmp.get(2));
            prix.setText(tot+"€");

            tot = 0;

            for(List<String> l : panier){
                tot += Float.parseFloat(l.get(1)) * Float.parseFloat(l.get(2));
            }

            total.setText("Total : " + tot + "€");

            if(convertView != null)
                rowView = convertView;

            return rowView;
        }

    }
}
