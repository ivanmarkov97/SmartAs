package com.example.ivan.smartas.FilterDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.example.ivan.smartas.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 19.09.2017.
 */

public class ScienceRegionDialog extends DialogFragment {

    private final String FILTER_SETTINGS = "filter_settings";
    private final String FILTER_SCIENCE_REGION = "science_region";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final String[] regions = getContext().getResources().getStringArray(R.array.regions);
        builder.setItems(regions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String region = regions[which];
                ((Button)getActivity().findViewById(R.id.filter_science_btn)).setText(region);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FILTER_SETTINGS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(FILTER_SCIENCE_REGION, region);
                editor.apply();
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
