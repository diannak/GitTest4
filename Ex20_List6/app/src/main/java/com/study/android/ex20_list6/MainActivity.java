package com.study.android.ex20_list6;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    //
//    String[] names = {"홍길동","강감찬","을지문덕","양만춘","유관순"};
//    String[] ages = {"20","25","30","35","40"};
//    int[] images = {R.drawable.face1,R.drawable.face2,R.drawable.face3,
//            R.drawable.face1,R.drawable.face2};
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new SingerAdapter(this);

        SingerItem item1 = new SingerItem("홍길동", "010-1234-5678", R.drawable.face1);
        adapter.addItem(item1);

        SingerItem item2 = new SingerItem("이순신", "010-1234-5679", R.drawable.face2);
        adapter.addItem(item2);

        SingerItem item3 = new SingerItem("김유신", "010-1234-5670", R.drawable.face3);
        adapter.addItem(item3);

        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),
                        "selected : " + item.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}