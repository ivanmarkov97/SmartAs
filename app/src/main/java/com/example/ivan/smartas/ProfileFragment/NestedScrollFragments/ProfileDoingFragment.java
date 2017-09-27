package com.example.ivan.smartas.ProfileFragment.NestedScrollFragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.smartas.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ivan on 16.06.2017.
 */

public class ProfileDoingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_doing_layout, container, false);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.profile_doing_layout_item_container);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        DoingLoader doingLoader = new DoingLoader();
        doingLoader.setLayoutInflater(layoutInflater);
        doingLoader.setLinearLayout(linearLayout);
        doingLoader.execute();

        /*for(int i = 0; i < 100; i ++) {
            View addView = layoutInflater.inflate(R.layout.profile_doing_layout_item, linearLayout, false);
            linearLayout.addView(addView);
        }*/

        return v;
    }

    public class DoingLoader extends AsyncTask<String, Void, String>{

        URL url;
        String response = "";
        JSONObject jsonObject = null;
        LayoutInflater layoutInflater;
        LinearLayout linearLayout;

        @Override
        protected void onPostExecute(String s) {
            Log.d("TAG","response = " + s);
            setLinearLayoutContent();
        }

        public void setLinearLayout(LinearLayout linearLayout) {
            this.linearLayout = linearLayout;
        }

        public void setLayoutInflater(LayoutInflater layoutInflater) {
            this.layoutInflater = layoutInflater;
        }

        public void setLinearLayoutContent(){
            int len = 0;

            final String[] ordetype = {"Домашняя работа", "Контрольная работа", "Курсовая работа"};

            try{
                jsonObject = new JSONObject(response);
                len = jsonObject.getJSONArray("response").length();
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                Log.d("TAG", String.valueOf(len));
                for(int i = 0; i < len; i++) {
                    View addView = layoutInflater.inflate(R.layout.order_item, linearLayout, false);
                    ((TextView)addView.findViewById(R.id.subject_name)).setText(jsonArray.getJSONObject(i).getString("subject"));
                    ((TextView)addView.findViewById(R.id.order_type)).setText(ordetype[jsonArray.getJSONObject(i).getInt("type")]);
                    ((TextView)addView.findViewById(R.id.order_date)).setText(jsonArray.getJSONObject(i).getString("create_date"));
                    ((TextView)addView.findViewById(R.id.order_limit)).setText(jsonArray.getJSONObject(i).getString("end_date"));
                    ((TextView)addView.findViewById(R.id.order_cost)).setText(String.valueOf(jsonArray.getJSONObject(i).getInt("cost")));
                    linearLayout.addView(addView);
                }
            }catch (JSONException e){
                Log.d("ERROR_TAG", e.getMessage());
                len = 0;
            }
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                url = new URL("https://fast-basin-97049.herokuapp.com/order/perform/?id=22");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    Log.d("TAG", "OK DOING");
                }else{
                    Log.d("TAG", "NOT OK DOING");
                }

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    response += line;
                }
            }catch (MalformedURLException e){
                ;
            }catch (IOException e){
                ;
            }

            return response;
        }
    }
}
