package com.example.ivan.smartas;

/**
 * Created by Ivan on 28.09.2017.
 */

public class UserDataClass {
    private static String USER_ID_STR = "";
    private static String USER_NAME = "";
    private static int USER_ID_INT = 0;

    public static String getUserIdStr() {
        return USER_ID_STR;
    }

    public static void setUserIdStr(String userIdStr) {
        USER_ID_STR = userIdStr;
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public static void setUserName(String userName) {
        USER_NAME = userName;
    }

    public static int getUserIdInt() {
        return USER_ID_INT;
    }

    public static void setUserIdInt(int userIdInt) {
        USER_ID_INT = userIdInt;
    }
}
