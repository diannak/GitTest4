package com.study.android.ex44_sqlite1;
//미니데이터베이스
//앱보다 DB가 크자나 걔 대신해서
//중요한 개인정보는 담으면안된다.
//루팅된거라면 폴더복사해서 다볼수잇다.
//읽기전용은 미리넣어도되고
//
//apk에 create해서 써야
// 안그럼 메모리에 저장되서 앱종료되면 다 날라간다.
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG= "lecture";

    SQLiteDatabase database;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView2 =findViewById(R.id.textView2);

    }

    private void printInfo(String msg){textView2.append(msg + "\n");}

    public void onBtn1Clicked(View v){
        String dbName = "customer.sqlite";
        try {
            database = openOrCreateDatabase(dbName, Activity.MODE_PRIVATE, null);

            printInfo("데이타베이스 만듦: " + dbName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onBtn2Clicked(View v){
        String sql =
                "create table if not exists customer (name text, age integer, mobile text) ";
        try{
            database.execSQL(sql);
            printInfo("table 만듦 : customer");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void onBtn3Clicked(View v){
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
    public void onBtn4clicked(View v){
        String sql = "select name, age, mobile from customer ";

        try {
            Cursor cursor = database.rawQuery(sql, null);

            int count = ((Cursor) cursor).getCount();
            printInfo("데이터 개수 :" + count);

            int i = 0;
            while (i < count) {
                cursor.moveToNext();

                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                Log.d(TAG, "#" + name + ":" + age + ":" + mobile);
                printInfo("#" + name + ":" + age + ":" + mobile);
                i++;

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void onBtn5Clicked(View v){
        String sql =
                "drop table customer";
        try{
            database.execSQL(sql);
            printInfo("table 삭제 : customer");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void onBtn6Clicked(View v){
        String sql =
                " delete from customer ";
        try{
            database.execSQL(sql);
            printInfo("데이터 삭제 : customer");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
