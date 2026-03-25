package com.example.naturewalkstracker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AmbientSoundService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, "ambient_channel")
                .setContentTitle("Nature Walks Tracker")
                .setContentText("Playing ambient nature sounds")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        mediaPlayer = MediaPlayer.create(this, R.raw.nature_ambient);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        return START_STICKY;
    }

    private void createNotificationChannel(){
        NotificationChannel notificationChannel = new NotificationChannel(
                       "ambient_channel",
                "Nature Walks Tracker",
                NotificationManager.IMPORTANCE_LOW);

        notificationChannel.setDescription("Playing ambient nature sounds");

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(notificationChannel);

    }


}
