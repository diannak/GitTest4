package com.dog.android.happy_dog;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Dog1ItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    ImageView imageView1;
    Context context;


    public Dog1ItemView(Context context){
        super(context);
        this.context = context;
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_dog1_view,this,true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        imageView1 = findViewById(R.id.imageView1);

    }
    public void setnoticeNo(String noticeNo) {
        textView1.setText(noticeNo);  // 등록번호
    }

    public  void sethappenDt(String happenDt) { // 접수일
        textView2.setText(happenDt);
    } //접수일

    public void setimage(String image) {
        Picasso.with(getContext()).load( image ).resize(350,350).into( imageView1); //이미지
    }

    public  void setkindCd(String kindCd) {textView3.setText(kindCd);
    } //품종

    public  void setsexCd(String sexCd) {textView4.setText(sexCd);
    } //성별

    public  void setprocessState(String processState) { //현재상태
        textView5.setText(processState);
    } //상태

}
