package com.dog.android.happy_dog;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dog.android.happy_dog.fineweather.FineWeatherContract;
import com.dog.android.happy_dog.fineweather.FineWeatherFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "happy";

    ViewPager viewPager;
    TabLayout tabLayout;

    private LinearLayout LL0;

    //-- 위치 날씨 온도 -----------------------------------------------------//
    public static final int REQUEST_CODE_FINE_COARSE_PERMISSION = 1000;
    private ViewPager mViewPager;
    private List<Pair<Fragment, String>> mFragmentList;
    private FusedLocationProviderClient mFusedLocationClient;
    //----------------------------------------------------- 위치 날씨 온도 --//

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabMenu);

        PageAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected( TabLayout.Tab tab ) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected( TabLayout.Tab tab ) {

            }

            @Override
            public void onTabReselected( TabLayout.Tab tab ) {

            }
        });

        //* 위치 날씨 온도 *//*
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        setUpViewPager();

        //* 날씨 화면 변환 *//*
        LL0 = findViewById(R.id.LL0);
        //* 버튼 작업이 안됨*//*
        LL0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "날씨화면으로", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //-- 위치 날씨 온도 -----------------------------------------------------//
    private void setUpViewPager() {
        mViewPager = findViewById(R.id.view_Pager);
        loadDbData();
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
    }
    private void loadDbData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new Pair<Fragment, String>(
                new FineWeatherFragment(), "현재 위치"
        ));
    }
    public void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_FINE_COARSE_PERMISSION );
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            FineWeatherContract.View view = (FineWeatherContract.View) mFragmentList.get(0).first;
                            view.reload(location.getLatitude(), location.getLongitude());
                        }
                    }
                });
    }
    private static class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Pair<Fragment, String>> mFragmentList;

        public MyPagerAdapter( FragmentManager fm, List<Pair<Fragment, String>> fragmentList) {
            super(fm);
            mFragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position).first;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentList.get(position).second;
        }
    }
    @Override
    public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //-- 위치 날씨 온도 --//
        if (requestCode == REQUEST_CODE_FINE_COARSE_PERMISSION){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getLastKnownLocation();
            }
        }
    }
    //---------------------------------------------------------------------------------------------------- 위치 날씨 온도 --//

    //-- Fragment3에서 이전키 눌렀을 때 WebView 히스토리 back ---------------------------------------------------------------//
    @Override
    public void onBackPressed() {
        if (Fragment3.web.canGoBack()) {
            try {
                Fragment3.web.goBack();  //only webview back-key code
            } catch (Exception e) {
                //back-key code of another fragment
            }
        } else {
            // back-key code of another fragment
        }
    }

}
