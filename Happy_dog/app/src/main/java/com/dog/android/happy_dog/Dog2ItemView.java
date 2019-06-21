package com.dog.android.happy_dog;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Dog2ItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;

    Context context;


    public Dog2ItemView(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_dog2_view, this, true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);


    }

    public void setAnimalCnterNm(String animalCnterNm) {
        textView1.setText(animalCnterNm);  // 등록번호
    }

    public void setRdnmadr(String rdnmadr) { // 접수일
        textView2.setText(rdnmadr);
    } //접수일


    public void setPhoneNumber(String phoneNumber) {
        textView3.setText("tel) " +phoneNumber);
    } //품종

}
