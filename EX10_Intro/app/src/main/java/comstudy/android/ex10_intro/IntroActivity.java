package comstudy.android.ex10_intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.util.logging.LogRecord;

public class IntroActivity extends AppCompatActivity {
    private static final  String TAG ="lecture";
    private  static final int STOPSPLASH = 0;
    private  static final int SPLASHTIME = 2000;

    private Handler splashHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            Intent intent;
            super.handleMessage(msg);

            switch (msg.what) {
                case STOPSPLASH:
                    intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.hold);
                    finish();
                    break;
            }
        }
    };
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intro);

        }

        @Override
        public void onResume(){
            super.onResume();

        Message msg = new Message();
        msg.what = STOPSPLASH;
        splashHandler.sendMessageDelayed(msg, SPLASHTIME);

        }

    }

