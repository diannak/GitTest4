package comstudy.android.ex18_list4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "lecture";

    MyAdapter adapter;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyAdapter();

        SingerItem item1 = new SingerItem("권다얀", "27", R.drawable.face1);
        adapter.addItem(item1);

        SingerItem item2 = new SingerItem("윤혀네", "27", R.drawable.face2);
        adapter.addItem(item2);

        SingerItem item3 = new SingerItem("김소야", "28", R.drawable.face3);
        adapter.addItem(item3);

        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                                                 final SingerItem item = (SingerItem) adapter.getItem(position);
                                                 //Toast.makeText(getApplicationContext(), "selected :" + item.getName(), Toast.LENGTH_SHORT).show();
                                                 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                                                 builder.setMessage("삭제하시겠습니까?")
                                                         .setIcon(android.R.drawable.ic_dialog_alert)
                                                         .setTitle("알림")
                                                         .setCancelable(false)
                                                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                             @Override
                                                             public void onClick(DialogInterface dialog, int id) {

                                                                 adapter.removeItem(item);
                                                                 adapter.notifyDataSetChanged();
                                                                 dialog.cancel();

                                                             }
                                                         })
                                                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                             @Override
                                                             public void onClick(DialogInterface dialog, int id) {

                                                                 dialog.cancel();
                                                             }
                                                         });
                                                 AlertDialog alert = builder.create();
                                                 alert.show();
                                             }
                                         });

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
    }

    public void onBtn1Clicked(View v) {
        String inputName = editText1.getText().toString();
        String inputAge = editText2.getText().toString();

        SingerItem item = new SingerItem(inputName, inputAge, R.drawable.face1);
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }

    class MyAdapter extends BaseAdapter {

        ArrayList<SingerItem> items = new ArrayList<>();

        public void addItem(SingerItem item) {
            items.add(item);
        }

        public void removeItem(SingerItem item) {
            items.remove(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

    public View getView(int position, View convertView, ViewGroup parent) {

        SingerItemView view = new SingerItemView(getApplicationContext());

        SingerItem item = items.get(position);
        view.setName(item.getName());
        view.setAge(item.getAge());
        view.setImage(item.getResId());

        return view;

        }
    }
}
