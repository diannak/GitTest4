package comstudy.android.ex16_list2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "lecture";

    String [] names = {"권다얀", "윤혀네", "소야", "고니","권다얀", "윤혀네", "소야", "고니","권다얀", "윤혀네", "소야", "고니"};
    String [] ages = {"27", "27","28","26","27", "27","28","26","27", "27","28","26",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView1 = (ListView)findViewById(R.id.listView1);

//        //주인=나, 뿌려줄 곳 , 뿌릴 데이타
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
//        ListView listView1 = findViewById(R.id.listView1);
//
//        listView1.setAdapter(adapter); // listView1을 찾아와서 어댑터와 연결- 앱은 데이터를 주고 받을때 어댑터를 이용.

        //2단계 -- 내 어댑터를 만들고 어댑터와 리스트뷰1를 연결한다.
        final  MyAdapter adapter =new MyAdapter();
        listView1.setAdapter(adapter);

        //4단계
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "selected : " + names[position], Toast.LENGTH_SHORT).show();

            }
        });
    }

    //1단계 -- 있는 어댑터를 그냥 상속받아서 내전용 어댑터를 만들어 내맴대로 수정가능하게 한다.
    class MyAdapter extends BaseAdapter {

    @Override
    public int getCount() {return names.length;}

    @Override
    public Object getItem(int position){
        return names[position];
        }
    @Override
    public long getItemId (int position){
        return position;
        }
    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        //1-1단계 -- 이름 정보를 뿌린다.

            TextView view1 = new TextView( getApplicationContext() );
            view1.setText(names[position]);
            view1.setTextSize(40.0f);
            view1.setTextColor(Color.BLUE);

        //    return view1;
       //3단계 -- 나이 정보를 뿌린다.
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            layout.addView(view1);

            TextView view2 = new TextView( getApplicationContext());
            view2.setText(ages[position]);
            view2.setTextSize(40.0f);
            view2.setTextColor(Color.RED);

            layout.addView(view2);

            return layout;
        }

    }
}
