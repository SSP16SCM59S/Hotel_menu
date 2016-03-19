package com.cs442.shash5259.assignment2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database extends AppCompatActivity implements View.OnClickListener {
    public static Button b2, b3, b4;
    public static TextView t1, t2, t3;
    private List<Order> myOrder = new ArrayList<>();
    DataHandler db;
    DataHandler db1;
    DataHandler db2;
    String a3 = null;
    String a4 = null;
    String a5 = null;
    String a6 = null;
    List<String> elephantList;
    //Order my[] = new Order[10];
    int i =0;
//    ListView lv = (ListView)findViewById(R.id.listView2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Intent myintent = getIntent();
        b2 = (Button) findViewById(R.id.button5);
        //b3 = (Button)findViewById(R.id.button6);
        b4 = (Button) findViewById(R.id.button8);
        //t1 = (TextView)findViewById(R.id.textView13);
        //t2 = (TextView)findViewById(R.id.textView14);
        //t3 = (TextView)findViewById(R.id.textView15);
        //t1.setMovementMethod(new ScrollingMovementMethod());
        // t2.setMovementMethod(new ScrollingMovementMethod());
        // t3.setMovementMethod(new ScrollingMovementMethod());

        b2.setOnClickListener(this);
        //b3.setOnClickListener(this);
        b4.setOnClickListener(this);


        // int i=0;

        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();


        db1 = new DataHandler(getBaseContext());
        db1.open();
        Cursor cs = db1.returnData();

        if (cs.moveToFirst()) {
            do {
                a3 = cs.getString(0);
                a4 = cs.getString(1);
                a5 = cs.getString(2);


                elephantList = Arrays.asList(a4.split(","));
                //a6 = cs.getString(3);
                //Toast.makeText(getBaseContext(),"Total Cost: "+a5+"\nname: "+a4+"\nid = "+a3+" \nCount = "+cs.getCount(),Toast.LENGTH_SHORT).show();


              /*  builder.append(a3 + "\n");
                for (int i = 0; i < elephantList.size() - 1; i++) {
                    builder.append("\n");
                }
                t1.setText(builder.toString());


                for (String details : elephantList) {
                    builder1.append(details + "\n");
                }

                //builder1.append(a4 + " \n");
                t2.setText(builder1.toString());


                builder2.append(a5 + " \n");
                for (int i = 0; i < elephantList.size() - 1; i++) {
                    builder2.append("\n");
                }
                t3.setText(builder2.toString());*/
                populateOrderList();


            } while (cs.moveToNext());

        } /*else {
            builder.append("1" + " \n");
            t1.setText(builder.toString());

            builder1.append("Empty" + " \n");
            t2.setText(builder1.toString());

            builder2.append("0$" + " \n");
            t3.setText(builder2.toString());
        }*/

        db1.close();


        populateListView();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button5:
                Intent i = new Intent(Database.this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.button8:
                db2 = new DataHandler(getBaseContext());
                db2.open();
                Cursor cs1 = db2.DeleteData();
                Toast.makeText(getBaseContext(), " Count = " + cs1.getCount(), Toast.LENGTH_LONG).show();
                db2.close();
                break;
        }

    }

    private void populateOrderList() {
        myOrder.add(new Order(a3, a4, a5));



      /*  my[i] = new Order();
        my[i].id = a3;
        my[i].items=a4;
        my[i].cost=a5;
        i++;*/


    }

    private void populateListView() {
        final ArrayAdapter<Order> adapter = new MyListAdapter();
        final ListView lv = (ListView) findViewById(R.id.listView2);

        //list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, strArr));
        lv.setAdapter(adapter);

    }

    public class MyListAdapter extends ArrayAdapter<Order> {
        public MyListAdapter() {
            super(Database.this, R.layout.ls, myOrder);
        }

        @Override
        public View getView(int position, final View convertView, ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.ls, parent, false);
            }
            //find item to work with
            Order currentOrder = myOrder.get(position);
            //Order d = getItem(position);

            TextView t1 = (TextView) itemView.findViewById(R.id.textView);
            t1.setText(currentOrder.getId());
            //t1.setText(d.id);

            TextView t2 = (TextView) itemView.findViewById(R.id.textView3);
            t2.setText(currentOrder.getItems());
            //t2.setText(d.items);

            TextView t3 = (TextView) itemView.findViewById(R.id.textView2);
            t3.setText(currentOrder.getCost());
            //t3.setText(d.cost);
            //lv.setBackgroundColor(Color.WHITE);

            return itemView;
            //return super.getView(position,convertView,parent);
        }

    }
}