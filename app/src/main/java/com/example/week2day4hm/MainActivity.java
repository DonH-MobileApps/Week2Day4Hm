package com.example.week2day4hm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FOR_MAIN = 421;
    public static final String KEY_SHARED_PREF = "shared_pref";
    public static final String KEY_LAST_ENTERED_NAME = "last_name";
    public static final String KEY_LAST_ENTERED_ADDRESS = "last_address";


    TextView tvNameDisplay;
    TextView tvAddressDisplay;
    TextView tvCityDisplay;
    TextView tvStateDisplay;
    TextView tvZipCodeDisplay;
    TextView tvPhoneNumberDisplay;
    TextView tvEmailDisplay;

    SharedPreferences sharedPreferences;

    UserDatabaseHelper userDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE);

        userDatabaseHelper = new UserDatabaseHelper(this);
        bindViews();


    }



    public void bindViews() {

        tvNameDisplay = findViewById(R.id.tvName);
        tvAddressDisplay = findViewById(R.id.tvAddress);
        tvCityDisplay = findViewById(R.id.tvCity);
        tvStateDisplay = findViewById(R.id.tvState);
        tvZipCodeDisplay = findViewById(R.id.tvZipCode);
        tvPhoneNumberDisplay = findViewById(R.id.tvPhone);
        tvEmailDisplay = findViewById(R.id.tvEmail);

}

public void populateTextViews(@NonNull User userInfoToPopulate){
        tvNameDisplay.setText(userInfoToPopulate.getName());
        tvAddressDisplay.setText(userInfoToPopulate.getAddress());
        tvCityDisplay.setText(userInfoToPopulate.getCity());
        tvStateDisplay.setText(userInfoToPopulate.getState());
        tvZipCodeDisplay.setText(userInfoToPopulate.getZipCode());
        tvPhoneNumberDisplay.setText(userInfoToPopulate.getPhoneNumber());
        tvEmailDisplay.setText(userInfoToPopulate.getEmail());


}


    public void onClickDataResult(View view) {
        switch (view.getId()) {
            case R.id.btnStartForResult:
                Intent explicitIntent = new Intent(this, DataEntryActivity.class);
                startActivityForResult(explicitIntent, REQUEST_CODE_FOR_MAIN);
                break;

        }
    }

public void saveNameAddressToSharedPref(User user){
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(KEY_LAST_ENTERED_NAME, user.getName());
        sharedPrefEditor.putString(KEY_LAST_ENTERED_ADDRESS, user.getAddress());
        sharedPrefEditor.commit();


}



public void saveAndLogUserInSharedPref(@NonNull User user){
        String name = sharedPreferences.getString(KEY_LAST_ENTERED_NAME, "NO VALUE ENTERED");
        String address = sharedPreferences.getString(KEY_LAST_ENTERED_ADDRESS, "NO VALUE ENTERED");

    Log.d(
            "TAG",
                    "saveAndLogUserInSharedPref: IN SHARED PREF: name = "
            + name + "  | address = " + address);


    saveNameAddressToSharedPref(user);

    name = sharedPreferences.getString(KEY_LAST_ENTERED_NAME, "NO VALUE ENTERED");
    address = sharedPreferences.getString(KEY_LAST_ENTERED_ADDRESS, "NO VALUE ENTERED");

    Log.d(

            "TAG",
            "saveAndLogUserInSharedPref: IN SHARED PREF: name = " + name + " | address = "
            + address);

}

    public void saveUserToDatabaseAndViewLog(@NonNull User user) {
        userDatabaseHelper.insertUserIntoDatabase(user);
        ArrayList<User> userList = userDatabaseHelper.getAllUsersFromDatabase();
        for (User currentUser :userList){
            Log.d("TAG", currentUser.toString());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    if (data != null) {
        Bundle bundle = data.getExtras();
        if(bundle != null) {
            User user = bundle.getParcelable( "user");
            if (user != null){
                saveNameAddressToSharedPref(user);
                saveUserToDatabaseAndViewLog(user);
                populateTextViews(user);
            }
        }

    }


    }
}
