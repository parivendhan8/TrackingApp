package com.example.trackingapp;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


import static com.example.trackingapp.MyApplication.TAG;
import static com.example.trackingapp.SQLiteHelper.Date_Time;
import static com.example.trackingapp.SQLiteHelper.Date_Time_Location;
import static com.example.trackingapp.SQLiteHelper.Demodate;
import static com.example.trackingapp.SQLiteHelper.Followdate;
import static com.example.trackingapp.SQLiteHelper.Latitude;
import static com.example.trackingapp.SQLiteHelper.Longitude;
import static com.example.trackingapp.SQLiteHelper.Name;
import static com.example.trackingapp.SQLiteHelper.Notes;
import static com.example.trackingapp.SQLiteHelper.Phone;
import static com.example.trackingapp.SQLiteHelper.Role;
import static com.example.trackingapp.SQLiteHelper.Shopname;
import static com.example.trackingapp.SQLiteHelper.TABLE_NAME;
import static com.example.trackingapp.SQLiteHelper.TABLE_NAME_Location;
import static com.example.trackingapp.SQLiteHelper.UserLatitude;
import static com.example.trackingapp.SQLiteHelper.UserLongitude;
import static com.example.trackingapp.SQLiteHelper.UserName;


public class   Home extends Fragment {

    int selectedId,select;
    SQLiteHelper db;
    private HashMap user = new HashMap<>();
    private  HashMap regvalues=new HashMap<>();
    String uidvalue,testcheck;
    EditText namevalue,shopname,phonevalue,rolevalue;
    Button btnSave,btnView,ViewLocation;
    String sv,sv1;
//    SessionManager session;
    ScrollView scrollView;
    Runnable r;
    EditText prepnotes;
    EditText followdate,demodate;
    SQLiteDatabase database;
    String lat,lon;
    String formattedDate,Date_location;
    Handler handler;
    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();
    private final static int ALL_PERMISSIONS_RESULT = 101;
    GPSTracker gps;
    public RadioGroup radiofollowgroup,radiodemogroup;
    public RadioButton radiofollowbutton,radiodemobutton;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    public int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home, container, false);
        followdate = view.findViewById(R.id.date_follow);
        demodate = view.findViewById(R.id.date_demo);
        namevalue = view.findViewById(R.id.emp_nameid);
        shopname = view.findViewById(R.id.emp_district_id);
        phonevalue = view.findViewById(R.id.rolevalueid);
        rolevalue = view.findViewById(R.id.emp_rolevalue);
        radiofollowgroup = view.findViewById(R.id.radiofollow);
        radiodemogroup = view.findViewById(R.id.radiodemo);
        btnSave = view.findViewById(R.id.savebutton);
        prepnotes = view.findViewById(R.id.prep_notes_id_ip);




        btnView = view.findViewById(R.id.viewbutton);
        ViewLocation=view.findViewById(R.id.viewLocationBtn);
        gps = new GPSTracker(getActivity());
         db = new SQLiteHelper(getActivity());
        database = db.getWritableDatabase();
        try {
            if (ActivityCompat.checkSelfPermission(getContext(), mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);
//                if (gps.canGetLocation()) {
//
//                    double latitude = gps.getLatitude();
//                    double longitude = gps.getLongitude();
//
//                   lat=String.valueOf(latitude);
//                   lon=String.valueOf(longitude);
//
//                    Log.e("lat",lat+"");
//                    Log.e("longitude",lon+"");
//                    Toast.makeText(getActivity(), "Your Location is - \nLat: "
//                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//                } else {
//
//                    gps.showSettingsAlert();
//                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        handler = new Handler();
        r = new Runnable() {
            public void run() {

                SharedPreferences sp =getActivity().getSharedPreferences("gpscheck", Activity.MODE_PRIVATE);
                String myIntValue = sp.getString("valu", null);
                Log.e("myIntValue",myIntValue+"");


                if (myIntValue!=null){
                    if (myIntValue.equalsIgnoreCase("1")){



                    }
                }

                Locationupdatestoggle();

//                Toast.makeText(getActivity(), "Your Location is - \nLat: "
//                        + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                Log.e("Handler", "Running Handler");
                handler.postDelayed(this, 3000);

            }
        };

        handler.postDelayed(r, 3000);



            String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            followdate.setText(date_n);
            demodate.setText(date_n);


            followdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    sv = (dayOfMonth < 10 ? ("0" + dayOfMonth) : (dayOfMonth)) + "-" + ((monthOfYear + 1) < 10 ? ("0" + (monthOfYear + 1)) : (monthOfYear + 1)) + "-" + year;
                                    followdate.setText(sv);


//                                (month<10?("0"+month):(month))
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            });
            demodate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    sv1 = (dayOfMonth < 10 ? ("0" + dayOfMonth) : (dayOfMonth)) + "-" + ((monthOfYear + 1) < 10 ? ("0" + (monthOfYear + 1)) : (monthOfYear + 1)) + "-" + year;
                                    demodate.setText(sv1);


