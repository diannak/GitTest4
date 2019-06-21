package com.dog.android.happy_dog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment {
    private static final String TAG = "happy";

    Button btn_Dog1, btn_Dog2, btn_Dog3, btn_Dog4, btn_Dog5, btn_Dog6 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        // 유기견 파싱
        btn_Dog1 = rootView.findViewById(R.id.btn_Dog1);
        btn_Dog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button1 -> 유기견 파싱");

                Intent mlntent = new Intent(getActivity(), Dog1Activity.class);
                startActivity(mlntent);
            }
        });

        // 보호소 파싱
        btn_Dog2 = rootView.findViewById(R.id.btn_Dog2);
        btn_Dog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button2 -> 보호소 파싱");
                Intent mlntent = new Intent(getActivity(), Dog2Activity.class);
                startActivity(mlntent);
            }
        });
        // 영상 파싱
        btn_Dog3 = rootView.findViewById(R.id.btn_Dog3);
        btn_Dog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button3 -> 영상 파싱");
                Intent mlntent = new Intent(getActivity(), Dog3StartActivity.class);
                startActivity(mlntent);
            }
        });

        btn_Dog5 = rootView.findViewById(R.id.btn_Dog5);
        btn_Dog5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button4 -> 영상 파싱");
                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:15770954"));
                startActivity(mlntent);
            }
        });

        btn_Dog6 = rootView.findViewById(R.id.btn_Dog6);
        btn_Dog6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "button5 -> 영상 파싱");
                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.animal.go.kr"));
                startActivity(mlntent);
            }
        });


        return rootView;
    }
}
