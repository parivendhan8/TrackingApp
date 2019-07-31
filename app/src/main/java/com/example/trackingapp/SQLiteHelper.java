package com.example.trackingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="UserDataBase";

    public static final String TABLE_NAME="UserTable";

    public static final String Table_Column_ID="id";

    public static final String Name="name";

    public static final String Shopname="shopname";

    public static final String Phone="phone";

    public static final String Role="role";

    public static final String Notes="notes";

    public static final String Followdate="followdate";

    public static final String Demodate="demodate";

    public static final String Latitude="latitude";

    public static final String Longitude="longitude";

    public static final String Date_Time="date_time";

    public static final String TABLE_NAME_Location="UserLocation";

    public static final String UserName="name";

    public static final String UserLatitude="latitude";

    public static final String UserLongitude="longitude";

    public static final String Date_Time_Location="date_time";



    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 4);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Name+" VARCHAR, "+Shopname+" VARCHAR, "+Phone+" VARCHAR, "+Role+" VARCHAR, "+Notes+" VARCHAR, "+Followdate+" VARCHAR, "+Demodate+" VARCHAR, "+Latitude+" VARCHAR, "+Longitude+" VARCHAR, "+Date_Time+" VARCHAR)";
        database.execSQL(CREATE_TABLE);
       String CREATE_User_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_Location+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+UserName+" VARCHAR, "+UserLatitude+" VARCHAR, "+UserLongitude+" VARCHAR, "+Date_Time_Location+" VARCHAR)";
        database.execSQL(CREATE_User_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_Location);
        onCreate(db);

    }

}