//                                (month<10?("0"+month):(month))
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            });
            btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BlankFragment.class);
                startActivity(intent);
                }
            });

        ViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Fragment_Location.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => "+c.getTime());
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                     formattedDate = df.format(c.getTime());
                    // formattedDate have current date/time
                    Toast.makeText(getActivity(), formattedDate, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onCreateView: dateCheck"+formattedDate);
                    Locationupdate();


                }


            });

            return view;
        }

//    private void UpdateLocation() {
//        Log.e("UpdateLocation","working");
//        handler.post(r);
//
//    }

    private void Locationupdatestoggle() {
        handler.removeCallbacks(r);
        Log.e("Locationtoogle","working");

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        lat=String.valueOf(latitude);
        lon=String.valueOf(longitude);

        Log.e("lat",lat+"");
        Log.e("longitude",lon+"");

        Log.e("lat",latitude+"");
        Log.e("longitude",longitude+"");
        Calendar c1 = Calendar.getInstance();
        System.out.println("Current time => "+c1.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date_location = df.format(c1.getTime());
        // formattedDate have current date/time
        Toast.makeText(getActivity(), Date_location, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreateView: dateCheck"+Date_location);
        LocationuSave();
    }


    private void LocationuSave() {

        ContentValues value = new ContentValues();
        value.put(UserName, namevalue.getText().toString());
        value.put(UserLatitude, lat);
        value.put(UserLongitude, lon);
        value.put(Date_Time_Location,Date_location);
        database.insert(TABLE_NAME_Location, null, value);
        Log.e("Values",value+"");
    }


    private void Locationupdate() {

        Log.e("lat",lat+"");
        Log.e("lon",lon+"");
        ContentValues values = new ContentValues();
        values.put(Name, namevalue.getText().toString());
        values.put(Shopname, shopname.getText().toString());
        values.put(Phone, phonevalue.getText().toString());
        values.put(Role, rolevalue.getText().toString());
        values.put(Notes, prepnotes.getText().toString());
        values.put(Followdate, followdate.getText().toString());
        values.put(Demodate, demodate.getText().toString());
        values.put(Latitude, lat);
        values.put(Longitude, lon);
        values.put(Date_Time,formattedDate);

        database.insert(TABLE_NAME, null, values);
          Log.e("Values",values+"");
        Toast.makeText(getActivity(),
                "Inserted Successfully", Toast.LENGTH_SHORT).show();
        namevalue.setText("");
        shopname.setText("");
        phonevalue.setText("");
        rolevalue.setText("");
        prepnotes.setText("");
        followdate.setText("");
        demodate.setText("");


//        ContentValues value = new ContentValues();
//        value.put(UserName, namevalue.getText().toString());
//        value.put(UserLatitude, lat);
//        value.put(UserLongitude, lon);
//        database.insert(TABLE_NAME_Location, null, value);
//        Log.e("Values",value+"");

//
    }

}
