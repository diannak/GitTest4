package com.study.android.myapplication;
import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    Geocoder coder;
    TextView result;
    EditText etAddress;

    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions MyLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coder = new Geocoder(this);
        result = findViewById(R.id.result);
        etAddress = findViewById(R.id.address);

        //권한 허가
        if (ContextCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        //맵 띄우기
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "GoogleMap is ready");

                map = googleMap;
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBtn2Clicekd(View v) {
        List<Address> list = null;
        String address = etAddress.getText().toString();

        try {
            list = coder.getFromLocationName(address, 10);

        } catch (IOException e) {
            result.setText("에러 :" + e.getMessage());
            e.printStackTrace();
        }
        if (list != null) {
            //result.setText(list.get(0).toString());//내용 전부 출력
           // result.setText(list.get(0).getLatitude() + " " + list.get(0).getLongitude());

            Double Latitude = list.get(0).getLatitude();
            Double Longitude = list.get(0).getLongitude();

            Log.d("위도", Double.toString(Latitude));
            Log.d("경도", Double.toString(Longitude));

            requestMapDraw(Latitude, Longitude);
        }
        Log.d("주소", address);

    }

    //지도 마커찍기
    private void requestMapDraw(Double Latitude, Double Longitude) {

        //잃어버린 장소 = 사용자 입력값 'rPlace' = "txt_rPlace"
        LatLng MPlace = new LatLng(Latitude, Longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(MPlace, 14f));
        if (MyLocationMarker == null) {
            MyLocationMarker = new MarkerOptions();
            MyLocationMarker.position(MPlace);
            MyLocationMarker.title("실종된 위치");
            MyLocationMarker.snippet("GPS로 확인한 위치");
            map.addMarker(MyLocationMarker);
        } else {
            MyLocationMarker.position(MPlace);
        }
    }
}
