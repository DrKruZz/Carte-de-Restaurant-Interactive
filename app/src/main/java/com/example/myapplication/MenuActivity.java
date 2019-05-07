package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
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

public class MenuActivity extends AppCompatActivity {

    private List<List<String>> list = new ArrayList<>();
    private ArrayList<List<String>> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Context c = this.getApplicationContext();

        ListView lv = findViewById(R.id.lv_menu);

        Intent intent = getIntent();

        panier = (ArrayList<List<String>>)intent.getSerializableExtra("panier");

        if(panier == null){
            panier = new ArrayList<>();
        }

        String [] noms_plat = c.getResources().getStringArray(R.array.plats_array);
        String [] prix_plat = c.getResources().getStringArray(R.array.prix_array);

        for (int i = 0; i < noms_plat.length; i++){
            List tmp = new ArrayList<String>();
            tmp.add(noms_plat[i]);
            tmp.add(prix_plat[i]);
            list.add(tmp);
        }

        MyArrayAdapter arrayAdapter = new MyArrayAdapter(this,android.R.layout.activity_list_item,list);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> selectedItem = (ArrayList<String>) parent.getItemAtPosition(position);

                Intent intent = new Intent(MenuActivity.this,Presentation.class);
                intent.putExtra("name_item",selectedItem.get(0));
                intent.putExtra("panier",panier);
                startActivity(intent);
            }
        });
    }


    public void cross(View Sender){
        panier.clear();
        Intent intent = new Intent(MenuActivity.this,MenuActivity.class);
        startActivity(intent);
    }

    public void call(View Sender){
        Intent intent = new Intent(MenuActivity.this,CallWaiter.class);
        startActivity(intent);
    }
    public void search(View Sender){
        Intent intent = new Intent(MenuActivity.this,SearchActivity.class);
        intent.putExtra("panier",panier);
        startActivity(intent);
    }
    public void help(View Sender){
        Intent intent = new Intent(MenuActivity.this,Help.class);
        startActivity(intent);
    }

    public void panier(View Sender){
        Intent intent = new Intent(MenuActivity.this,Panier.class);
        intent.putExtra("panier",panier);
        startActivity(intent);
    }

    public void filtre(View Sender) {
        Intent intent = new Intent(MenuActivity.this,FiltreActivity.class);
        intent.putExtra("panier",panier);
        startActivity(intent);
    }
    private class MyArrayAdapter extends ArrayAdapter<List<String>>{

        public MyArrayAdapter(Context context,int textViewResourceId,List<List<String>> objects){
            super(context,textViewResourceId,objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.row_custom_list_view_menu, parent, false);

            TextView name = rowView.findViewById(R.id.name);
            TextView prix =rowView.findViewById(R.id.prix);

            List<String> tmp = getItem(position);
            name.setText(tmp.get(0));
            prix.setText(tmp.get(1)+"€");

           if(convertView != null)
                rowView = convertView;

            return rowView;
        }
    }
}
