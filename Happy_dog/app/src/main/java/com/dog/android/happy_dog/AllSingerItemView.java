package com.dog.android.happy_dog;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AllSingerItemView extends LinearLayout {

    ImageView imageView1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AllSingerItemView(Context context){
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.all_singer_item_view, this, true);

        imageView1 = findViewById(R.id.imageView1);

        GradientDrawable drawable=
                (GradientDrawable) context.getDrawable(R.drawable.background_rounding);

        imageView1.setBackground(drawable);
        imageView1.setClipToOutline(true);


    }

    public void setImage(int imgNum) {
        imageView1.setImageResource(imgNum);
    }
}
