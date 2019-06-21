package com.study.android.ex50_googlemap2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    SupportMapFragment mapFragment;
    GoogleMap map;
    MarkerOptions  MyLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }

        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG,"GoogleMap is ready");

                map = googleMap;
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBtnClicked(View v) {
        requestMyLocation();
    }

    private  void  requestMyLocation() {
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        try {
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {

                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );

            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation !=  null) {
                showCurrentLocation(lastLocation);
            }
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );
        }catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        showMyLocationMarker(location);
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    private void showMyLocationMarker(Location location){
        if(MyLocationMarker== null) {
            MyLocationMarker = new MarkerOptions();
            MyLocationMarker.position(new LatLng(location.getLatitude(), location.getLongitude()));
            MyLocationMarker.title("---내위치---\n");
            MyLocationMarker.snippet("GPS로 확인한 위치");
            MyLocationMarker.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker2",200,200)));
            map.addMarker(MyLocationMarker);
        }else{
            MyLocationMarker.position(new LatLng(location.getLatitude(), location.getLongitude()));

        }
    }

//    googleMap.addMarker(new MarkerOptions()
//            .title("New Marker")
//            .snippet("Check out this place.")
//            .position(chelsea).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("image_name",100,100))));

    @Override
    protected void onPause(){
        super.onPause();
        if(map != null){
            map.setMyLocationEnabled(false);
            }
        }
    @Override
    protected void onResume(){
        super.onResume();
        if(map != null){
            map.setMyLocationEnabled(true);
        }
    }

}

