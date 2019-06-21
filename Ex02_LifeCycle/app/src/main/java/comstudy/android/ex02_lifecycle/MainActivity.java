package comstudy.android.ex02_lifecycle;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "lecture";

    @Override //앱을 실행했을때
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //메인과 new를 자동으로 연결시켜놓음

        Toast.makeText(getApplicationContext(), "onCreate() 호출됨" , Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)//매개변수 퀘스트 절트 데이타 받음.
    {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onButton1Clicked(View v)//edittext에 입력한 값을 textView 에 보여주기
    {
        Intent intent = new Intent(getApplicationContext(), NewActivity.class);

        startActivityForResult(intent,1); //홍길동이 담긴 인텐트를 보내며 자기 번호를 같이 리절트에 담아 보냄

    }

    @Override // 앱 화면이 켜지고
    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart() 호출됨" , Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart");
    }

    @Override// 새 액티비티로 넘어가기 전 정보 저장 , 다시 돌아왔을때 저장된 정보 확인후 불러와 다시실행 --실행중
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(),"onResume() 호출됨" , Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume");
    }

    @Override//현재 앱의 무엇을 중단케 했을때 액티비티 멈추고 정보 저장
    protected void onPause(){
        super.onPause();
        Toast.makeText(getApplicationContext(),"onPause() 호출됨" , Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause");
    }
    //진행 상태에서의 일시정지및 재시작이 resume 과 pause에서 계속 왓다갓다하는거.

    @Override//액티비티 정지
    protected void onStop(){
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop() 호출됨" , Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop");
    }

    @Override//액티비티를 완전 종료했을때
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy() 호출됨" , Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy");
    }
}
