package com.dog.android.happy_dog;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Geocoder;

public class Dog2selectedItem extends AppCompatActivity {

    private String animalCnterNm;
    private String rdnmadr;
    private String phoneNumber;
    private String weekdayOperOpenHhmm;
    private String weekdayOperColseHhmm;
    private String wkendOperOpenHhmm;
    private String wkendOperCloseHhmm;
    private String rstde;


    Intent intent;
    Button button1;
    Context context;
    StringBuffer buffer = new StringBuffer();

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView6;
    TextView textView10;
    Button btn_Dog1, btn_Dog2, btn_Dog3;
    //지도
    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions  MyLocationMarker;
    private String latitude;
    private String hardness;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "happy";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog2_selected_view );

        //파싱
        Intent intent = getIntent();
        animalCnterNm = intent.getStringExtra("animalCnterNm");
        rdnmadr = intent.getStringExtra("rdnmadr");
        phoneNumber = intent.getStringExtra("phoneNumber");
        weekdayOperOpenHhmm = intent.getStringExtra("weekdayOperOpenHhmm");
        weekdayOperColseHhmm = intent.getStringExtra("weekdayOperColseHhmm");
        wkendOperOpenHhmm  = intent.getStringExtra("wkendOperOpenHhmm");
        wkendOperCloseHhmm  = intent.getStringExtra("wkendOperCloseHhmm");
        rstde = intent.getStringExtra("rstde");
        latitude = intent.getStringExtra("latitude");
        hardness  = intent.getStringExtra("hardness");

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView10 = findViewById(R.id.textView10);

        button1 = findViewById(R.id.button1);

        textView1.setText("보호소 : " + animalCnterNm );
        textView2.setText(rdnmadr);
        textView3.setText("전화번호 : " + phoneNumber );
        textView4.setText("평일 운영시간 : " + weekdayOperOpenHhmm +" ~ " + weekdayOperColseHhmm );
        textView10.setText("주말 운영시간 : " + wkendOperOpenHhmm  + " ~ " + wkendOperCloseHhmm );
        textView6.setText("휴무일 : "+ rstde );


        button1.setText(animalCnterNm+ " 입양 문의" );

        //버튼
        //입양
        btn_Dog1 = findViewById(R.id.btn_Dog1);
        btn_Dog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog1Activity.class);
                startActivity(mlntent);
            }
        });
        //보호소
        btn_Dog2 = findViewById(R.id.btn_Dog2);
        btn_Dog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog2Activity.class);
                startActivity(mlntent);
            }
        });
        // 영상 파싱
        btn_Dog3 = findViewById(R.id.btn_Dog3);
        btn_Dog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog3StartActivity.class);
                startActivity(mlntent);
            }
        });
       //----------------------------------------------------------------------------------------------
       //지도 권한
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
    //지도
        private void requestMapDraw() {
        //shelter =  보호소
            LatLng shelter = new LatLng(Double.parseDouble(latitude), Double.parseDouble(hardness));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(shelter, 14f));
            if (MyLocationMarker == null) {
                MyLocationMarker = new MarkerOptions();
                MyLocationMarker.position(shelter);
                MyLocationMarker.title(animalCnterNm);
                MyLocationMarker.snippet("GPS로 확인한 위치");
                map.addMarker(MyLocationMarker);
            } else {
                MyLocationMarker.position(shelter);
            }
        }
    public void onClick1(View v) {   // 메서드 명으로 불러서 실행, tel은 전화 , sns 는 문자 (ACTION_VIEW)
        String b= "tel:";


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:&"+phoneNumber));
        Log.d("lecture", "번호"+b+phoneNumber);
        startActivity(intent);
    }
}
