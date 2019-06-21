package comstudy.android.ex01_hello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {//예전버전까지 호환(Appcompat)된 Activity를 상속받음

     String sName; //지역변수로사용되고 끝나면 사라지니까 전역변수로 만들어주자

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent intent = getIntent();
        sName = intent.getStringExtra("CustomerName");
    }


    public void onbutton1Clicked(View v) {//명시적 의도
        //토스트로 전달된 데이터 보여주기
        Toast.makeText(getApplicationContext(), "CustomerName :" + sName, Toast.LENGTH_SHORT).show();
    }

     public void onbutton2Clicked(View v){
    //현재 인텐트 종료시 인텐트에 전달할 데이터 세팅
         Intent intent = new Intent();
         intent.putExtra("BackData", "강감찬");
         setResult(10, intent);
        finish();//창 종료 - 새로 뜬 창이 종료됨.

    }



}
