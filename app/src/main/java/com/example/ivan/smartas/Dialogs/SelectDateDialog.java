package com.example.ivan.smartas.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.ivan.smartas.R;

import java.util.Date;

/**
 * Created by Ivan on 15.09.2017.
 */

public class SelectDateDialog extends DialogFragment {

    Date date = new Date();
    private int day_x = date.getDay();
    private int month_x = date.getMonth();
    private int year_x = date.getYear();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                day_x = dayOfMonth;
                month_x = month + 1;
                year_x = year;
                ((TextView)getActivity().findViewById(R.id.add_deadline_value)).setText("" + day_x + "." + month_x + "." + year_x);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), myCallBack, day_x, month_x, year_x);

        return datePickerDialog;

    }
}
