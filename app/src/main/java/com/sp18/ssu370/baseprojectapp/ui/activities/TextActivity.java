package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.app.AlarmManager;

import com.sp18.ssu370.baseprojectapp.R;

import java.util.concurrent.TimeUnit;
import android.app.DatePickerDialog;


import java.util.List;

import static android.Manifest.permission.SEND_SMS;

//import java.util.ArrayList;
//import java.text.DateFormat;
//import java.util.Date;





public class TextActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final int REQUEST_SMS = 0;
    private static final int REQ_PICK_CONTACT = 2;
    private EditText phoneEditText;
    private EditText messageEditText;

    private Button sendButton;
    private ImageView pickContact;
    private TextView sendStatusTextView;
    //private TextView deliveryStatusTextView;
    private ImageView pickTime;
    private ImageView pickDate;
    //private Button chooseTime;
    String amPm;
    int currentHour;
    Long user_hr_in_millis;
    Long user_min_in_millis;
    Long desired_time;

    Long total_time;

    int desired_year;
    int desired_month;
    int desired_day;

    int current_year;
    int current_month;
    int current_day;




    private BroadcastReceiver sentStatusReceiver, deliveredStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_main);

        pickTime = (ImageView) findViewById(R.id.add_contact_image_view2);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }

//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment datePicker = new DatePickerFragment();
//                datePicker.show(getSupportFragmentManager(), "date picker");
//                }
//
//            }
        });
        pickDate = (ImageView) findViewById(R.id.calendar_image_view);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                }
        });



                //chooseTime = (Button) findViewById(R.id.pick_time);
        phoneEditText = (EditText) findViewById(R.id.phone_number_edit_text);
        messageEditText = (EditText) findViewById(R.id.message_edit_text);
        sendButton = (Button) findViewById(R.id.send_button);
        sendStatusTextView = (TextView) findViewById(R.id.message_status_text_view);
        //deliveryStatusTextView = (TextView) findViewById(R.id.delivery_status_text_view);
        pickContact = (ImageView) findViewById(R.id.add_contact_image_view);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    int hasSMSPermission = checkSelfPermission(Manifest.permission.SEND_SMS);
                    if (hasSMSPermission != PackageManager.PERMISSION_GRANTED) {
                        if (!shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                            showMessageOKCancel("You need to allow access to Send SMS",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                                                        REQUEST_SMS);
                                            }
                                        }
                                    });
                            return;
                        }
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                                REQUEST_SMS);
                        return;
                    }
                    //sendMySMS();
                    //String phone = phoneEditText.getText().toString();
                    //String message = messageEditText.getText().toString();
                    scheduleText();
                }
            }
        });

        pickContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 2);
            }
        });
    }

//    public static class TimePickerFragment extends DialogFragment
//            implements TimePickerDialog.OnTimeSetListener {
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Use the current time as the default values for the picker
//            final Calendar c = Calendar.getInstance();
//            int hour = c.get(Calendar.HOUR_OF_DAY);
//            int minute = c.get(Calendar.MINUTE);
//
//            // Create a new instance of TimePickerDialog and return it
//            return new TimePickerDialog(getActivity(), this, hour, minute,
//                    DateFormat.is24HourFormat(getActivity()));
//        }
//
//        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            // Do something with the time chosen by the user
//            long user_hour = TimeUnit.HOURS.toMillis(hourOfDay);
//            long user_minute = TimeUnit.MINUTES.toMillis(hourOfDay);
//
//            long desired_time = user_hour + user_minute;
//
//
//        }
//    }

