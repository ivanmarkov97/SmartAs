package com.example.ivan.smartas.ProfileFragment.NestedScrollFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ivan.smartas.R;

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
        for(int i = 0; i < 100; i ++) {
            View addView = layoutInflater.inflate(R.layout.profile_doing_layout_item, linearLayout, false);
            linearLayout.addView(addView);
        }

        return v;
    }
}
