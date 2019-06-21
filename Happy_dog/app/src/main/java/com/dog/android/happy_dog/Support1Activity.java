package com.dog.android.happy_dog;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Support1Activity extends AppCompatActivity {
    Intent intent;

    String[] names = {"유기견 정보는 어디서 얻을 수 있나요?", "배송정보 확인 하는 곳 알고싶어요?", "봉사 신청은 어디에서 하나요?", "봉사 결과는 어디에서 찾을 수 있나요?",
            "메일 인증이 무엇인가요?", "배송비는 어떻게 책정 되나요?", "보호소 주소는 어디서 알 수 있나요?", "영상메뉴는 무엇인가요?"};
    String[] ages = {"입양메뉴에 유기견 클릭 하시면 됩니다.", "웹싸이트를 통해 쇼핑정보에서 확인 하시면 됩니다.", "봉사하기에 메뉴에서 신청 하시면 됩니다.",
            "봉사결과는 공지사항에 게시 및 메일로 알려드립니다.", "회원 가입시 메일 인증이 이루어져야 가입이 가능합니다.", "한가지 제품당 5만원 이상이면 배송비가 무료 입니다."
            ,"입양 메뉴의 보호소 안내에서 보호소 주소를 확인 하실 수 있습니다.", "유튜브 에서 유기견 관련 영상을 보여 주는 곳 입니다."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_support1 );



        final MyAdapter adapter = new MyAdapter();
        ListView listView2 = findViewById(R.id.listView2);
        listView2.setAdapter(adapter);


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                Toast.makeText(getApplicationContext(),
                        "selected  : " + names[position],
                        Toast.LENGTH_SHORT).show();


            }
        });

    }



    public void onClick1(View v) {   // 메서드 명으로 불러서 실행
        intent = new Intent(Support1Activity.this, Support1ActivityChat.class);
        startActivity(intent);
    }


    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int posotion, View convwetView, ViewGroup parent) {

            SupportItemView view = new SupportItemView(getApplicationContext());
            view.setName(names[posotion]);
            view.setAge(ages[posotion]);

            return view;
        }
    }
}