//    public void showTimePickerDialog(View v) {
//        //DialogFragment newFragment = new TimePickerFragment();
//        TimePickerFragment newFragment = new TimePickerFragment();
//        newFragment.show(getSupportFragmentManager(), "timePicker");
//
//        //long desired_time = user_hour + user_minute;
//    }


    @Override
    public void onDateSet(DatePicker view, int targetYear, int targetMonth, int targetdayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, targetYear);
        c.set(Calendar.MONTH, targetMonth);
        c.set(Calendar.DAY_OF_MONTH, targetdayOfMonth);
        String currentDateString = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL).format(c.getTime());
        TextView textView_2 = (TextView) findViewById(R.id.textView_2);
        textView_2.setText(currentDateString);

        //desired_year = year;

        //desired_month = month;
        //desired_day = dayOfMonth;

        Calendar y = Calendar.getInstance();
        current_year = y.get(Calendar.YEAR);
        current_month = y.get(Calendar.MONTH);
        current_day = y.get(Calendar.DAY_OF_MONTH);

        //int yearUntilAlarm = targetYear - current_year;

        //long years_in_millis = yearUntilAlarm;
        //years_in_millis = DateUtils.YEAR_IN_MILLIS;
        //long years_in_millis = DateUtils.YEAR_IN_MILLIS(yearUntilAlarm);
        //long years_in_millis = DateUtils.DAY_IN_MILLIS
        //long years = Y
    }

    @Override
    public void onTimeSet(TimePicker view, int targetHourOfDay, int targetMinute) {

        TextView textView = (TextView) findViewById(R.id.textView);
        if (targetHourOfDay >= 12) {
            amPm = "PM";
        }
        else {
            amPm = "AM";
        }

        if (targetHourOfDay > 12) {
            currentHour = targetHourOfDay - 12;
        }
        else if (targetHourOfDay == 0) {
            currentHour = 12;
        }
        else {
            currentHour = targetHourOfDay;
        }

        //textView.setText(String.format("%02d:%02d", currentHour, targetMinute) + ' ' + amPm);

        if (DateFormat.is24HourFormat(this)) {
            textView.setText(String.format("%02d:%02d", targetHourOfDay, targetMinute));
            }
        else {
            textView.setText(String.format("%02d:%02d", currentHour, targetMinute) + ' ' + amPm);
        }



        user_hr_in_millis = TimeUnit.HOURS.toMillis(targetHourOfDay);

        user_min_in_millis = TimeUnit.MINUTES.toMillis(targetMinute);

        Calendar x = Calendar.getInstance();
        int currentHour = x.get(Calendar.HOUR_OF_DAY);
        int currentMinute = x.get(Calendar.MINUTE);

        int hoursUntilAlarm = targetHourOfDay - currentHour;
        int minuteUntilAlarm = targetMinute - currentMinute;

        long millisUntilAlarm =
                TimeUnit.HOURS.toMillis(hoursUntilAlarm)
                        + TimeUnit.MINUTES.toMillis(minuteUntilAlarm);

        long millisOfAlarmTime = System.currentTimeMillis() + millisUntilAlarm;

        total_time = millisOfAlarmTime;

    }



    public void sendMySMS(String phone, String message) { // in parameters put String phone, String message

        // String phone = phoneEditText.getText().toString();
        //String message = messageEditText.getText().toString();

        //Check if the phoneNumber is empty
        if (phone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();

        } else {

            SmsManager sms = SmsManager.getDefault();
            // if message length is too long messages are divided
            List<String> messages = sms.divideMessage(message);
            for (String msg : messages) {

                PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
                PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);
                sms.sendTextMessage(phone, null, msg, sentIntent, deliveredIntent);

            }
        }
    }


    public void scheduleText() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        intentAlarm.putExtra("PHONE_EDIT_TEXT", phoneEditText.getText().toString());
        intentAlarm.putExtra("MESSAGE_EDIT_TEXT", messageEditText.getText().toString());


        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, total_time, pendingIntent);
            Toast.makeText(this, "Message Scheduled", Toast.LENGTH_LONG).show();
        }

        else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, total_time, pendingIntent);
            Toast.makeText(this, "Message Scheduled", Toast.LENGTH_LONG).show();

        }
    }



    public void onResume() {
        super.onResume();
        sentStatusReceiver=new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String s = "Unknown Error";
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        s = "Message Sent Successfully !!";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        s = "Generic Failure Error";
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        s = "Error : No Service Available";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        s = "Error : Null PDU";
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        s = "Error : Radio is off";
                        break;
                    default:
                        break;
                }
                sendStatusTextView.setText(s);

            }
        };
        deliveredStatusReceiver=new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String s = "Message Not Delivered";
                switch(getResultCode()) {
                    case Activity.RESULT_OK:
                        s = "Message Delivered Successfully";
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                }
                //deliveryStatusTextView.setText(s);
                phoneEditText.setText("");
                messageEditText.setText("");
            }
        };
        registerReceiver(sentStatusReceiver, new IntentFilter("SMS_SENT"));
        registerReceiver(deliveredStatusReceiver, new IntentFilter("SMS_DELIVERED"));
    }


    public void onPause() {
        super.onPause();
        unregisterReceiver(sentStatusReceiver);
        unregisterReceiver(deliveredStatusReceiver);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    private boolean checkPermission() {
        return ( ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS ) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{SEND_SMS}, REQUEST_SMS);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SMS:
                if (grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access sms", Toast.LENGTH_SHORT).show();
                    String phone = phoneEditText.getText().toString();
                    String message = messageEditText.getText().toString();
                    //sendMySMS(phone, message); // in parameter put phone, message
                    scheduleText();

                }else {
                    Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and sms", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(SEND_SMS)) {
                            showMessageOKCancel("You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{SEND_SMS},
                                                        REQUEST_SMS);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(TextActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_PICK_CONTACT) {
            if (resultCode == RESULT_OK) {
                Uri contactData = data.getData();
                Cursor cursor = managedQuery(contactData, null, null, null, null);
                cursor.moveToFirst();

                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phoneEditText.setText(number);
            }
        }
    }


}

