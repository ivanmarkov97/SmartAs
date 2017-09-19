package com.example.ivan.smartas.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.smartas.R;

/**
 * Created by Ivan on 15.09.2017.
 */

public class SelectSubjectDialog extends DialogFragment {

    private String subject = "";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Выберите предмет");
        final String[] subjects = getContext().getResources().getStringArray(R.array.subjects);
        builder.setItems(R.array.subjects, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("TestTAG", "" + which);
                subject = subjects[which];
                Toast.makeText(getContext(), subject, Toast.LENGTH_SHORT).show();
                ((TextView)getActivity().findViewById(R.id.add_subject_name_value)).setText(subject);
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
