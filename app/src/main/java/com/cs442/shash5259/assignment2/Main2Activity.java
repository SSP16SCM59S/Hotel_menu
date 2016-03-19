package com.cs442.shash5259.assignment2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private Button btn;
    private Button btn1;
    private Button btn3;
    private int temp=0;
    String[] result = new String[25];
    String[] result1 = new String[25];
    String[] result2 = new String[25];

    int cost=0;
int i=0;
    String[] separated=new String[result.length];
    DataHandler db;
    DataHandler db1;
    DataHandler db2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView =(TextView)findViewById(R.id.textView2);
        TextView textView1 =(TextView)findViewById(R.id.textView3);
        TextView textView2 =(TextView)findViewById(R.id.textView10);
        TextView textView3 =(TextView)findViewById(R.id.textView8);

        btn1 = (Button)findViewById(R.id.button3);
       // btn3 = (Button)findViewById(R.id.button7);

        Intent myIntent = getIntent(); // this is just for example purpose
//       myIntent.getExtra("key");
        //Bundle b = getIntent().getExtras();
       // Bundle b1 = getIntent().getExtras();
        //Bundle b2 = getIntent().getExtras();
        Bundle extras = myIntent.getExtras();
        result = extras.getStringArray("key");
        result1 = extras.getStringArray("key1");
        result2 = extras.getStringArray("key2");



        int k = result.length;

        /*while(true){
            separated = result[i].split(" ");
            //separated[1] = separated[1].trim();
            cost = Integer.parseInt(separated[0]);
            i++;
            if(i==result.length)
            break;
            separated[0]=null;
            separated[1]=null;
        }*/

        for(int i=0;i<result.length;i++)
      {
          if(result[i]!=null) {
              //System.out.println("Hello " + result[i]);
               cost += Integer.parseInt(result[i]);
          }
      }

//cost = Integer.parseInt(separated[0]);
        //cost=Integer.parseInt(result1[0]);


       // lv.setAdapter(adapter);

        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        StringBuilder builder3 = new StringBuilder();

        for (int i=0;i<result.length;i++) {
                if(result[i] != null) {

                    builder.append(result[i]+"$" + " \n");
                    textView.setText(builder.toString());


                }
        }


        for (int i=0;i<result1.length;i++) {
            if(result1[i] != null) {
                builder1.append(result1[i] + " \n");
                textView1.setText(builder1.toString());


            }
        }
        for (int i=0;i<result2.length;i++) {
            if(result2[i] != null) {

                builder2.append(result2[i] + " \n");
                textView2.setText(builder2.toString());


            }
        }

        builder3.append(cost + "$" + " \n");
       textView3.setText(builder3.toString());

        btn = (Button)findViewById(R.id.button2);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        btn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                temp += cost;

                String txt="";
                String del = " ";

                for(int m=0;m<result1.length;m++)
                {
                    if(result1[m]!=null)
                    {
                        txt = txt + result1[m];
                        if(m<result1.length-1)
                        {
                            txt = txt + del;
                        }
                    }

                }
                //String a1 = "Ghee Rice Butter Chicken Gobi Manchuri Gulab Jamoon Chicken Kebab";
                String a2 = "Veg";
                String a11 = String.valueOf(temp+"$");
                //String a12 = "id1";

                db = new DataHandler(getBaseContext());
                db.open();
                Cursor cs;
                cs = db.returnData();
                long a = cs.getCount();
                a++;
                String str = String.valueOf(a);
                long id = db.insertData(txt,a11,str);
                Toast.makeText(getBaseContext(), "Insertion done"+id, Toast.LENGTH_SHORT).show();
                db.close();
                //break;

                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("key", temp);
                intent.putExtras(extras);
                //startActivityForResult(intent, 2);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                temp = 0;
                Bundle extras = new Bundle();

                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                extras.putInt("key", temp);
                intent.putExtras(extras);

                //startActivityForResult(intent, 2);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }



}
