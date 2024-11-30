package com.example.electric;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Enable the back icon in the ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Electricity Bill Estimator"); // Set title
            actionBar.setDisplayHomeAsUpEnabled(true);        // Show back icon
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle the back icon click
        if (item.getItemId() == android.R.id.home) {
            finish(); // Go back to the previous screen
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


