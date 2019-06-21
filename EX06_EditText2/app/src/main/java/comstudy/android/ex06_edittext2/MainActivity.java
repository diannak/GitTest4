package comstudy.android.ex06_edittext2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "lecture";

    EditText inputMessage;
    String strAmount; //임시저장값 (콤마)

    @Override //눈에 숫자가 보이기 전
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMessage = findViewById(R.id.etMessage);

        //etMessage.setinputType(InputType.TYPE_CLASS_NUMBER); --숫자만 입력
        inputMessage.addTextChangedListener(watcher);
    }
    TextWatcher watcher = new TextWatcher() {

        public void onTextChanged(CharSequence str, int start, int before, int count) {
            Log.d(TAG, str.toString() + " : " + strAmount);

            if (!str.toString().equals(strAmount)) {//스택 오버플로우를 막기위해
                strAmount = makeStringComa(str.toString().replace(",", ""));
                inputMessage.setText(strAmount);
                inputMessage.setSelection(inputMessage.getText().length(),
                        inputMessage.getText().length());
            }
        }
        public void beforeTextChanged(CharSequence str, int start, int count, int after) {
            strAmount = str.toString();
        }

        public void afterTextChanged(Editable strEditable) {

        }
    };

     protected String makeStringComa(String str) { //콤마붙여주는 애
            if (str.length() == 0) //길이가 0이면 넣지 않고
                return "";
            long value = Long.parseLong(str); //길이가 있고
            DecimalFormat format = new DecimalFormat("###,##0");
            return format.format(value); //값을 포멧에 넣고 리턴하세요 - 숫자뒤에 콤마가 찍혀서 나옴.
        }
    }


