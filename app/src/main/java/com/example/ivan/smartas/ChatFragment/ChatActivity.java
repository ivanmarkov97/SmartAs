package com.example.ivan.smartas.ChatFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ivan.smartas.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static java.security.AccessController.getContext;

public class ChatActivity extends AppCompatActivity {

    private ListView listView;
    private View btnSend;
    private EditText editText;
    boolean isMine = false;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;
    private OkHttpClient client;
    private WebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        chatMessages = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list_msg);
        btnSend = findViewById(R.id.btn_chat_send);
        editText = (EditText) findViewById(R.id.msg_type);

        //set ListView adapter first
        adapter = new MessageAdapter(this, R.layout.item_chat_left, chatMessages);
        listView.setAdapter(adapter);

        client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://fast-basin-97049.herokuapp.com/gameapi").build();
        MyWebSocketListener myWebSocketListener = new MyWebSocketListener();
        webSocket = client.newWebSocket(request, myWebSocketListener);

        //event for button SEND
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input some text...", Toast.LENGTH_SHORT).show();
                } else {
                    //add message to list
                    ChatMessage chatMessage = new ChatMessage(editText.getText().toString(), isMine);
                    webSocket.send(chatMessage.getContent());
                    chatMessages.add(chatMessage);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    /*if (isMine) {
                        isMine = false;
                    } else {
                        isMine = true;
                    }*/
                }
            }
        });
    }

    private class MyWebSocketListener extends WebSocketListener{
        public MyWebSocketListener() {
            super();
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            //super.onMessage(webSocket, text);
            Log.d("WebSockTAG", text);
        }

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("code", 2);
                jsonObject.put("id", 1);
            }catch (JSONException e){;}
            Log.d("WebSockJS", jsonObject.toString());
            webSocket.send(jsonObject.toString());
        }
    }

    private String makeChatJSON(int from, int to, int chat_id, String msg){
        JSONObject jsonMsg = new JSONObject();
        try{
            jsonMsg.put("code", 1);
            jsonMsg.put("response", (new JSONObject()).put("message", msg).put("from_id", from).put("to_id", to).put("chat_id", chat_id));
        }catch (JSONException e){;}
        Log.d("WebSockTAG", "jsonMsg == " + jsonMsg.toString());
        return jsonMsg.toString();
    }
}
