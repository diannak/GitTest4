package com.dog.android.happy_dog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class Dog3Activity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    Button btn_Dog1, btn_Dog2, btn_Dog3;
    private YouTubePlayerView ytpv;
    private YouTubePlayer ytp;
    final String serverKey="AIzaSyD2skYtUalkG5dS5iVAooQHngR0OdC1fcI"; //콘솔에서 받아온 서버키를 넣어줍니다



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog3);
        ytpv = (YouTubePlayerView) findViewById(R.id.youtubeplayer);
        ytpv.initialize(serverKey, this);

    }



    @Override
    public void onInitializationFailure(Provider arg0,
                                        YouTubeInitializationResult arg1) {
        Toast.makeText(this, "Initialization Fail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider,
                                        YouTubePlayer player, boolean wasrestored) {
        ytp = player;

        Intent gt =getIntent();
        ytp.loadVideo(gt.getStringExtra("id"));
    }


}