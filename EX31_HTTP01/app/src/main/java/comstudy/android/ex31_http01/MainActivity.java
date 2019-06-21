package comstudy.android.ex31_http01;
//백으로 데이터를 요청할때
//!--데이터를 받겠다.
//XML,json으로
//공공데이터 받아올때 이 예제를 사용

//와이파이르르 잡아놓고 내 컴터 주소 192.168.0.98
//LTE로 하려면 아마존 웹서버를 통해서 해야함.
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    Button btnGetAct;
    Button btnPostAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetAct = findViewById(R.id.btnGetAct);
        btnPostAct = findViewById(R.id.btnPostAct);
    }

    public void onBtnGetAct (View v){

        Intent intent = new Intent(MainActivity.this, HttpGetActivity.class);
        startActivity(intent);
    }

    public void onBtnPostAct (View v){

        Intent intent = new Intent(MainActivity.this, HttpPostActivity.class);
        startActivity(intent);
    }

}
