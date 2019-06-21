package com.study.android.go_palace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="lectrue";
    //firebase auth
    //private  static final int RC_SIGN_IN = 1001;

    //행사안내 리스트
    ArrayAdapter adapter;
    //스왚새로고침
    private SwipeRefreshLayout swipeRefreshLayout = null;
    //행사안내 리스트에 들어갈 내용.
    String [] events = {"경복궁 야간개장", "경회루 특별관람", "칠궁 특별관람",
                        "한복 무료관람", "다례체험", "수라간 시식공감",
                        "경복궁 음악회" , "수문장 교대 의식"};

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;

               case R.id.navigation_tmap:
                    Intent intent = new Intent(getApplicationContext(), TMapActivity.class);
                    startActivity(intent);
                    return true;

               case R.id.navigation_navi:
                    intent = new Intent(getApplicationContext(), NaviActivity.class);
                    startActivity(intent);

                    return true;

               case R.id.navigation_share:

                    return true;

               case R.id.navigation_mypage:

                   intent = new Intent(getApplicationContext(), LoginActivity.class);
                   startActivity(intent);
                     return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //행사안내 리스트---------------------------------------------------------------------------------------
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
        //스왚새로고침--
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Toast.makeText(getApplicationContext(), "해당 페이지로 이동합니다", Toast.LENGTH_SHORT).show();

                if (events[position].equals("경복궁 야간개장")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "http://www.royalpalace.go.kr");
                    startActivity(intent);

                } else if (events[position].equals("경회루 특별관람")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "http://www.royalpalace.go.kr/content/guide/guide26.asp");
                    startActivity(intent);

                } else if (events[position].equals("칠궁 특별관람")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "http://www.royalpalace.go.kr/content/guide/guide32.asp");
                    startActivity(intent);

                } else if (events[position].equals("한복 무료관람")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "http://www.royalpalace.go.kr/content/guide/guide01_tab07.asp");
                    startActivity(intent);

                } else if (events[position].equals("\"다례체험")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "http://www.royalpalace.go.kr/content/preview/preview04_2.asp");
                    startActivity(intent);

                } else if (events[position].equals("수라간 시식공감")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "http://www.royalpalace.go.kr/content/preview/preview04_10.asp");
                    startActivity(intent);

                } else if (events[position].equals("경복궁 음악회")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "http://www.royalpalace.go.kr/content/preview/preview04_11.asp");
                    startActivity(intent);

                } else if (events[position].equals("수문장 교대 의식")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "http://www.royalpalace.go.kr/content/preview/preview04_5a.asp");
                    startActivity(intent);
                }
            }
        });

        // Adding Listener
        //문제발생 --기존의 리스트가 안뜨다가 새로고침을 먼저해야지만 뜸.
        //-->해서 위에서 한번 뿌려놔서 기존 리스트를 띄우고
        //밑에서 새로고침을 함.
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ListView listView1 = findViewById(R.id.listView1);
                listView1.setAdapter(adapter); // listView1을 찾아와서 어댑터와 연결- 앱은 데이터를 주고 받을때 어댑터를 이용.

                // To keep animation for 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500); // Delay in millis
            }
        });
        // Scheme colors for animation
        swipeRefreshLayout.setColorSchemeColors
                (
                        getResources().getColor(android.R.color.holo_blue_bright),
                        getResources().getColor(android.R.color.holo_green_light),
                        getResources().getColor(android.R.color.holo_orange_light),
                        getResources().getColor(android.R.color.holo_red_light)
                );

    }

}
