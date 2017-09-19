package com.example.ivan.smartas.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.smartas.R;

/**
 * Created by Ivan on 15.09.2017.
 */

public class SelectSunjectTypeDialog extends DialogFragment {
    private String type = "";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Выберите тип работы");
        final String[] types = getContext().getResources().getStringArray(R.array.subjects_type);
        builder.setItems(R.array.subjects_type, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                type = types[which];
                Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
                ((TextView)getActivity().findViewById(R.id.add_work_type_value)).setText(type);
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
