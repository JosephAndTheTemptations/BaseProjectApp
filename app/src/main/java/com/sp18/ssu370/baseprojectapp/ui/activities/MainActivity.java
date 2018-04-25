package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sp18.ssu370.baseprojectapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://i.imgur.com/v4I8yu6.jpg");
        mNames.add("Joseph Criseno");

        mImageUrls.add("https://i.imgur.com/D8m6jc4.jpg");
        mNames.add("Christian Havranek");

        mImageUrls.add("https://i.imgur.com/KfBNZn3.jpg");
        mNames.add("Soumana Sylla");

        mImageUrls.add("https://i.imgur.com/7oXwJ8T.jpg");
        mNames.add("Hunter Straub");

        mImageUrls.add("https://i.imgur.com/AcNgpeu.jpg");
        mNames.add("Iron Man");

        mImageUrls.add("https://i.imgur.com/Y596dha.jpg");
        mNames.add("Seth Rogen");



        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
