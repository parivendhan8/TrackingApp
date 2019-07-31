package com.example.trackingapp;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

//import android.widget.ListAdapter;


public class Fragment_Location extends Activity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter_Location listAdapter;
    ListView LISTVIEW;


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

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME_Location+"", null);



        if (cursor.moveToFirst()) {
            do {

                Latitude.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.UserLatitude)));

                Longitude.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.UserLongitude)));

                Date_Time.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Date_Time_Location)));





            } while (cursor.moveToNext());
        }
        listAdapter = new ListAdapter_Location(Fragment_Location.this, Latitude,Longitude,Date_Time);

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}