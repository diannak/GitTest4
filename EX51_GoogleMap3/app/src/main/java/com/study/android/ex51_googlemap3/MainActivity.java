package com.study.android.ex51_googlemap3;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    private static final LatLng SEOUL = new LatLng(37.575936899999995, 126.9768157);
    private static final LatLng UIJEONGBU = new LatLng(37.5785225, 126.97698450000001);
    private static final LatLng DAEJEON = new LatLng(37.5790885, 126.977044);


    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions  MyLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "GoogleMap is ready");

                map = googleMap;

                requestMapDraw();
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestMapDraw() {
        //폴리라인 그리기
        PolylineOptions myLine = new PolylineOptions().width(5).color(Color.BLACK);
        map.addPolyline((myLine).add(SEOUL, UIJEONGBU, DAEJEON));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(UIJEONGBU, 7.0f));
        if(MyLocationMarker== null) {
            MyLocationMarker = new MarkerOptions();
            MyLocationMarker.position(UIJEONGBU);
            MyLocationMarker.title("---내위치---\n");
            MyLocationMarker.snippet("GPS로 확인한 위치");
            map.addMarker(MyLocationMarker);
        }else {
            MyLocationMarker.position(UIJEONGBU);
        }
    }

}




