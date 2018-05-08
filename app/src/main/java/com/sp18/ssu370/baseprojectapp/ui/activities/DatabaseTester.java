package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.sp18.ssu370.baseprojectapp.R;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;

/**
 * Created by josephcriseno on 5/7/18.
 */

public class DatabaseTester extends AppCompatActivity {

    EditText phoneInput;
    EditText messageInput;
    TextView buckysText;
    Button btnAdd;
    Button btnDelete;
    MyDBHandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        btnAdd = (Button) findViewById(R.id.addButton);
        btnDelete = (Button) findViewById(R.id.deleteButton);
        phoneInput = (EditText) findViewById(R.id.phoneInput);
        messageInput = (EditText) findViewById(R.id.messageInput);
        buckysText = (TextView) findViewById(R.id.buckysText);

        dbHandler = new MyDBHandler(this, null, null, 1);
        Log.i("exxxx", "CREATED DATABASE");

        try {
            printDatabase();
        }catch (Exception e){
            Log.i("exxxx", e.toString());
        }
    }

    //Add product to Database
    public void addButtonClicked(View view){
        Log.i("exxxx", "CLİCKED ADD BUTTON");
        SavedMessage SavedMessage = new SavedMessage(phoneInput.getText().toString(),
                messageInput.getText().toString());
        dbHandler.addMessage(SavedMessage);
        Log.i("exxxx", "ADDED MESSAGE");
        printDatabase();
    }

    // Delete product to Database
    public void deleteButtonClicked(View view){
        Log.i("exxxx", "CLİCKED DELETE BUTTON");
        String inputText = phoneInput.getText().toString();
        dbHandler.deleteMessage(inputText);
        printDatabase();


    }

    public void printDatabase(){
        Log.d("exxxx", "printing Database");
        String dbString = dbHandler.databaseToString();
        Log.d("exxxx", "acquired Database");
        buckysText.setText(dbString);
        phoneInput.setText("");
        messageInput.setText("");
        Log.d("exxxx", "displayed Database");
    }
}