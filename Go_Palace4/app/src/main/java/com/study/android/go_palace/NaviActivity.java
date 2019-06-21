package com.study.android.go_palace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

public class NaviActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            Location destination = Location.newBuilder("경복궁", 126.976912  , 37.576267).build();
            NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(
                    VehicleType.FIRST).setRpOption(RpOption.SHORTEST).build();
            KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
            KakaoNaviParams params = builder.build();
            KakaoNaviService.navigate(NaviActivity.this, builder.build());
        }
    }
}

