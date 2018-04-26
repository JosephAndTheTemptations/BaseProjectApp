package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sp18.ssu370.baseprojectapp.R;
import com.sp18.ssu370.baseprojectapp.SettingsActivity;

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
                Toast.makeText(getApplicationContext(), "Map", Toast.LENGTH_SHORT).show();
                // Go to the map
                break;
            case R.id.action_settings:
                // Go To Settings
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.create_message:
                Toast.makeText(getApplicationContext(), "Messages", Toast.LENGTH_SHORT).show();
                // Go to messages
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
