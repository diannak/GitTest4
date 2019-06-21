package com.study.android.ex48_geocoder;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecutre";

    Geocoder coder;
    TextView result;
    EditText etLatitude;
    EditText etLongtitude;
    EditText etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coder = new Geocoder(this);
        result = findViewById(R.id.result);
        etAddress = findViewById(R.id.address);
    //  etLatitude = findViewById(R.id.lattitude);
    // etLongitude = findViewById(R.id.longitude);
    }
/*
    public void onBtn1Clicekd(View v) {
        List<Address> list = null;
        String lattitude = etLatitude.getText().toString();//입력한 위도
        String longitude = etLongitude.getText().toString();//입력한 경도
        //입력한 위도 경도를 list에 넣고
        try {
            list = coder.getFromLocation(
                    Double.parseDouble(lattitude),
                    Double.parseDouble(longitude),
                    10);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            tvResult.setText("에러 :" + e.getMessage());
            e.printStackTrace();
        }
        if (list != null) {//코딩결과가 든list를 tvResult에 값을 출력한다.
            tvResult.setText(list.get(0).toString());
        }
        Log.d("LatLng", lattitude + ',' + longitude);

    }
*/
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
          result.setText(list.get(0).getLatitude() + " " + list.get(0).getLongitude());
          result.setText(list.get(0).toString());//내용 전부 출력
//                Longitude.setText(list.get(0).getLatitude());
//                Latitude.setText(list.get(0).getLongitude());
        }
            Log.d("주소", address);

        }
    }
