package comstudy.android.ex38_audioex;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private static String TAG="lecture";

    MediaPlayer mp = null;
    int playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //재생
    public void onBtn1Clicked(View v){
        mp =MediaPlayer.create(this, R.raw.old_pop);
        mp.seekTo(0);
        mp.start();
    }
    //일시정지
    public void onBtn2Clicked(View v){
    if(mp != null){
        mp.pause();
        playbackPosition =mp.getCurrentPosition();
        }
    }
    //일시정지 후 재생
    public void onBtn3Clicked(View v){
        if(mp != null){
            mp.seekTo(playbackPosition);
            mp.start();
        }
    }
    //정지
    public void onBtn4Clicked(View v) {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        mp = null;
    }
    //녹음으로~
    public void onBtn5Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
        startActivity(intent);
        }
    }

