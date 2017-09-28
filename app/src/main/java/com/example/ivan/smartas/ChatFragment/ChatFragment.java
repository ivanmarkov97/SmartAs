package com.example.ivan.smartas.ChatFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ivan.smartas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 01.06.2017.
 */

public class ChatFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.chat_list, container, false);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.chat_list_new_messages_container);
        LayoutInflater layoutInflater = getLayoutInflater(getArguments());

        LinearLayout linearLayoutAllMes = (LinearLayout) v.findViewById(R.id.chat_list_all_messages_container);
        LayoutInflater layoutInflaterAllMes = getLayoutInflater(getArguments());

        for(int i = 0; i < 3; i++) {
            View item = layoutInflater.inflate(R.layout.chat_list_item, linearLayout, false);
            RelativeLayout relativeLayout = (RelativeLayout) item.findViewById(R.id.chat_list_item_relative_layout);
            relativeLayout.setBackgroundColor(Color.parseColor("#F0F0F0"));
            //item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), ChatActivity.class));
                }
            });

            linearLayout.addView(item);
        }

        for (int i = 0; i < 20; i++){
            View item = layoutInflaterAllMes.inflate(R.layout.chat_list_item, linearLayoutAllMes, false);
            //item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), ChatActivity.class));
                }
            });
            linearLayoutAllMes.addView(item);
        }
        return v;
    }
}
