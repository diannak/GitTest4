package com.dog.android.happy_dog;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Support1ActivityChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_support1_chat );
    }
    public void onClick1(View v) {   // 메서드 명으로 불러서 실행
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pf.kakao.com/_Vfmxoj/chat "));
        startActivity(intent);
    }

}
