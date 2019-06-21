package com.study.android.go_palace;
//실행중입니다 -
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.TextView;

public class CustomCircleProgressDialog extends Dialog {
    private TextView progressCntTv;
    private TextView progressTextTv;

    public CustomCircleProgressDialog(@NonNull Context context){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //노 타이틀!
        setContentView(R.layout.custom_circle_progress);
    }
}
