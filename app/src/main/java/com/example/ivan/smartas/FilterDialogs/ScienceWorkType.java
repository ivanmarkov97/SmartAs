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

import com.example.ivan.smartas.Filter;
import com.example.ivan.smartas.R;

/**
 * Created by Ivan on 19.09.2017.
 */

public class ScienceWorkType extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final String[] filter_types = getContext().getResources().getStringArray(R.array.filter_types);
        builder.setItems(filter_types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String type = filter_types[which];
                ((Button)getActivity().findViewById(R.id.filter_order_type_btn)).setText(type);
                Filter.setType(type);
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
