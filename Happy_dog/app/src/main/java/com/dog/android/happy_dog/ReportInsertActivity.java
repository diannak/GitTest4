package com.dog.android.happy_dog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReportInsertActivity extends Activity {

	Button btn_insert, but_rMdate;
	TextView txt_rName, txt_rId, txt_rSort;

	EditText edit_rKind, edit_rColor, edit_rContent, edit_rPlace, edit_rTel;
	RadioGroup radioGroup1, radioGroup_rGender;
	CheckBox checkBox_rGender1;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_insert_layout);

		txt_rName = (TextView)findViewById(R.id.txt_rName);
		txt_rId = (TextView)findViewById(R.id.txt_rId);
		edit_rTel = (EditText)findViewById(R.id.edit_rTel);
		edit_rPlace = (EditText)findViewById(R.id.edit_rPlace);
		edit_rKind = (EditText)findViewById(R.id.edit_rKind);
		radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
		radioGroup_rGender = (RadioGroup)findViewById(R.id.radioGroup_rGender);
		checkBox_rGender1 = (CheckBox)findViewById(R.id.checkBox_rGender1);
		edit_rColor = (EditText)findViewById(R.id.edit_rColor);
		//edit_rFile = (EditText)findViewById(R.id.edit_rFile);
		edit_rContent = (EditText)findViewById(R.id.edit_rContent);
		but_rMdate = (Button)findViewById(R.id.but_rMdate);
        
        btn_insert = (Button)findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {


				//String rName = txt_rName.getText().toString();
				String rName = "이름";
				String rId = txt_rId.getText().toString();

				int id_Sort = radioGroup1.getCheckedRadioButtonId();
				//getCheckedRadioButtonId() 의 리턴값은 선택된 RadioButton 의 id 값.
				RadioButton RS = (RadioButton) findViewById(id_Sort);
				String rSort = RS.getText().toString();
				if(rSort.equals("제보")) {
					rSort = "1";
				} else if (rSort.equals("실종")) {
					rSort = "0";
				}

				String rTel = edit_rTel.getText().toString();
				String rPlace = edit_rPlace.getText().toString();
				String rKind = edit_rKind.getText().toString();

				int id_Gender = radioGroup_rGender.getCheckedRadioButtonId();
				RadioButton RG = (RadioButton) findViewById(id_Gender);
				String Gender = RG.getText().toString();

				String neutered;
				if (checkBox_rGender1.isChecked()) {
					neutered = "(중성화O)";
				} else {
					neutered = "(중성화X)";
				}

				String rGender;
				if(Gender.equals("미확인")){
					rGender = Gender;
				} else {
					rGender = Gender + neutered;
				}

				String rColor = edit_rColor.getText().toString();
				String rFile = "happydog.png"; //기본이미지
				String rContent = edit_rContent.getText().toString();
				String rMdate = but_rMdate.getText().toString();


				String requestURL = "http://192.168.219.107:8081/guest/insertdo2";
				InputStream is= requestGet(requestURL, rName, rId, rSort, rTel, rPlace, rKind, rGender, rColor, rFile, rContent, rMdate);
						
				finish();
			}
		});//end onClick()

    }//end onCreate

	public InputStream requestGet(String requestURL, String rName, String rId, String rSort, String rTel, String rPlace,
								  String rKind, String rGender, String rColor, String rFile, String rContent, String rMdate) {
        
    	Log.i("xxx", "requestGet start");
		//--출처: https://androphil.tistory.com/13 [소림사의 홍반장!]--//
		if(Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		} try {
    		
    		//1.
        	HttpClient client = new DefaultHttpClient();
        	
        	//폼데이터 저장
        	List<NameValuePair> dataList = new ArrayList<NameValuePair>();
			dataList.add(new BasicNameValuePair("rName", rName));
			dataList.add(new BasicNameValuePair("rId", rId));
			dataList.add(new BasicNameValuePair("rSort", rSort));
			dataList.add(new BasicNameValuePair("rTel", rTel));
			dataList.add(new BasicNameValuePair("rPlace", rPlace));
			dataList.add(new BasicNameValuePair("rKind", rKind));
			dataList.add(new BasicNameValuePair("rGender", rGender));
			dataList.add(new BasicNameValuePair("rColor", rColor));
			dataList.add(new BasicNameValuePair("rFile", rFile));
			dataList.add(new BasicNameValuePair("rContent", rContent));
			dataList.add(new BasicNameValuePair("rMdate", rMdate));
        	requestURL = requestURL + "?" + URLEncodedUtils.format(dataList, "UTF-8");
        	
        	HttpGet get = new HttpGet(requestURL);
        	
        	//2. 요청
        	HttpResponse response = client.execute(get);
    		
        	//3. 결과 받기
        	HttpEntity entity = response.getEntity();
        	InputStream is = entity.getContent();

        	return is;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }//end requestGet()

	/* 작성 취소 */
	@Override
	public void onBackPressed() {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ReportInsertActivity.this);

		// AlertDialog 셋팅
		alertDialogBuilder
				.setMessage("글 작성을 취소하시겠습니까?")
				.setCancelable(false)
				.setPositiveButton("예", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				})
				.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		// 다이얼로그 생성
		AlertDialog alertDialog = alertDialogBuilder.create();
		// show
		alertDialog.show();
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
	}

}