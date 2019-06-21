package com.study.android.go_palace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

import net.daum.android.map.MapView;

public class GotoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="lecture";
    Button button;

    WebView web;
    Intent intent;
    Button go_navi;
    MapView daummap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto);

        intent = getIntent();
        String title = intent.getStringExtra("title");
        daummap = findViewById(R.id.map_view2);

        String lat = intent.getStringExtra("lat");
        String longi = intent.getStringExtra("longi");

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        Log.d (TAG, title+ lat+ longi);
        web = findViewById(R.id.web1);
        web.clearCache(true);
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDefaultTextEncodingName("UTF-8");
        web.setHorizontalScrollBarEnabled(false);
        web.setVerticalScrollBarEnabled(false);
        daummap.setVisibility(View.GONE);

        web.loadUrl("http://192.168.0.90:8081/JspServer/tmap.jsp?lat="+ lat + "&longi=" + longi + "&title=" + title);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            web.setVisibility(View.GONE);
            daummap.setVisibility(View.VISIBLE);
            Location destination = Location.newBuilder("경복궁", 126.976912  , 37.576267).build();
            NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(
                    VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();
            KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
            KakaoNaviParams params = builder.build();
            KakaoNaviService.navigate(GotoActivity.this, builder.build());
        }
    }
}
