package com.cs442.shash5259.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by shash on 24-02-2016.
 */
public class DataHandler {

    public static final String name = "name";
    public static final String veg = "veg";
    public static final String cost = "cost";
    public static final String id = "id";
    public static final String TABLE_NAME = "order_history";
    public static final String DATA_BASE_NAME = "finaldb1";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE = "create table order_history(id text not null,name text not null,cost text not null);";

    DatabaseHelper dbhelper;
    Context ctx;
    SQLiteDatabase db;
    public DataHandler(Context ctx)
    {
        this.ctx = ctx;
        dbhelper = new DatabaseHelper(ctx);
    }
    public static class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context ctx)
        {
            super(ctx,DATA_BASE_NAME,null,DATABASE_VERSION);

        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                db.execSQL(TABLE_CREATE);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS order_history");

        }
    }
    public DataHandler open()
    {
        db = dbhelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbhelper.close();
    }

   // public long insertData(String name,String email)
    public long insertData(String name1,String price,String id1)
    {
        //Toast.makeText("name1 : "+name1+"Email "+email,Toast.LENGTH_SHORT).show();
        ContentValues content = new ContentValues();
        content.put(name,name1);
        content.put(id, id1);
        content.put(cost, price);
        //content.put(veg, email);
        return db.insertOrThrow(TABLE_NAME, null, content);
    }

   public Cursor returnData()
   {
        //return db.query(TABLE_NAME,new String[]{name,veg}, null, null, null, null, null);
       Cursor cs;
       String query = "SELECT * FROM "+TABLE_NAME;
        //cs = db.query(TABLE_NAME,new String[] {name,veg},null,null,null,null,null);
       cs = db.rawQuery(query,null);

   return cs;
   }

    public Cursor DeleteData()
    {
        //return db.query(TABLE_NAME,new String[]{name,veg}, null, null, null, null, null);
        Cursor cs;
        String query = "DELETE FROM "+TABLE_NAME;
        //cs = db.query(TABLE_NAME,new String[] {name,veg},null,null,null,null,null);
        cs = db.rawQuery(query,null);
        return cs;
    }

}
