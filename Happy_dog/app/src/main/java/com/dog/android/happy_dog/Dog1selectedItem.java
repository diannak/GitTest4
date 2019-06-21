package com.dog.android.happy_dog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Dog1selectedItem extends AppCompatActivity {
    Intent intent;
    Button button1;
    Context context;
    StringBuffer buffer = new StringBuffer();
    String image;
    String kindCd;
    String noticeNo;
    String happenPlace;
    String specialMark;
    String careNm;
    String careTel;
    String processState;
    String sexCd;
    String happenDt;

    ImageView imageView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    Button btn_Dog1, btn_Dog2, btn_Dog3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog1_selected_view );

        Intent intent = getIntent();
        image = intent.getStringExtra("image");
        noticeNo = intent.getStringExtra("noticeNo");
        happenDt = intent.getStringExtra("happenDt");
        kindCd = intent.getStringExtra("kindCd");
        processState = intent.getStringExtra("processState");
        sexCd = intent.getStringExtra("sexCd");
        happenPlace = intent.getStringExtra("happenPlace");
        specialMark = intent.getStringExtra("specialMark");
        careNm = intent.getStringExtra("careNm");
        careTel = intent.getStringExtra("careTel");

        imageView = findViewById(R.id.imageView2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView10 = findViewById(R.id.textView10);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        button1 = findViewById(R.id.button1);

        textView1.setText("등록번호 : " + noticeNo);
        textView2.setText("접수일 : " + happenDt);
        textView3.setText("품종 : " + kindCd);
        textView4.setText("상태 : " + processState);
        textView10.setText("성별 : " + sexCd);
        textView6.setText("발견장소 : "+happenPlace);
        textView7.setText("특징 : " + specialMark);
        textView8.setText("보호소 이름: " + careNm);
        textView9.setText("보호소 번호 : " +careTel);

        button1.setText(careNm+ " 전화 연결" );


        Picasso.with(context).load(image).resize(700,450).into(imageView);

        btn_Dog1 = findViewById(R.id.btn_Dog1);
        btn_Dog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog1Activity.class);
                startActivity(mlntent);
            }
        });

        btn_Dog2 = findViewById(R.id.btn_Dog2);
        btn_Dog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog2Activity.class);
                startActivity(mlntent);
            }
        });
        // 영상 파싱
        btn_Dog3 = findViewById(R.id.btn_Dog3);
        btn_Dog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog3StartActivity.class);
                startActivity(mlntent);
            }
        });


    }
    public void onClick1(View v) {   // 메서드 명으로 불러서 실행, tel은 전화 , sns 는 문자 (ACTION_VIEW)
        String b= "tel:";


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:&"+careTel));
        Log.d("lecture", "번호"+b+careTel);
        startActivity(intent);
    }


}
