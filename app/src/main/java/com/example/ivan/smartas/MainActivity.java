package com.example.ivan.smartas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ivan.smartas.AddFragment.AddFragment;
import com.example.ivan.smartas.ChatFragment.ChatFragment;
import com.example.ivan.smartas.ClockFragment.ClockFragment;
import com.example.ivan.smartas.HomeFragment.HomeFragment;
import com.example.ivan.smartas.ProfileFragment.ProfileFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    ListView listViewAllOrders;
    Typeface typeface;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    private final String SUBJECT_NAME_ATTRIBUTE = "ssubject_name";
    private final String ORDER_TYPE_ATTRIBUTE = "order_type";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewAllOrders = (ListView) findViewById(R.id.all_orders);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cat2);
        getSupportActionBar().setTitle("Все категории");

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).disallowAddToBackStack().commit();

        //bottomNavigationView.inflateMenu(R.menu.bottom_nav_items);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_bottom_clock:
                        ClockFragment clockFragment = new ClockFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, clockFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                    case R.id.menu_bottom_add:
                        AddFragment addFragment = new AddFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, addFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                    case R.id.menu_bottom_chat:
                        ChatFragment chatFragment = new ChatFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                    case R.id.menu_bottom_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;

                    case R.id.menu_bottom_home:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).disallowAddToBackStack().commit();
                        //listViewAllOrders.setEnabled(false);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            overridePendingTransition(R.anim.right_in,R.anim.left_out);
        }
        if(item.getItemId() == R.id.action_filter){
            startActivity(new Intent(MainActivity.this, FilterActivity.class));
            overridePendingTransition(R.anim.left_in,R.anim.right_out);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, OrderShowActivity.class));
    }
}
