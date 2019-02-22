package com.example.week2day4hm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.week2day4hm.UserDatabaseContract.COLUMN_ADDRESS;
import static com.example.week2day4hm.UserDatabaseContract.COLUMN_CITY;
import static com.example.week2day4hm.UserDatabaseContract.COLUMN_EMAIL;
import static com.example.week2day4hm.UserDatabaseContract.COLUMN_ID;
import static com.example.week2day4hm.UserDatabaseContract.COLUMN_NAME;
import static com.example.week2day4hm.UserDatabaseContract.COLUMN_PHONE_NUMBER;
import static com.example.week2day4hm.UserDatabaseContract.COLUMN_STATE;
import static com.example.week2day4hm.UserDatabaseContract.COLUMN_ZIP_CODE;
import static com.example.week2day4hm.UserDatabaseContract.DATABASE_VERSION;
import static com.example.week2day4hm.UserDatabaseContract.TABLE_NAME;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    public UserDatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDatabaseContract.createQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }


    public long insertUserIntoDatabase(@NonNull User user) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_ADDRESS, user.getAddress());
        contentValues.put(COLUMN_CITY, user.getCity());
        contentValues.put(COLUMN_STATE, user.getState());
        contentValues.put(COLUMN_ZIP_CODE, user.getZipCode());
        contentValues.put(COLUMN_PHONE_NUMBER, user.getPhoneNumber());
        contentValues.put(COLUMN_EMAIL, user.getEmail());
        contentValues.put(COLUMN_ID, user.getUserId());

        return writableDatabase.insert(TABLE_NAME, null, contentValues);

    }

    public ArrayList<User> getAllUsersFromDatabase(){
        ArrayList<User> returnUserList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();

        Cursor cursor = readableDatabase.rawQuery(UserDatabaseContract.getAllUsersQuery(), null);

        if(cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
                String zipCode = cursor.getString(cursor.getColumnIndex(COLUMN_ZIP_CODE));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

                returnUserList.add(new User(name,address,city,state,zipCode,phoneNumber,email,id));
            }while (cursor.moveToNext());


        }

        cursor.close();
        return  returnUserList;
    }

        public User getUserById(int id) {
            SQLiteDatabase readableDatabase = this.getReadableDatabase();

            User returnUser = new User();

            Cursor cursor = readableDatabase.rawQuery(UserDatabaseContract.getOneUserById(id),null);
            if (cursor.moveToFirst()){
                int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
                String zipCode = cursor.getString(cursor.getColumnIndex(COLUMN_ZIP_CODE));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

                returnUser = new  User(name,address,city,state,zipCode,phoneNumber,email,idFromDB);

            }
            cursor.close();
            return returnUser;

        }


}
