package com.aman.contacttask.ui.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aman.contacttask.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

    }
}