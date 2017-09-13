package com.example.ivan.smartas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

public class AuthActivity extends AppCompatActivity {

    private Button logInBtn;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        logInBtn = (Button) findViewById(R.id.auth_in_btn);
        email = (EditText) findViewById(R.id.auth_enter_name);
        password = (EditText) findViewById(R.id.auth_enter_pass);

        new AuthSend().execute("https://fast-basin-97049.herokuapp.com/person/enter");
    }

    private class AuthSend extends AsyncTask<String, Void, String>{

        JSONObject jsonObject = null;
        String auth_email;
        String auth_pass;
        int responceCode;
        String responce;

        @Override
        protected void onPreExecute() {
            auth_email = email.getText().toString();
            auth_pass = password.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {
            URL url;
            try{
                url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("email", auth_email);
                    jsonObject.put("password", auth_pass);
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
                Log.d("TestTAG", responce);

            }catch (MalformedURLException e){
                ;
            }catch (IOException e){
                ;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonResp = new JSONObject(s);
                int code = jsonResp.getInt("code");
                if(code == 0){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e){
                ;
            }
        }
    }

}