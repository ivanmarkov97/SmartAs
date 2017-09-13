package com.example.ivan.smartas.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ivan.smartas.OrderShowActivity;
import com.example.ivan.smartas.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.ivan.smartas.RecyclerViewListOrder.Order;
import com.example.ivan.smartas.RecyclerViewListOrder.OrderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ivan on 01.06.2017.
 */

public class HomeFragment extends Fragment{

    RecyclerView listViewAllOrders;
    private Context context;
    private GetReciever getReciever;
    private OrderAdapter orderAdapter;
    private ArrayList<Order> orders = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, container, false);
        context = container.getContext();

        listViewAllOrders = (RecyclerView) v.findViewById(R.id.all_orders);
        listViewAllOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        listViewAllOrders.setHasFixedSize(true);
        orderAdapter = new OrderAdapter(getContext());
        listViewAllOrders.setAdapter(orderAdapter);

        getReciever = new GetReciever();
        getReciever.execute("https://fast-basin-97049.herokuapp.com/order/new?user_id=12");

        return v;
    }

    public class GetReciever extends AsyncTask<String, Void, String> {

        private String responce = "";
        private JSONObject jsonObject = null;
        private String[] types = {"Все типы", "Домашняя работа", "Контрольная работа", "Курсовой проект"};
        private final String SUBJECT_NAME_ATTRIBUTE = "ssubject_name";
        private final String ORDER_TYPE_ATTRIBUTE = "order_type";
        private final String ORDER_CREATE_DATE = "created_date";
        private final String ORDER_DEADLINE_DATE = "deadline_date";
        private final String ORDER_COST = "order_cost";
        private Context context;

        @Override
        protected String doInBackground(String... params) {
            URL url;
            int responceCode;
            try{
                url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                responceCode = urlConnection.getResponseCode();

                if(responceCode == HttpURLConnection.HTTP_OK){
                    responce = readDataFromConnection(urlConnection);
                    Log.d("TestTAG", "HTTP OK");
                }else {
                    Log.d("TestTAG", "" + responceCode);
                }

            }catch (MalformedURLException e){
                ;
            }catch (IOException e){
                ;
            }
            return responce;
        }

        private String readDataFromConnection(HttpURLConnection urlConnection){
            String resp = "";
            try {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine()) != null){
                    resp += line;
                }
            }catch (IOException e){
                ;
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("TestTAG", "tag = " + s);
            JSONArray jsonArray = getJSONResponce(s);
            ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>(jsonArray.length());
            Map<String, Object> map;
            String subject = "";
            String subType = "";
            String createdDate = "";
            String deadlineDate = "";
            int cost = 0;

            for(int i = 0; i < jsonArray.length(); i++){
                try {
                subject = jsonArray.getJSONObject(i).getString("subject");
                subType = types[jsonArray.getJSONObject(i).getInt("type")];
                createdDate = jsonArray.getJSONObject(i).getString("create_date");
                deadlineDate = jsonArray.getJSONObject(i).getString("end_date");
                cost = jsonArray.getJSONObject(i).getInt("cost");
                orders.add(new Order(
                        subject,
                        subType,
                        createdDate,
                        deadlineDate,
                        cost
                ));

            }catch (JSONException e){
                    ;
                }
            }
            orderAdapter.setOrders(orders);
            orderAdapter.notifyDataSetChanged();
            Log.d("TestTAG", "" + orderAdapter.getItemCount());
        }

        public String getResponce(){
            return responce;
        }

        private JSONArray getJSONResponce(String responce){
            JSONArray jsonResponce = null;
            try {
                JSONObject jsonObject = new JSONObject(responce);
                jsonResponce = jsonObject.getJSONArray("response");
            }catch (JSONException e){
                ;
            }
            return jsonResponce;
        }
    }

}
