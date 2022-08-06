package com.aman.contacttask.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aman.contacttask.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle("Details");

    }
}