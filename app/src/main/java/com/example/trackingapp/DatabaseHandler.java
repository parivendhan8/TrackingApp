package com.example.trackingapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AndroidLogint";
    // Login table name
    private static final String TABLE_NEWLOGIN = "tlogin";
    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_UID = "uid";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_TYPE = "type";
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "shopname";
    private static final String COL4 = "phone";
    private static final String COL5 = "role";
    private static final String COL6 = "notes";
    private static final String COL7 = "Datefollow";
    private static final String COL8 = "demodate";



    public DatabaseHandler(Context context, Context c) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);



    }




    public DatabaseHandler(Context context1){
        super(context1,TABLE_NAME,null,DATABASE_VERSION);
    }


    // Table Create Statements
    private static final String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_NEWLOGIN + "("
            +KEY_TYPE +"INTEGER,"
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_UID + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT UNIQUE,"
            + KEY_CREATED_AT + " TEXT" + ")";
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN_TABLE);
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT,"
                + COL3 + " TEXT,"
                + COL4 + " TEXT,"
                + COL5 + " TEXT,"
                + COL6 + " TEXT,"
                + COL7 + " TEXT,"
                + COL8 + " TEXT)";
        db.execSQL(createTable);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWLOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        // Create tables again
        onCreate(db);
    }
    /**
     * Storing user details in database
     * */
    public void addUser(String name, String uid, String uname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UID, uid); // uid
        values.put(KEY_NAME, uname); // FirstName
//        values.put(KEY_EMAIL, email); // Email
//        values.put(KEY_CREATED_AT, created_at); // Created At
        // Inserting Row
        db.insert(TABLE_NEWLOGIN, null, values);
        db.close(); // Closing database connection
    }
    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user = new HashMap<>();
//        String selectQuery = new StringBuilder().append("SELECT  * FROM ").append(TABLE_LOGIN).append("WHERE").append(KEY_TYPE).append("=?1").toString();
        String selectQuery = "SELECT  * FROM " + TABLE_NEWLOGIN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("name", cursor.getString(1));
            user.put("uid", cursor.getString(2));
            user.put("uname",cursor.getString(3));
//            user.put("email", cursor.getString(3));
            user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        return user;
    }

    /**
     * Re create database
     * Delete all tables and create them again
     * */
    public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NEWLOGIN, null, null);
        db.close();
    }




//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COL2 +" TEXT)";
//        db.execSQL(createTable);
//    }

//    @Override
//    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }

    public boolean addData(String item,String item1,String item2,String item3,String item4, String item5,String item6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3,item1);
        contentValues.put(COL4,item2);
        contentValues.put(COL5,item3);
        contentValues.put(COL6,item4);
        contentValues.put(COL7,item5);
        contentValues.put(COL8,item6);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        return result != -1;
    }

    /**
     * Returns all the data from database
     * @return
     */
//    public Cursor getData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = " SELECT * FROM " + TABLE_NAME;
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
//    public void updateData(String , int id, String oldName){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "UPDATE " + TABLE_NAME + " SET "
//                + COL2 +" = '" + newName +
//
//                "' WHERE " + COL1 + " = '" + id + "'" +
//                " AND " + COL2 + " = '" + oldName + "'";
//        Log.d(TAG, "updateName: query: " + query);
//        Log.d(TAG, "updateName: Setting name to " + newName);
//        db.execSQL(query);
//    }
    public boolean updateData(String id,String name,String shopname,String role,String notes,String followdate,String demodate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,shopname);
        contentValues.put(COL4,role);
        contentValues.put(COL5,notes);
        contentValues.put(COL6,followdate);
        contentValues.put(COL7,demodate);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }



    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + COL2 + COL3 + COL4 +COL5 +COL6 + COL7 +COL8 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
//        String query ="SELECT * FROM " + TABLE_NAME ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */




    /**
     * Delete from database
     * @param id
//     * @param name
     */
//    public void deleteName(int id, String name){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
//                + COL1 + " = '" + id + "'" +
//                " AND " + COL2 + " = '" + name + "'";
//        Log.d(TAG, "deleteName: query: " + query);
//        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
//        db.execSQL(query);
//    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

}