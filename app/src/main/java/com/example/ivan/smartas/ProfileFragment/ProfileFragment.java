package com.example.ivan.smartas.ProfileFragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.ivan.smartas.ProfileFragment.NestedScrollFragments.ProfileArchiveFragment;
import com.example.ivan.smartas.ProfileFragment.NestedScrollFragments.ProfileDoingFragment;
import com.example.ivan.smartas.ProfileFragment.NestedScrollFragments.ProfilePreviewsFragment;
import com.example.ivan.smartas.ProfileFragment.NestedScrollFragments.ProfileTookFragment;
import com.example.ivan.smartas.R;

/**
 * Created by Ivan on 01.06.2017.
 */

public class ProfileFragment extends Fragment{
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.profile, container, false);

        String[] titles = {"Выполняю", "Заказал", "Архив", "Отзывы"};

        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        for(int i = 0; i < 4; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(titles[i]);
            tabLayout.setTabTextColors(Color.BLACK, Color.BLACK);
            tabLayout.addTab(tab);
        }

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.profile_nested_scroll_container, new ProfileDoingFragment()).commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            Fragment fragment = null;

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragment = new ProfileDoingFragment();
                        break;
                    case 1:
                        fragment = new ProfileTookFragment();
                        break;
                    case 2:
                        fragment = new ProfileArchiveFragment();
                        break;
                    case 3:
                        fragment = new ProfilePreviewsFragment();
                        break;
                }

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.profile_nested_scroll_container, fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

}
