package com.study.android.ex41_service1;
//바인드 : 화면은 없고 동작만하는
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClicked(View v){
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
    }
}

