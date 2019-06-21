package comstudy.android.ex15_list1;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "lecture";
    private SwipeRefreshLayout swipeRefreshLayout = null;
    String[] names = {"권다얀", "윤혀네", "소", "고니", "시라소니", "권다얀", "윤혀네", "소", "고니", "시라소니", "권다얀", "윤혀네", "소", "고니", "시라소니"};
     ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        // Getting SwipeContainerLayout
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        // Adding Listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here

                ListView listView1 = findViewById(R.id.listView1);

                listView1.setAdapter(adapter); // listView1을 찾아와서 어댑터와 연결- 앱은 데이터를 주고 받을때 어댑터를 이용.

                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        Toast.makeText(getApplicationContext(), "selected : " + names[position], Toast.LENGTH_SHORT).show();
                    }
                });
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });

        // Scheme colors for animation
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );
    }
}

