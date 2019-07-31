package com.example.trackingapp;



import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class BlankFragment extends Activity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter;
    ListView LISTVIEW;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> PHONE_NUMBER_Array;
    ArrayList<String> Role_Array;
    ArrayList<String> Notes_Array;
    ArrayList<String> Follow_Array;
    ArrayList<String> Demo_Array;
    ArrayList<String> Latitude;
    ArrayList<String> Longitude;
    ArrayList<String> Date_Time;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    String TempHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);

        LISTVIEW = findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        PHONE_NUMBER_Array = new ArrayList<String>();
        Role_Array = new ArrayList<String>();

        Notes_Array = new ArrayList<String>();

        Follow_Array = new ArrayList<String>();

        Demo_Array = new ArrayList<String>();

        Latitude = new ArrayList<String>();

        Longitude = new ArrayList<String>();

        Date_Time = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

    }
    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();
        Role_Array.clear();
        Notes_Array.clear();
        Follow_Array.clear();
        Demo_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Name)));

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Shopname)));

                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Phone)));

                Role_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Role)));

                Notes_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Notes)));

                Follow_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Followdate)));

                Demo_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Demodate)));

                Latitude.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Latitude)));

                Longitude.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Longitude)));

                Date_Time.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Date_Time)));





            } while (cursor.moveToNext());
        }
        listAdapter = new ListAdapter(BlankFragment.this, ID_Array,NAME_Array,PHONE_NUMBER_Array,Role_Array,Notes_Array,Follow_Array,Demo_Array,Latitude,Longitude,Date_Time);

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}