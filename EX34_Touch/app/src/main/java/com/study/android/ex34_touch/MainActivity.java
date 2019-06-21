package com.study.android.ex34_touch;
//터치가 뭔지만 알면됨 = 이해용도
//화면에 관련된건 무조건 view를 상속받는다.
//그래픽에서 화면을 그래픽,그려진다를 드로우 랜더링
//인벨리데이트 어쩌고 = 화면에 그리는것.
//액션 다운 =손가락으로 터치다운 (닿는것)
//액션 무브- 손가락으로 이리저리
//액션업 -위로올리는거
//초창기는 다 코딩햇지만 지금은 웬만큼 정리되엇음
//위젯이나 프레임워크로 만들면됨.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private static final String TAG ="lecture";

    protected class MyView extends View {
        int x = 100, y = 100;
        String str;
        Paint paint;

        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.YELLOW);

            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setTextSize(50);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawCircle(x, y, 100, paint);
            canvas.drawText("액션의 종류 :" + str, 0, 100, paint);
        }

        public boolean onTouchEvent(MotionEvent event) {
            x = (int) event.getX();
            y = (int) event.getY();

            if (event.getAction() == MotionEvent.ACTION_DOWN)
                str = "ACTION_DOWN";
            if (event.getAction() == MotionEvent.ACTION_MOVE)
                str = "ACTION_MOVE";
            if (event.getAction() == MotionEvent.ACTION_UP)
                str = "ACTION_UP";

            invalidate();
            return true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyView w = new MyView(this);
        setContentView(w);
    }
}
