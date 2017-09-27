package com.example.ivan.smartas;

import android.util.Log;

/**
 * Created by Ivan on 20.09.2017.
 */

public class DateDigitsFromString {
    private int day_x;
    private int month_x;
    private int year_x;

    public DateDigitsFromString(String strDate){
        int countDots = 0;
        String tmp_day = "";
        String tmp_month = "";
        String tmp_year = "";
        for(int i = 0; i < strDate.length(); i++){
            Log.d("TestDATE", "" + strDate.charAt(i));
            if(strDate.charAt(i) != '.'){
                if(countDots == 0){
                    tmp_day += strDate.charAt(i);
                }
                if(countDots == 1){
                    tmp_month += strDate.charAt(i);
                }
                if(countDots == 2){
                    tmp_year += strDate.charAt(i);
                }
            }else{
                countDots++;
            }
        }
        day_x = Integer.valueOf(tmp_day);
        month_x = Integer.valueOf(tmp_month);
        year_x = Integer.valueOf(tmp_year);
        Log.d("TestTAG", ""+day_x+"."+month_x+"."+year_x);
    }

    public int getDay_x() {
        return day_x;
    }

    public int getMonth_x() {
        return month_x;
    }

    public int getYear_x() {
        return year_x;
    }
}
