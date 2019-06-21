package com.study.android.ex45_sqlite2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="lecture";

    SQLiteDatabase database;
    SingerAdapter adapter;
    TextView textView2;

    String dbName;
    String tname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tname = "customer";

        adapter = new SingerAdapter(this);

        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                SingerItem item =(SingerItem)adapter.getItem(position);
                Toast.makeText(getApplicationContext(),
                        "seleted :" +item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        textView2 = findViewById(R.id.textView2);

        createMyDatabase();
        createMyTable();
        selectAllData();

    }

    private void printInfo(String msg){textView2.append(msg + "\n");}

    public void onBtn1Clicked(View v){
        String sql1 = "insert into customer " +
                " (name, age, mobile) values ('권다얀', 27, '01067393977') ";

        String sql2 = "insert into customer " +
                " (name, age, mobile) values ('윤혀네', 27, '01075601155') ";
        try{
            database.execSQL(sql1);
            printInfo("데이터 추가 : 1");

            database.execSQL(sql2);
            printInfo("데이터 추가 : 2");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void onBtn2Clicked(View v){
        selectAllData();

        adapter.notifyDataSetChanged();
    }

    private void createMyDatabase(){
        String dbName = "customer.sqlite";
        try {
            database = openOrCreateDatabase(dbName, Activity.MODE_PRIVATE, null);

            printInfo("데이타베이스 만듦: " + dbName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void createMyTable(){
        String sql = "create table if not exists customer (name text, age integer, mobile text) ";

        try{
            database.execSQL(sql);
            printInfo("table 만듦 : customer");
        }catch(Exception e){
        e.printStackTrace();
             }
        }

    private void selectAllData(){

        String sql = "select name, age, mobile from customer ";

        try {
            Cursor cursor = database.rawQuery(sql, null);

            int count = cursor.getCount();
            printInfo("데이터 개수 :" + count);

            int i = 0;
            while (i < count) {
                cursor.moveToNext();

                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                Log.d(TAG, "#" + name + ":" + age + ":" + mobile);
                printInfo("#" + name + ":" + age + ":" + mobile);

                SingerItem item = new SingerItem(name, age, mobile);
                adapter.addItem(item);
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

