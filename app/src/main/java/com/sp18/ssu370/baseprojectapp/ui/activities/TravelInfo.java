package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sp18.ssu370.baseprojectapp.R;

public class TravelInfo extends AppCompatActivity implements GeoTask.Geo {

    public static String url;
    private ImageView clipBoard;
    private ImageView text_page;
    private String durationDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_info);

        //main();

        url = MapsActivity.distanceMatrix();

        new GeoTask(TravelInfo.this).execute(url);
        clipBoard = findViewById(R.id.clipBoard);
        clipBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Travel info", durationDistance);
                clipboard.setPrimaryClip(clip);
            }
        });

        text_page = findViewById(R.id.text_page);
        text_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TravelInfo.this, TextActivity.class));
                }
            });
    }


    public void main(){
        //Toast.makeText(this, "made it into main", Toast.LENGTH_SHORT).show();

        float result = MapsActivity.travelDistance();

        result = (float) (result*0.000621371192);
        int newResult = Math.round(result);
        TextView value = (TextView) findViewById(R.id.valueText);
        value.setText("" + result);
    }

    @Override
    public void setDouble(String result) {
        String res[]=result.split(",");

        durationDistance = "Duration = " + res[0] + ", Distance = " + res[1];

        /*clipBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Travel info", durationDistance);
                clipboard.setPrimaryClip(clip);
            }
        });*/

        TextView value = (TextView) findViewById(R.id.valueText);
        value.setText(res[1]);

        TextView time = (TextView) findViewById(R.id.travelTime);
        time.setText(res[0]);

    }
}
