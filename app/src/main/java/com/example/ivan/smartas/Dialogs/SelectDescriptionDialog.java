package com.example.ivan.smartas.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ivan.smartas.R;

/**
 * Created by Ivan on 15.09.2017.
 */

public class SelectDescriptionDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Добавьте описание");
        final EditText editText = new EditText(getContext());
        builder.setView(editText);
        builder.setPositiveButton("Применить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String desc = editText.getText().toString();
                if(desc.length() > 20) {
                    desc = desc.substring(0, 20);
                    ((TextView) getActivity().findViewById(R.id.add_description_value)).setText(desc + "...");
                }else {
                    ((TextView) getActivity().findViewById(R.id.add_description_value)).setText(desc);
                }
            }
        });

        return builder.create();
    }
}
