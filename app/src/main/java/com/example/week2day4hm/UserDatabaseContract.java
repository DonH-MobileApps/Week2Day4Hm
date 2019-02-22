package com.example.week2day4hm;

import android.util.Log;

public class UserDatabaseContract {
    public static final String DATABASE_NAME = "USER_DB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "USERS";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_ZIP_CODE = "zip code";
    public static final String COLUMN_PHONE_NUMBER = "phone number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ID = "id";

    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();


        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" (");
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INT NONNULL IDENTITY PRIMARY,KEY ");
        queryBuilder.append(COLUMN_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_ADDRESS);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_CITY);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_STATE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_ZIP_CODE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_PHONE_NUMBER);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_EMAIL);
        Log.d("TAG", "createQuery: " + queryBuilder.toString());

        return queryBuilder.toString();


    }

public static String getAllUsersQuery() {

        return "SELECT = FROM " + TABLE_NAME;

}


public static String getOneUserById(int id) {
    return String.format("SELECT * FROM %s WHERE %s = \"d\"",
            TABLE_NAME, COLUMN_ID, id);
}


}
