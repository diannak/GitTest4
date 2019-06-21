package com.dog.android.happy_dog;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SupportItemView extends LinearLayout {
    TextView textView1;
    TextView textView2;

    public SupportItemView(Context context) {
        super(context);


        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_support1_view, this, true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

    }
        public void setName(String name) {
            textView1.setText(name);
        }

        public  void setAge(String age) {
            textView2.setText(age);
        }


    }


