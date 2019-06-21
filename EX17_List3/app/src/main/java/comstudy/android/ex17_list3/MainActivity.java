package comstudy.android.ex17_list3;
//화면 그리는 클래스만 따로 빼놓기 때문에 전 예제보다 단순해짐.
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "lecture";

    String[] names = {"권다얀", "윤혀네", "소야", "고니"};
    String[] ages = {"27", "27", "28", "26"};
    int[] images = {R.drawable.face1, R.drawable.face2, R.drawable.face3, R.drawable.face2, R.drawable.face1,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3단계
        final MyAdapter adapter = new MyAdapter();
        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        //4단계 - 클릭되면 포지션에 맞는 정보를 꽂아 뿌린다.
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Toast.makeText(getApplicationContext(), "selected : " + names[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    //1단계 -- 있는 어댑터를 그냥 상속받아서 내전용 어댑터를 만들어 내맴대로 수정가능하게 한다.
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() { return names.length; }

        @Override
        public Object getItem(int position) { return names[position]; }

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //2단계
            SingerItemView view = new SingerItemView(getApplicationContext());
            view.setName(names[position]);
            view.setAge(ages[position]);
            view.setImage(images[position]);

            return view;
        }
    }
}
