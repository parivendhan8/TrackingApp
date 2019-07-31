package com.example.trackingapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.os.Bundle;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    Toolbar toolbar;
    SessionManager sessionManager;
    Fragment newFragment;
    TextView txtname;
    ImageButton btnLogout;
    DatabaseHandler db;
    Switch aSwitch;
    int s=0;

    private HashMap user = new HashMap<>();

    BottomNavigationView navigation_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        navigation_main=(BottomNavigationView)findViewById(R.id.navigation_main);
        txtname=(TextView)findViewById(R.id.title_name);
        btnLogout=(ImageButton)findViewById(R.id.logout);
        aSwitch=(Switch)findViewById(R.id.swith_On);
        navigation_main.setItemIconTintList(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.menuicon);
        Fragmentsmethod();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        db = new DatabaseHandler(getApplicationContext());
        user = db.getUserDetails();
        sessionManager = new SessionManager(getApplicationContext());

        if (!sessionManager.isLoggedIn()) {
            logoutUser();
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    s=1;
                    String g=String.valueOf(s);
                    Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences("gpscheck", MODE_PRIVATE).edit();
                    editor.putString("valu", g);
                    editor.apply();
                } else {
                    s=0;
                    SharedPreferences sharedPreferences = getSharedPreferences("gpscheck", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.clear().apply();
                    Toast.makeText(MainActivity.this, "Disable  check", Toast.LENGTH_SHORT).show();

                }
            }
        });
        String name = (String) user.get("uname");
        txtname.setText("User Id:"+name);
        init();
    }

    private void init() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();

            }
        });


    }

    private void logoutUser() {
        sessionManager.setLogin(false);
        // Launching the login activity
        Functions logout = new Functions();
        logout.logoutUser(getApplicationContext());
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void Fragmentsmethod() {

         newFragment = new Home();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, newFragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.receipt) {
            Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
//           Intent i=new Intent(MainActivity.this,Sample.class);
//           startActivity(i);
            newFragment = new Home();
            onBackPressed();
        } else if (id == R.id.cust_bal) {
            Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
//            fragment = new Customerbal();
            onBackPressed();
        }
        else if (id == R.id.statement) {
            Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
//            fragment = new StatementFrag();
            onBackPressed();
//           fragment = new Statement();
        }
        else if(id==R.id.home_page){
            newFragment=new Home();
        }
//       else if (id==R.id.new_col){
//           fragment=new NewCollection();
//       }
        else if (id == R.id.navigation_home) {
           Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
//            fragment = new Receipt();

        }
        else if (id == R.id.navigation_dashboard) {
//           fragment = new Statement();
//            fragment = new StatementFrag();
            Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();

        }
        else if (id == R.id.navigation_notifications) {
//            fragment = new Customerbal();
            Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
        }
        else if(id==R.id.navigation_profile){
            newFragment=new Home();
        }
        if (newFragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, newFragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
