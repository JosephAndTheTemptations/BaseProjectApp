package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sp18.ssu370.baseprojectapp.R;

public class TravelInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_info);

        main();
    }


    public void main(){
        Toast.makeText(this, "made it into main", Toast.LENGTH_SHORT).show();

        TextView value = (TextView) findViewById(R.id.valueText);
        value.setText("this is text");
    }
}
