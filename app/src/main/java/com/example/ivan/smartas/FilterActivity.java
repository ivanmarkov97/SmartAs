package com.example.ivan.smartas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.smartas.FilterDialogs.FilterCostDialog;
import com.example.ivan.smartas.FilterDialogs.ScienceRegionDialog;
import com.example.ivan.smartas.FilterDialogs.ScienceWorkType;

import static com.example.ivan.smartas.R.layout.toolbar;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;

    Button scienceButton;
    Button scienceType;
    SwitchCompat orderedASK;
    Button sortByDate;
    Button sortByCost;
    Button sortByLimit;
    TextView minCost;
    TextView maxCost;
    Button takeFilter;

    private final String FILTER_SETTINGS = "filter_settings";
    private final String FILTER_SCIENCE_REGION = "science_region";
    private final String FILTER_WORK_TYPE = "work_type";
    private final String FILTER_ORDER_ASK = "order_ask";
    private final String FILTER_SORTED_DATE = "sorted_date";
    private final String FILTER_SORTED_COST = "sorted_cost";
    private final String FILTER_SORTED_LIMIT = "sorted_deadline";
    private final String FILTER_MIN_COST = "min_cost";
    private final String FILTER_MAX_COST = "max_cost";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Фильтр");

        scienceButton = (Button) findViewById(R.id.filter_science_btn);
        scienceType = (Button) findViewById(R.id.filter_order_type_btn);
        orderedASK = (SwitchCompat) findViewById(R.id.switcher);
        sortByDate = (Button) findViewById(R.id.filter_order_sort_date);
        sortByCost = (Button) findViewById(R.id.filter_order_sort_cost);
        sortByLimit = (Button) findViewById(R.id.filter_order_sort_deadline);
        minCost = (TextView) findViewById(R.id.filter_sort_cost_min_value);
        maxCost = (TextView) findViewById(R.id.filter_sort_cost_max_value);
        takeFilter = (Button) findViewById(R.id.take_filter);

        scienceButton.setOnClickListener(this);
        scienceType.setOnClickListener(this);
        orderedASK.setOnClickListener(this);
        sortByDate.setOnClickListener(this);
        sortByCost.setOnClickListener(this);
        sortByLimit.setOnClickListener(this);
        minCost.setOnClickListener(this);
        maxCost.setOnClickListener(this);
        takeFilter.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(FILTER_SETTINGS, Context.MODE_PRIVATE);
        filterBySharedPreferences(sharedPreferences);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(FilterActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            //finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (v.getId()){
            case R.id.filter_science_btn:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ScienceRegionDialog scienceRegionDialog = new ScienceRegionDialog();
                scienceRegionDialog.show(fragmentManager, "science_region");
                break;
            case R.id.filter_order_type_btn:
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                ScienceWorkType scienceWorkType = new ScienceWorkType();
                scienceWorkType.show(fragmentManager1, "work_type");
                break;
            case R.id.switcher:
                if(orderedASK.isChecked()){
                    editor.putString(FILTER_ORDER_ASK, "true");
                }else {
                    editor.putString(FILTER_ORDER_ASK, "false");
                }
                editor.apply();
                break;
            case R.id.filter_order_sort_date:
                Drawable imageCheck = getResources().getDrawable(R.drawable.ic_filter_sort_check);
                sortByDate.setCompoundDrawablesWithIntrinsicBounds(null, null, imageCheck, null);
                sortByCost.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                sortByLimit.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                editor.putString(FILTER_SORTED_DATE, "true");
                editor.putString(FILTER_SORTED_COST, "false");
                editor.putString(FILTER_SORTED_LIMIT, "false");
                editor.apply();
                break;
            case R.id.filter_order_sort_cost:
                Drawable imageCheck1 = getResources().getDrawable(R.drawable.ic_filter_sort_check);
                sortByDate.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                sortByCost.setCompoundDrawablesWithIntrinsicBounds(null, null, imageCheck1, null);
                sortByLimit.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                editor.putString(FILTER_SORTED_DATE, "false");
                editor.putString(FILTER_SORTED_COST, "true");
                editor.putString(FILTER_SORTED_LIMIT, "false");
                editor.apply();
                break;
            case R.id.filter_order_sort_deadline:
                Drawable imageCheck2 = getResources().getDrawable(R.drawable.ic_filter_sort_check);
                sortByCost.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                sortByDate.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                sortByLimit.setCompoundDrawablesWithIntrinsicBounds(null, null, imageCheck2, null);
                editor.putString(FILTER_SORTED_DATE, "false");
                editor.putString(FILTER_SORTED_COST, "false");
                editor.putString(FILTER_SORTED_LIMIT, "true");
                editor.apply();
                break;
            case R.id.filter_sort_cost_min_value:
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FilterCostDialog filterCostDialogMin = new FilterCostDialog();
                filterCostDialogMin.setType(FILTER_MIN_COST);
                filterCostDialogMin.show(fragmentManager2, "min_cost");
                break;
            case R.id.filter_sort_cost_max_value:
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FilterCostDialog filterCostDialogMax = new FilterCostDialog();
                filterCostDialogMax.setType(FILTER_MAX_COST);
                filterCostDialogMax.show(fragmentManager3, "max_cost");
                break;
            case R.id.take_filter:
                editor.apply();
                Toast.makeText(getApplicationContext(), "Фильтр настроен", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void filterBySharedPreferences(SharedPreferences sharedPreferences){
        if(sharedPreferences != null){
            String region = "all";
            String type = "all";
            String isASK = "false";
            String byDate = "true";
            String byCost = "false";
            String byLimit = "false";
            String minCostSP = "";
            String maxCostSP = "";

            region = sharedPreferences.getString(FILTER_SCIENCE_REGION, "All");
            type = sharedPreferences.getString(FILTER_WORK_TYPE, "Все типы");
            isASK = sharedPreferences.getString(FILTER_ORDER_ASK, "false");
            byDate = sharedPreferences.getString(FILTER_SORTED_DATE, "true");
            byCost = sharedPreferences.getString(FILTER_SORTED_COST, "false");
            byLimit = sharedPreferences.getString(FILTER_SORTED_LIMIT, "false");
            minCostSP = sharedPreferences.getString(FILTER_MIN_COST, "Цена от");
            maxCostSP = sharedPreferences.getString(FILTER_MAX_COST, "Цена до");

            scienceButton.setText(region);
            scienceType.setText(type);
            minCost.setText(minCostSP);
            maxCost.setText(maxCostSP);
            if(isASK.equals("false")){
                orderedASK.setChecked(false);
            } else {
                orderedASK.setChecked(true);
            }
            Drawable imageCheckSP = getResources().getDrawable(R.drawable.ic_filter_sort_check);
            if(byDate.equals("true")){
                sortByDate.setCompoundDrawablesWithIntrinsicBounds(null, null, imageCheckSP, null);
                sortByCost.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                sortByLimit.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
            if(byCost.equals("true")){
                sortByDate.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                sortByCost.setCompoundDrawablesWithIntrinsicBounds(null, null, imageCheckSP, null);
                sortByLimit.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
            if(byLimit.equals("true")){
                sortByDate.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                sortByCost.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                sortByLimit.setCompoundDrawablesWithIntrinsicBounds(null, null, imageCheckSP, null);
            }
        }
    }
}
