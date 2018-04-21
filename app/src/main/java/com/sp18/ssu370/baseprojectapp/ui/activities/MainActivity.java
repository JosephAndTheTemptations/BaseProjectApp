package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sp18.ssu370.baseprojectapp.R;

public class MainActivity extends AppCompatActivity {

    private Button getLocation;

    private Button setLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLocation = findViewById(R.id.location_button);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

        setLocation = findViewById(R.id.set_location);
        setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //startActivity(new Intent(MainActivity.this, SetDestinationActivity.class));
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

    }
}
