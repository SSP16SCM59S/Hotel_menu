package com.cs442.shash5259.assignment2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    ArrayList<String> list = new ArrayList<String>();

    //ArrayAdapter<String> adapter;
    private Button btn;
    private Button btn1;
    private Button ord;
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private EditText et;
    private EditText et1;
    private EditText et2;
    private int i=0;
    private int j=0;
    private int k=0;
    private int result =0;



    int  num1 =0;
    int res1=0;
    String[] shareFact = new String[25];
    String[] shareFact1 = new String[25];
    String[] shareFact2 = new String[25];
    DataHandler db;
    DataHandler db1;


    private List<Hotel> myHotels = new ArrayList<Hotel>();
    private ArrayList<String> strArr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Toast.makeText(MainActivity.this,"2",Toast.LENGTH_LONG).show();
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();

        this.setResult(2, myIntent);

       if(extras!=null)
        {
//onActivityResult(2,2,myIntent);
            this.setResult(2, myIntent);
         //   this.finish();

        }
        strArr = new ArrayList<String>();
        btn = (Button)findViewById(R.id.button);
        btn1 = (Button)findViewById(R.id.button4);
        ord = (Button)findViewById(R.id.button9);
       // et = (EditText)findViewById(R.id.myEditText);
       // et1 = (EditText)findViewById(R.id.myeditText1);
       // et2 = (EditText)findViewById(R.id.myeditText2);
        lv = (ListView)findViewById(R.id.listView);
        //lv.setAdapter(adapter);




        populateHotelList();
        populateListView();
        registerClickCallBack();

        //Button btn1 = (Button)findViewById(R.id.button);

        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);


       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        ord.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,Database.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {



      //  super.onActivityResult(requestCode, resultCode, data);

//Toast.makeText(MainActivity.this,"3",Toast.LENGTH_LONG).show();

                for(int i=0;i<lv.getChildCount();i++)
                {
                    View listitem = lv.getChildAt(i);

                    listitem.setBackgroundColor(Color.WHITE);
                }
                for(int i=0;i<6;i++)
                {
                    lv.setItemChecked(i,false);
                }
                for (int i=0;i<shareFact.length;i++)
                {
                    shareFact[i]=null;
                    shareFact1[i]=null;
                    shareFact2[i]=null;
                }
                TextView t1 = (TextView) findViewById(R.id.Cost);
                Intent myIntent = data;
               Bundle extras = myIntent.getExtras();

                result = extras.getInt("key");
                res1 = result + res1;
                StringBuilder builder = new StringBuilder();
                builder.append(res1 + "$" + " \n");
                t1.setText(builder.toString());
            }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void populateHotelList()
    {
        myHotels.add(new Hotel("Butter Chicken",25,"Non-veg",R.drawable.butterchicken ));
        myHotels.add(new Hotel("Gulab Jamoon",10,"Veg",R.drawable.gulabjamoon));
        myHotels.add(new Hotel("Chicken Kabab",15,"Non-veg",R.drawable.chickenkabab ));
        myHotels.add(new Hotel("Gobi Manchuri", 12,"Veg", R.drawable.gobi));
        myHotels.add(new Hotel("Ghee Rice", 17, "Veg", R.drawable.gheerice));
    }


    private void populateListView() {
        final ArrayAdapter<Hotel> adapter = new MyListAdapter();
        final ListView list = (ListView) findViewById(R.id.listView);

        //list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, strArr));
        list.setAdapter(adapter);


//where list_content is the id of TextView in listview_item.xml


        //list.setOnItemClickListener(items);




           /*

                String add1;
                String add2;
                String add3;

                 add1 = et.getText().toString();
                 add2 = et1.getText().toString();
                 add3 = et2.getText().toString();
                 int no = Integer.parseInt(add3);
                strArr.add(et.getText().toString());
                String message1 = "New Item Successfuly Added";
                if(add1.length()!= 0 && add2.length()!=0 && add3.length()!=0)
                {
                    myHotels.add(new Hotel(add1, no, add2, R.drawable.food));
                    Toast.makeText(MainActivity.this, message1, Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "One or More Fields Left blank Please Enter Data", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } */




        //list = getListView();

        //lv.setDividerHeight(3);
       /*
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int arg2, long arg3) {
                Hotel clickedHotel = myHotels.get(arg2);
                String str = lv.getItemAtPosition(arg2).toString();
                myHotels.remove(arg2);
                // Can't manage to remove an item here
                String message7 =  clickedHotel.getName() + " is removed";
                Toast.makeText(MainActivity.this, message7, Toast.LENGTH_SHORT).show();

                list.setAdapter(adapter);


                return false;
            }
        });
 */
            registerClickCallBack();

    }
  //  ListView listView1 = (ListView)findViewById(R.id.listView);



    public class MyListAdapter extends ArrayAdapter<Hotel>
    {
        public MyListAdapter()
        {
            super(MainActivity.this, R.layout.item_view, myHotels);
        }

        @Override
        public View getView(int position, final View convertView,ViewGroup parent)
        {
            View itemView = convertView;
            if(itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }
            //find item to work with
            Hotel currentHotel = myHotels.get(position);

            //fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            imageView.setImageResource(currentHotel.getIconId());

            TextView makeText = (TextView) itemView.findViewById(R.id.item_txtName);
            makeText.setText(currentHotel.getName());

            TextView priceText = (TextView) itemView.findViewById(R.id.item_txtPrice);
            priceText.setText("" + currentHotel.getPrice() + " $");

            TextView VegNonText = (TextView) itemView.findViewById(R.id.item_txtVeg_Non);
            VegNonText.setText(currentHotel.getVeg_nonv());

            //lv.setBackgroundColor(Color.WHITE);

            return itemView;
            //return super.getView(position,convertView,parent);
        }

            }
    private void registerClickCallBack()
    {

        final ListView list = (ListView)findViewById(R.id.listView);
        list.setClickable(true);
        list.setItemsCanFocus(true);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //ArrayAdapter<Hotel> adapter1 = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,myHotels);
        ArrayAdapter<Hotel> adapter = new MyListAdapter();
        list.setAdapter(adapter);
         // list.setAdapter(adapter1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View viewClicked,  final int position, long id) {


                Hotel clickedHotel = myHotels.get(position);
                viewClicked.setBackgroundColor(Color.rgb(80,80,80));
                list.setItemChecked(position, true);
                //int m1 = list.getCheckedItemCount();
                TextView textView = (TextView) viewClicked.findViewById(R.id.item_txtPrice);
                TextView textView1 = (TextView) viewClicked.findViewById(R.id.item_txtName);
                TextView textView2 = (TextView) viewClicked.findViewById(R.id.item_txtVeg_Non);

                final String temp = textView.getText().toString();

                String[] temp1 = temp.split(" ");

                shareFact[i++] = temp1[0].toString();
                shareFact1[j++] = textView1.getText().toString();
                shareFact2[k++] = textView2.getText().toString();

                btn1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                              for(int i=0;i<list.getChildCount();i++)
                                              {
                                                  View listitem = list.getChildAt(i);

                                                  listitem.setBackgroundColor(Color.TRANSPARENT);
                                              }
                                                for(int i=0;i<6;i++)
                                                {
                                                    list.setItemChecked(i,false);
                                                }
                                                for (int i=0;i<shareFact.length;i++)
                                                {
                                                    shareFact[i]=null;
                                                    shareFact1[i]=null;
                                                    shareFact2[i]=null;
                                                }
                                            }
                });

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {






                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        //startActivityForResult(intent, 2);// Activity is started with requestCode 2
                        // Intent i = new Intent(MainActivity.this, Main2Activity.class);

                        if(list.getCheckedItemCount() == 0)
                        {
                            Toast.makeText(MainActivity.this,"Select an item First",Toast.LENGTH_SHORT).show();
                        }

                      //   Bundle b = new Bundle();
                       // Bundle b1 = new Bundle();
                       // Bundle b2 = new Bundle();

                       // b.putStringArray("key", shareFact);
                       // b1.putStringArray("key1", shareFact1);
                       // b2.putStringArray("key2",shareFact2);

                        // Add the bundle to the intent.
                       // intent.putExtras(b);
                       // intent.putExtras(b1);
                        //intent.putExtras(b2);
                        else {
                            Bundle extras = new Bundle();
                            extras.putStringArray("key", shareFact);
                            extras.putStringArray("key1", shareFact1);
                            extras.putStringArray("key2", shareFact2);

                            intent.putExtras(extras);

                            // start the ResultActivity
                            startActivityForResult(intent, 2);
                        }

                    }
                });


                //for (int i = 0; i < list.getAdapter().getCount(); i++) {
               //     if (checked.get(i)) {
                //        viewClicked.setBackgroundColor(Color.WHITE);
               //     }
               // }


                String message = "You Clicked " + clickedHotel.getName() + "  King Sized Burger with Chicken Steak \n Ingredients - Bread,Chicken,Steak \n It is Sour and Spicy!";
                String test = clickedHotel.getName();
                String message1 = "You Clicked " + clickedHotel.getName() + "  Chinese Noodles with veg curry \n Ingredients - schezwan noodle, hot curry \n It is Plain and tasty! ";
                String message2 = "You Clicked " + clickedHotel.getName() + " Italian pasta with sauce \n Ingredients - pasta, mayonese sauce \n It is sweet!";
                String message3 = "You Clicked " + clickedHotel.getName() + " Soup with Hot chicken wings \n Ingredients - chicken fried, sour soup \n It is non-vegetarians delight!";
                String message4 = "You Clicked " + clickedHotel.getName() + "  French vanilla Desert \n Ingredients - Vanilla, choco  \n It is Cold and Refreshing!";
                String message5 = "You Clicked " + clickedHotel.getName();
                if (test == "Burger") {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }else if (test == "Noodles") {
                    Toast.makeText(MainActivity.this, message1, Toast.LENGTH_SHORT).show();
                }else if(test == "Pasta"){
                    Toast.makeText(MainActivity.this, message2, Toast.LENGTH_SHORT).show();
                }else if(test == "Chicken Soup") {
                    Toast.makeText(MainActivity.this, message3, Toast.LENGTH_SHORT).show();
                }else if(test == "Dessert"){
                    Toast.makeText(MainActivity.this, message4, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, message5, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


