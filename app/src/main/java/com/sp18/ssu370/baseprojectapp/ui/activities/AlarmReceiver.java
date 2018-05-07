package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String phone_text = intent.getStringExtra("PHONE_EDIT_TEXT");
        String message = intent.getStringExtra("MESSAGE_EDIT_TEXT");
        sendMySMS(phone_text, message , context); // in parameters put phone_text, message
    }

    private void sendMySMS(String phone, String message, Context context) {

        //Check if the phoneNumber is empty
        if (phone.isEmpty()) {
            Toast.makeText(context, "Please Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
        } else {

            SmsManager sms = SmsManager.getDefault();
            // if message length is too long messages are divided
            List<String> messages = sms.divideMessage(message);
            for (String msg : messages) {

                PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent("SMS_SENT"), 0);
                PendingIntent deliveredIntent = PendingIntent.getBroadcast(context, 0, new Intent("SMS_DELIVERED"), 0);
                sms.sendTextMessage(phone, null, msg, sentIntent, deliveredIntent);

            }
        }
    }
}




