package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.Window;
import android.widget.Toast;


import com.sp18.ssu370.baseprojectapp.R;

import java.util.ArrayList;

import static com.sp18.ssu370.baseprojectapp.R.id.app_bar;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This will change the color of the Status Bar
        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        Toolbar myToolbar = (Toolbar) findViewById(app_bar);

        setSupportActionBar(myToolbar);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.map_settings:
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                // Go to the map
                break;
            case R.id.action_settings:
                // Go To Settings
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.create_message:
                startActivity(new Intent(MainActivity.this, TextActivity.class));
                //Toast.makeText(getApplicationContext(), "Messages", Toast.LENGTH_SHORT).show();
                // Go to messages
                break;
            case R.id.database_tester:
                Intent database = new Intent(this, DatabaseTester.class);
                startActivity(database);
                //Toast.makeText(getApplicationContext(), "Database", Toast.LENGTH_SHORT).show();
                break;

            default:
                // Error
        }

        return super.onOptionsItemSelected(item);
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
