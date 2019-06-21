package comstudy.android.ex07_imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "lecture";

    ScrollView scrollView01;

    ImageView imageView01;
    ImageView imageView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //이미지 뷰
        imageView01 = findViewById(R.id.imageView01);
        imageView02 = findViewById(R.id.imageView02);

        imageView01.setImageResource(R.drawable.background1);
        imageView02.setImageResource(0); // 이미지 뷰에서는 0 =null값

        //화면을 계속 그리고 있기때문에 한번씩 해줘야
        //게임같은 경우 초당 몇백번씩 그리고 있기 때문에 배터리 소모가 큼
        imageView01.invalidate();
        imageView02.invalidate();

        //스크롤 바의 유무만 다를 뿐 실제로 스크롤 함.
        scrollView01 = findViewById(R.id.scrollView01);
        scrollView01.setVerticalScrollBarEnabled(true);
        scrollView01.setHorizontalScrollBarEnabled(true);
    }

    public void onBtn1Clicked(View v) { imageDown(); }
    public void onBtn2Clicked(View v) { imageUp(); }

    private void imageDown(){
        imageView01.setImageResource(0);
        imageView02.setImageResource(R.drawable.background1);

        imageView01.invalidate();
        imageView02.invalidate();
    }

    private void imageUp(){
        imageView01.setImageResource(R.drawable.background1);
        imageView02.setImageResource(0);

        imageView01.invalidate();
        imageView02.invalidate();
    }
}
