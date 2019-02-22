package com.example.week2day4hm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DataEntryActivity extends AppCompatActivity {

    public static final String KEY_USER_RESULT = "user_result";
    public static final int RESULT_CODE = 715;


    EditText etNameDisplay;
    EditText etAddressDisplay;
    EditText etCityDisplay;
    EditText etStateDisplay;
    EditText etZipCodeDisplay;
    EditText etPhoneNumberDisplay;
    EditText etEmailDisplay;

    Intent sentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        sentIntent = getIntent();
        bindViews();

    }


    public void bindViews( ){
        etNameDisplay = findViewById(R.id.etName);
        etAddressDisplay = findViewById(R.id.etAddress);
        etCityDisplay = findViewById(R.id.etCity);
        etStateDisplay = findViewById(R.id.etState);
        etZipCodeDisplay = findViewById(R.id.etZipCode);
        etPhoneNumberDisplay = findViewById(R.id.etPhone);
        etEmailDisplay = findViewById(R.id.etEmail);

    }

    public User generateUserObjectFromInput() {
        User returnUser = new User();

        returnUser.setName(
                etNameDisplay.getText() != null ? etNameDisplay.getText().toString() : "");
        returnUser.setAddress(
                etAddressDisplay.getText() != null ? etAddressDisplay.getText().toString() : "");
        returnUser.setCity(
                etCityDisplay.getText() != null ? etCityDisplay.getText().toString() : "");
        returnUser.setState(
                etStateDisplay.getText() != null ? etStateDisplay.getText().toString() : "");
        returnUser.setZipCode(
                etZipCodeDisplay.getText() != null ? etZipCodeDisplay.getText().toString() : "");
        returnUser.setPhoneNumber(
                etPhoneNumberDisplay.getText() != null ? etPhoneNumberDisplay.getText().toString() : "");
        returnUser.setEmail(
                etEmailDisplay.getText() != null ? etEmailDisplay.getText().toString() : "");

    return returnUser;

    }

    public void onClickDataEntry(View view) {
        User userResult = generateUserObjectFromInput();
        Bundle bundleOfTheUserResult = new Bundle();
        bundleOfTheUserResult.putParcelable(KEY_USER_RESULT, userResult);
        sentIntent.putExtras(bundleOfTheUserResult);
        setResult(RESULT_CODE, sentIntent);
        finish();
    }

}