package com.example.ivan.smartas.AddFragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.smartas.Dialogs.SelectCostDialog;
import com.example.ivan.smartas.Dialogs.SelectDateDialog;
import com.example.ivan.smartas.Dialogs.SelectDescriptionDialog;
import com.example.ivan.smartas.Dialogs.SelectSubjectDialog;
import com.example.ivan.smartas.Dialogs.SelectSunjectTypeDialog;
import com.example.ivan.smartas.OrderShowActivity;
import com.example.ivan.smartas.R;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Ivan on 01.06.2017.
 */

public class AddFragment extends Fragment implements View.OnClickListener{

    private final String SUBJECT_NAME_ATTRIBUTE = "ssubject_name";
    private final String ORDER_TYPE_ATTRIBUTE = "order_type";
    private final int ID_SELECT_DATE = 1;
    ListView listViewAddMenu;
    private ImageView imageView1, imageView2, imageView3, imageView4;
    private Button addPublish;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.add, container, false);

        String[] subjects = {"Математика", "Тип работы", "Цена, руб", "Срок сдачи", "Описание"};
        String[] order_type = {"", "", "", "", ""};

        ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>(subjects.length);
        Map<String, Object> map;
        for(int i = 0; i < subjects.length; i++){
            map = new HashMap<String, Object>();
            map.put(SUBJECT_NAME_ATTRIBUTE, subjects[i]);
            map.put(ORDER_TYPE_ATTRIBUTE, order_type[i]);
            arrayList.add(map);
        }

        String[] from = {SUBJECT_NAME_ATTRIBUTE, ORDER_TYPE_ATTRIBUTE};
        int[] to = {R.id.subject_name, R.id.order_type};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), arrayList, R.layout.order_item, from, to);

        LinearLayout linLayout = (LinearLayout) v.findViewById(R.id.add_subject_name);

        linLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                SelectSubjectDialog selectSubjectDialog = new SelectSubjectDialog();
                selectSubjectDialog.show(fragmentManager, "select_subject");
            }
        });
        LinearLayout linLayout2 = (LinearLayout) v.findViewById(R.id.add_work_type);

        linLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                SelectSunjectTypeDialog selectSunjectTypeDialog = new SelectSunjectTypeDialog();
                selectSunjectTypeDialog.show(fragmentManager, "select_type");
            }
        });

        LinearLayout linLayout3 = (LinearLayout) v.findViewById(R.id.add_work_cost);

        linLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                SelectCostDialog selectCostDialog = new SelectCostDialog();
                selectCostDialog.show(fragmentManager, "select_cost");
            }
        });
        LinearLayout linLayout4 = (LinearLayout) v.findViewById(R.id.add_deadline);

        linLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                SelectDateDialog selectDateDialog = new SelectDateDialog();
                selectDateDialog.show(fragmentManager, "select_date");
            }
        });
        LinearLayout linLayout5 = (LinearLayout) v.findViewById(R.id.add_description);

        linLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                SelectDescriptionDialog selectDescriptionDialog = new SelectDescriptionDialog();
                selectDescriptionDialog.show(fragmentManager, "select_descr");
            }
        });


        imageView1 = (ImageView) v.findViewById(R.id.imageView);
        imageView1.setOnClickListener(this);
        imageView2 = (ImageView) v.findViewById(R.id.imageView2);
        imageView2.setOnClickListener(this);
        imageView3 = (ImageView) v.findViewById(R.id.imageView3);
        imageView3.setOnClickListener(this);
        imageView4 = (ImageView) v.findViewById(R.id.imageView4);
        imageView4.setOnClickListener(this);

        addPublish = (Button) v.findViewById(R.id.add_publish_order);
        addPublish.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intentPhotoPicker = new Intent(Intent.ACTION_PICK);
        intentPhotoPicker.setType("image/*");
        switch (v.getId()){
            case R.id.imageView:
                startActivityForResult(intentPhotoPicker, 0);
                break;
            case R.id.imageView2:
                startActivityForResult(intentPhotoPicker, 0);
                break;
            case R.id.imageView3:
                startActivityForResult(intentPhotoPicker, 0);
                break;
            case R.id.imageView4:
                startActivityForResult(intentPhotoPicker, 0);
                break;
            case R.id.add_publish_order:
                Toast.makeText(getContext(), "добавляется", Toast.LENGTH_SHORT).show();
                PostOrderSender sender = new PostOrderSender();
                sender.execute("https://fast-basin-97049.herokuapp.com/order/create");
                break;
        }
    }

    private class PostOrderSender extends AsyncTask<String, Void, String>{

        URL url;
        JSONObject jsonObject = null;
        int responceCode = 0;
        String responce;

        final String[] types = getContext().getResources().getStringArray(R.array.subjects_type);

        int type_num = 0;

        String subject = "";
        String type = "";
        Integer category = 0;
        String description = "";
        String create_date = "";
        String end_date = "";
        String cost = "";

        private int getIndexByValue(String[] mass, String value){
            for(int i = 0; i < mass.length; i++){
                if(value == mass[i]){
                    return i;
                }
            }
            return mass.length - 1;
        }

        @Override
        protected void onPreExecute() {

            Date date = new Date();

            subject = ((TextView)getActivity().findViewById(R.id.add_subject_name_value)).getText().toString();
            type = ((TextView)getActivity().findViewById(R.id.add_work_type_value)).getText().toString();
            type_num = getIndexByValue(types, type);
            category = 0;
            description = ((TextView)getActivity().findViewById(R.id.add_description_value)).getText().toString();
            create_date = "" + date.getDay() + "." + date.getMonth() + "." + (date.getYear() + 1900);
            end_date = ((TextView)getActivity().findViewById(R.id.add_deadline_value)).getText().toString();
            cost = ((TextView)getActivity().findViewById(R.id.add_work_cost_value)).getText().toString();

            Log.d("TestTAG", subject);
            Log.d("TestTAG", "" + type_num);
            Log.d("TestTAG", description);
            Log.d("TestTAG", create_date);
            Log.d("TestTAG", end_date);
            Log.d("TestTAG", "" + cost);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getContext(), "добавлено", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("subject", subject);
                    jsonObject.put("type", type_num);
                    jsonObject.put("category", 0);
                    jsonObject.put("description", description);
                    jsonObject.put("create_date", create_date);
                    jsonObject.put("end_date", end_date);
                    jsonObject.put("cost", Integer.valueOf(cost));
                    jsonObject.put("client", 12);
                }catch (JSONException e){
                    Log.d("ErrorTAG", "JSON error");
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
                    responce += line;
                }

                Log.d("TestTAG", "POST response = " + responce);

            }catch (MalformedURLException e){
                ;
            }catch (IOException e){
                ;
            }
            return null;
        }
    }

}
