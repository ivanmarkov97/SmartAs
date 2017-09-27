package com.example.ivan.smartas;

/**
 * Created by Ivan on 20.09.2017.
 */

public class Filter {
    static private String subject = "All";
    static private String type = "Все типы";
    static private boolean isASK = false;
    static private boolean byDate = true;
    static private boolean byCost = false;
    static private boolean byLimit = false;
    static private int minCost = 0;
    static private int maxCost = 0;

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        Filter.subject = subject;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Filter.type = type;
    }

    public static boolean isASK() {
        return isASK;
    }

    public static void setIsASK(boolean isASK) {
        Filter.isASK = isASK;
    }

    public static boolean isByDate() {
        return byDate;
    }

    public static void setByDate(boolean byDate) {
        Filter.byDate = byDate;
    }

    public static boolean isByCost() {
        return byCost;
    }

    public static void setByCost(boolean byCost) {
        Filter.byCost = byCost;
    }

    public static boolean isByLimit() {
        return byLimit;
    }

    public static void setByLimit(boolean byLimit) {
        Filter.byLimit = byLimit;
    }

    public static int getMinCost() {
        return minCost;
    }

    public static void setMinCost(int minCost) {
        Filter.minCost = minCost;
    }

    public static int getMaxCost() {
        return maxCost;
    }

    public static void setMaxCost(int maxCost) {
        Filter.maxCost = maxCost;
    }
}
