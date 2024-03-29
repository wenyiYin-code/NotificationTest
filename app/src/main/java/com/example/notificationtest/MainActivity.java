package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String ch_id = "channel_1";

    int importance = NotificationManager.IMPORTANCE_HIGH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        if(id == R.id.send_notice){
            /*新增点击跳转功能*/
            Intent intent = new Intent(this, NotificationActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
            /*通知栏弹出通知功能*/
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(ch_id,"test",importance);
            manager.createNotificationChannel(mChannel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,ch_id);
            builder.setContentTitle("this is title");
            builder.setContentText("this is text");
            builder.setWhen(System.currentTimeMillis());
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
            builder.setContentIntent(pi);/*新增点击跳转功能*/
            builder.setAutoCancel(true);
            manager.notify(1,builder.build());
        }
    }

}