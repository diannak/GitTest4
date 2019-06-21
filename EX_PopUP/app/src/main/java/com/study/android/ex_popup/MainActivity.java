package com.study.android.ex_popup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    Button popbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            popbtn = findViewById(R.id.popbtn);
            popbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                    intent.putExtra("data", "Test Popup");
                    startActivityForResult(intent, 1);

                }
            });
        }
}


