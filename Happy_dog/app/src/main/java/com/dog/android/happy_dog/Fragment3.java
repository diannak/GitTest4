package com.dog.android.happy_dog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/* 페이지 설명 : 봉사관련 웹뷰 */

public class Fragment3 extends Fragment {
    private static final String TAG = "happy";
    public static WebView web;

    private final Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);

        web = rootView.findViewById(R.id.web);
        web.clearCache(true);                                      // 캐쉬 지우기
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); // 캐쉬 사용하지 않기
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDefaultTextEncodingName("UTF-8");

        web.loadUrl("http://192.168.219.107:8081/guest/volunteer_sub1"); //@아마존 연결시 링크수정
        //web.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfbsDYlFSOvASqZCixlJb4eKDne9qZeivUYK-41iWgG_Q3nSQ/viewform"); //@아마존 연결시 링크수정
        web.setWebViewClient(new myWebView());
        web.setWebChromeClient(new myWebChromeClient());
        web.setHorizontalScrollBarEnabled(false); // 세로 scroll 제거
        web.setVerticalScrollBarEnabled(false);    // 가로 scroll 제거
        web.addJavascriptInterface(new JavaScriptBridge(), "android");

        return rootView;
    }

    // 웹뷰에서 자바스크립트의 Alert/Confirm을 보여 줄 수 있도록 한다.
    private class myWebChromeClient extends WebChromeClient
    {
        @Override
        public boolean onJsAlert(WebView view, String url, String message,
                                 final android.webkit.JsResult result){
            result.confirm();

            new AlertDialog.Builder(view.getContext())
                    //.setIcon(R.drawable.icon);
                    //.setTitle(R.string.title_activity_main)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new AlertDialog.OnClickListener(){
                                public void onClick( DialogInterface dialog, int which) {
                                    result.confirm();
                                }
                            })
                    .setCancelable(true)
                    .create()
                    .show();

            return true;
        };

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   final android.webkit.JsResult result)
        {
            new AlertDialog.Builder(view.getContext())
                    //.setTitle(R.string.title_activity_main)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    result.confirm();
                                }
                            })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    result.cancel();
                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();

            return true;
        };
    }

    //동작을 후킹한다.
    private class myWebView extends WebViewClient {

        /* 웹페이지 로딩중 에러가 발생했을 때 처리 */
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            Toast.makeText(getActivity().getApplicationContext(),
                    "Loading Error : " + description,
                    Toast.LENGTH_SHORT).show();
        }
    }

    // 자바스크립트에서 Call할 클래스 함수
    final class JavaScriptBridge {

        @JavascriptInterface
        public void test(){
            handler.post(new Runnable() {
                public void run() {
                    Toast.makeText(getActivity().getApplicationContext(), "테스트", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Parameter must be final
        @JavascriptInterface
        public void testParams(final String arg){
            handler.post(new Runnable() {
                public void run() {
                    Log.d(TAG, "setMessage("+arg+")");
                    Toast.makeText(getActivity().getApplicationContext(), "테스트파라미터:"+arg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        web.stopLoading();
    }
}


