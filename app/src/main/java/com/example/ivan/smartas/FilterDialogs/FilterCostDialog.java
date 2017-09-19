package com.example.ivan.smartas.FilterDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ivan.smartas.Filter;
import com.example.ivan.smartas.R;

/**
 * Created by Ivan on 19.09.2017.
 */

public class FilterCostDialog extends DialogFragment {
    private final String FILTER_MIN_COST = "min_cost";
    private final String FILTER_MAX_COST = "max_cost";
    private String filter_type = "";

    public void setType(String type){
        if(type == FILTER_MAX_COST){
            filter_type = FILTER_MAX_COST;
        }else if(type == FILTER_MIN_COST){
            filter_type = FILTER_MIN_COST;
        }else{
            filter_type = "";
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText editText = new EditText(getContext());
        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(editText);
        builder.setPositiveButton("Применить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(filter_type == FILTER_MIN_COST){
                    ((TextView)getActivity().findViewById(R.id.filter_sort_cost_min_value)).setText(editText.getText().toString());
                    Filter.setMinCost(Integer.valueOf(editText.getText().toString()));
                }else if(filter_type == FILTER_MAX_COST){
                    ((TextView)getActivity().findViewById(R.id.filter_sort_cost_max_value)).setText(editText.getText().toString());
                    Filter.setMaxCost(Integer.valueOf(editText.getText().toString()));
                }else {
                    ;
                }
            }
        });
        return builder.create();
    }
}
