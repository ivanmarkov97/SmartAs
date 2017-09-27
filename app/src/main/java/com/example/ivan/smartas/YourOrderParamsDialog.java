package com.example.ivan.smartas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ivan on 26.09.2017.
 */

public class YourOrderParamsDialog extends DialogFragment {

    String cost = "";
    String date = "";
    DialogInterface mdialog = null;
    int task_id = 0;

    public void setTaskId(int id){
        this.task_id = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Ваша цена");
        final EditText yourCost = new EditText(getContext());
        cost = ((TextView) getActivity().findViewById(R.id.order_cost)).getText().toString();
        date = ((TextView) getActivity().findViewById(R.id.order_date)).getText().toString();
        yourCost.setText(cost);
        builder.setView(yourCost);
        builder.setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TakeOrder takeOrder = new TakeOrder();
                mdialog = dialog;
                takeOrder.execute();
                ((Button) getActivity().findViewById(R.id.take_order)).setEnabled(false);
            }
        });
        return  builder.create();
    }

    public class TakeOrder extends AsyncTask<String, Void, String> {

        URL url;
        HttpURLConnection urlConnection;
        String response = "";
        JSONObject jsonObject = null;
        int responceCode;

        @Override
        protected String doInBackground(String... params) {
            try{
                url = new URL("https://fast-basin-97049.herokuapp.com/order/take");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                Log.d("TAG", String.valueOf(task_id));

                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("id", task_id);
                    jsonObject.put("executor", 22);
                    jsonObject.put("cost", cost);
                    jsonObject.put("date", date);
                }catch (JSONException e){
                    ;
                }

                DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
                dataOutputStream.writeBytes(jsonObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();

                responceCode = urlConnection.getResponseCode();
                if(responceCode == HttpURLConnection.HTTP_OK){
                    Log.d("PostTAG", "OK");
                }else {
                    Log.d("PostTAG", "FAIL");
                }

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = bufferedReader.readLine()) != null){
                    response += line;
                }

                Log.d("TestTAG", "POST response = " + response);

            }catch (MalformedURLException e){
                ;
            }catch (IOException e){
                ;
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            mdialog.dismiss();
        }
    }
}
