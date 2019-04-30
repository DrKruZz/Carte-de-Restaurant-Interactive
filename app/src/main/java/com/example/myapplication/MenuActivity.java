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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

   /* private String[] menu = new String[]{"Frite","Coca"};
    private Integer[] all_img = new Integer[]{R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground};
    private String[] all_prix = new String[]{"19","37"};
    private String[] descripion = new String[]{"Petit morceau allongé de pomme de terre frite.",
            "Boisson gazeuse à base de coca et de cola."};*/
    private List<List<String>> list = new ArrayList<>();
    private ArrayList<List<String>> panier = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Context c = this.getApplicationContext();

        ListView lv = findViewById(R.id.lv_menu);
        //final TextView tv = findViewById(R.id.tv);

        String [] noms_plat = c.getResources().getStringArray(R.array.plats_array);
        String [] prix_plat = c.getResources().getStringArray(R.array.plats_array);

        for (int i = 0; i < noms_plat.length; i++){
            List tmp = new ArrayList<String>();
            tmp.add(noms_plat[i]);
            tmp.add(prix_plat[i]);
            list.add(tmp);
        }




        MyArrayAdapter arrayAdapter = new MyArrayAdapter(this,android.R.layout.activity_list_item,list);
        //ArrayAdapter arrayAdapter = new ArrayAdapter(MenuActivity.this, android.R.layout.activity_list_item,list);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> selectedItem = (ArrayList<String>) parent.getItemAtPosition(position);

                Intent intent = new Intent(MenuActivity.this,Presentation.class);
                intent.putExtra("panier",panier);
                intent.putExtra("plat",selectedItem);
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
    private class MyArrayAdapter extends ArrayAdapter<List<String>>{

        public MyArrayAdapter(Context context,int textViewResourceId,List<List<String>> objects){
            super(context,textViewResourceId,objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.row_custom_list_view_menu, parent, false);

            TextView name = rowView.findViewById(R.id.name);
            ImageView img= rowView.findViewById(R.id.img);
            TextView prix =rowView.findViewById(R.id.prix);

            List<String> tmp = getItem(position);
            name.setText(tmp.get(0));
            //img.setImageResource(Integer.parseInt(tmp.get(1)));
            prix.setText(tmp.get(1));

           /*if(convertView == null )
                img.setImageResource(Integer.parseInt(tmp.get(1)));
            else*/
           if(convertView != null)
                rowView = convertView;

            return rowView;
        }
    }
}
