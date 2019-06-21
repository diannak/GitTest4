package comstudy.android.ex01_hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import comstudy.android.ex01_hello.NewActivity;
import comstudy.android.ex01_hello.R;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "로그출력");
                Toast.makeText(getApplicationContext(), "다얀!", Toast.LENGTH_LONG).show();//다체인문법
            }
        });


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            //웹브라우저 띄우기//intent = ~할 의도 -암식적 의도 표명.
            //넣어놓은 주소를 보여줘. = 웹브라우저 띄워죠
            //이 의도(intent)의 행위(Activity)를 해줘(start)~
            //startActivity(intent);
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://54.180.95.221:8081/project/login.jsp"));
                startActivity(intent);
            }
        });
    }

    public void onButton2Clicked(View v)//웹브라우저 띄우기//intent = ~할 의도 -암식적 의도 표명.
    {//넣어놓은 주소를 보여줘. = 웹브라우저 띄워죠
        //이 의도(intent)의 행위(Activity)를 해줘(start)~
        //startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }

    public void onButton3Clicked(View v)//전화걸기
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01075601155"));
        startActivity(intent);
    }
/*
    public void onClickCall(View v){
        Uri n = Uri.parse("tel:01012345678");
        startActivity( new Intent(Intent.ACTION_CALL, n) );//전화창에 띄우고
    }
    public void onClickDial(View v){
        Uri n = Uri.parse("tel:01012345678");
        startActivity( new Intent(Intent.ACTION_DIAL, n) );//얜 아예 전화를 걸어버림
    }
*/

    public void onButton4Clicked(View v)//edittext에 입력한 값을 textView 에 보여주기
    {
        EditText editText = findViewById(R.id.editText);
        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(editText.getText());
    }

    public void onButton5Clicked(View v)//edittext에 입력한 값을 textView 에 보여주기
    {
        Intent intent = new Intent(getApplicationContext(), NewActivity.class);//명시적 의도
        intent.putExtra("CustomerName", "홍길동"); //데이터를 보내줌
        //startActivity(intent);
        startActivityForResult(intent, 1); //홍길동이 담긴 인텐트를 보내며 자기 번호를 같이 리절트에 담아 보냄

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)//매개변수 퀘스트 절트 데이타 받음.
    {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "콜백 함수 호출됨");

        if (requestCode == 1 && resultCode == 10) {
            String sData = "";
            String str = "OnActivityResult() called : " +
                    requestCode + " : " +
                    resultCode;

            sData = data.getStringExtra("BackData");
            str = str + " : " + sData;

            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }
}