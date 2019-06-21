package comstudy.android.ex30_webview;
//실행중입니다 -
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.TextView;

import static comstudy.android.ex30_webview.R.layout.custom_circle_progress;

public class CustomProgressDialog extends Dialog {
    private TextView progressCntTv;
    private TextView progressTextTv;

    public CustomProgressDialog(@NonNull Context context){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //노 타이틀!
        setContentView(custom_circle_progress);
    }
}
