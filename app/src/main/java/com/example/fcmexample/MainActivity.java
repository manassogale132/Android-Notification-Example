package com.example.fcmexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button notifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifyButton = (Button) findViewById(R.id.notifyButton);

        //code to handle current notificaton click and open same screen
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //build the notification
                NotificationCompat.Builder notification = new NotificationCompat.Builder(MainActivity.this, "ChannelID");
                notification.setContentTitle("Notification Title");
                notification.setContentText("Notification Content Information");
                notification.setSmallIcon(R.drawable.ic_android_black_24dp);
                notification.setAutoCancel(true);
                notification.setColor(Color.parseColor("#fee140"));
                notification.setContentIntent(pendingIntent);

                //if android version is greater than equal to Oreo then notification channel is mandatory
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("ChannelID", "ChannelName", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(notificationChannel);
                }

                //notify the user with the above notification
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(0, notification.build());

            }
        });
    }
}