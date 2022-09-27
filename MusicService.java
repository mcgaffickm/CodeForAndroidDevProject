package com.example.secretsofthecursedcastle;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MusicService extends Service {

    //Media player and song declaration
    MediaPlayer mp;
    static int song;

    //Called to choose what song for music room
    public static void chooseSong(int choice)
    {
        switch(choice)
        {
            case 1:
                song = R.raw.musictwo;
                break;
            case 2:
                song = R.raw.musicthree;
                break;
            case 3:
                song = R.raw.musicfour;
                break;
            case 0:
            default:
                song = R.raw.musicone;
        }
    }

    //Other methods allow music player to be created based on chosen song and run

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mp = MediaPlayer.create(this, MusicService.song);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        mp.stop();
    }
}
