package com.study.android.go_palace;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    private final Handler handler = new Handler();

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent= getIntent();
        String url = intent.getStringExtra("url");
        Log.d(TAG, "url");
        web = findViewById(R.id.web1);

        web = findViewById(R.id.web1);
        web.clearCache(true);                                      // 캐쉬 지우기
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); // 캐쉬 사용하지 않기
        web.getSettings().setJavaScriptEnabled(true);//이너블이 트루인 이유는 개발자가 바이러스 감염 책임지고 열어라~
        web.getSettings().setDefaultTextEncodingName("UTF-8");

        web.loadUrl(url);
//        web.setWebViewClient(new myWebView());
//        web.setWebChromeClient(new myWebChromeClient());
        web.setHorizontalScrollBarEnabled(false); // 세로 scroll 제거
        web.setVerticalScrollBarEnabled(false);    // 가로 scroll 제거
//        web.addJavascriptInterface(new JavaScriptBridge(), "android");

    }
}