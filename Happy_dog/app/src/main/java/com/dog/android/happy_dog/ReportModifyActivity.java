package com.dog.android.happy_dog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReportModifyActivity extends AppCompatActivity {

	Button btn_update;
	String RNUM;
	ImageView imageView1_img;
	Button but_rMdate;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_modify_layout);

		final RadioGroup radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
		final RadioGroup radioGroup_rGender = (RadioGroup)findViewById(R.id.radioGroup_rGender);
		final CheckBox checkBox_rGender1 = (CheckBox)findViewById(R.id.checkBox_rGender1);

		Intent intent = getIntent();
		RNUM = getIntent().getStringExtra("rNum");

		final EditText edit_rKind = (EditText)findViewById(R.id.edit_rKind);
		final EditText txt_rColor = (EditText)findViewById(R.id.txt_rColor);
		final EditText txt_rContent = (EditText)findViewById(R.id.txt_rContent);
		final EditText txt_rPlace = (EditText)findViewById(R.id.txt_rPlace);
		final TextView txt_rName = (TextView)findViewById(R.id.txt_rName);
		final TextView txt_rId = (TextView)findViewById(R.id.txt_rId);
		final EditText txt_rTel = (EditText)findViewById(R.id.txt_rTel);
		imageView1_img = (ImageView) findViewById(R.id.imageView1_img);
		but_rMdate = (Button) findViewById(R.id.but_rMdate);

		btn_update = (Button)findViewById(R.id.btn_update);
		btn_update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), RNUM + " 선택시 수정", Toast.LENGTH_SHORT).show();

				String rNum = RNUM;

				int id_Sort = radioGroup1.getCheckedRadioButtonId();
				//getCheckedRadioButtonId() 의 리턴값은 선택된 RadioButton 의 id 값.
				RadioButton RS = (RadioButton) findViewById(id_Sort);

				String rSort = RS.getText().toString();
				if(rSort.equals("제보")) {
					rSort = "1";
				} else if (rSort.equals("실종")) {
					rSort = "0";
				}

				String rTel = txt_rTel.getText().toString();
				String rPlace = txt_rPlace.getText().toString();
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

				String rColor = txt_rColor.getText().toString();
				String rContent = txt_rContent.getText().toString();
				String rMdate = but_rMdate.getText().toString(); // 날짜값변경(DatePicker활용)

				String requestURL = "http://192.168.219.107:8081/guest/update";
				InputStream up = update(requestURL, rNum, rSort, rTel, rPlace, rKind, rGender, rColor, rContent, rMdate);

				//인텐트 보내기
				Intent intent = new Intent(getApplicationContext(), ReportSelectActivity.class);
				intent.putExtra("RNUM", RNUM);
				startActivity(intent);
				finish();

			}
		});

		//AsyncTask 작동시킴(파싱)
		new Description1().execute();

    }//end onCreate

	public void onMdateClicked (View v) {
		android.support.v4.app.DialogFragment newFragment = new DatePickerFragment();   //DatePickerFragment 객체 생성
		newFragment.show(getSupportFragmentManager(), "datePicker");                //프래그먼트 매니저를 이용하여 프래그먼트 보여주기
	}

	public class Description1 extends AsyncTask<String, Void, ReportSelectItem> {

		//진행바표시
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			//진행다일로그 시작
			progressDialog = new ProgressDialog(ReportModifyActivity.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage("잠시 기다려 주세요.");
			progressDialog.show();

		}

		@Override
		protected ReportSelectItem doInBackground(String... params) {
			try {
				Document doc = Jsoup.connect("http://192.168.219.107:8081/guest/view_a?rNum=" + RNUM).get();

					String my_imgUrl = doc.select(".tdimg img").attr("src");
					String rSort1	= doc.select("td #RSort").text();
					String rSort = "";

						if(rSort1.equals("0")) {
							rSort = "실종";
						} else if(rSort1.equals("1")) {
							rSort = "제보";
						} else {
							rSort = "완료";
						}

					String rKind    = doc.select("td #RKind").text();
					String rGender  = doc.select("td #RGender").text();
					String rColor   = doc.select("td #RColor").text();
					String rContent = doc.select("td #RContent").text();
					String rMdate   = doc.select("td #RMdate").text();
					String rPlace   = doc.select("td #RPlace").text();
					String rName    = doc.select("td #RName").text();
					String rId      = doc.select("td #RId").text();
					String rTel     = doc.select("td #RTel").text();

					return new ReportSelectItem("http://192.168.219.107:8081" + my_imgUrl,
							rSort, rKind, rGender, rColor, rContent, rMdate, rPlace, rName, rId, rTel);
				//추출한 전체 <li> 출력해 보자.

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ReportSelectItem result) {
			super.onPostExecute(result);
			progressDialog.dismiss();

			Log.d("lecture", "rNum : " + RNUM);

			final EditText edit_rKind = (EditText)findViewById(R.id.edit_rKind);
			final EditText txt_rColor = (EditText)findViewById(R.id.txt_rColor);
			final EditText txt_rContent = (EditText)findViewById(R.id.txt_rContent);
			final EditText txt_rPlace = (EditText)findViewById(R.id.txt_rPlace);
			final TextView txt_rName = (TextView)findViewById(R.id.txt_rName);
			final TextView txt_rId = (TextView)findViewById(R.id.txt_rId);
			final EditText txt_rTel = (EditText)findViewById(R.id.txt_rTel);

			edit_rKind.setText(result.getrKind());
			txt_rColor.setText(result.getrColor());
			txt_rContent.setText(result.getrContent());
			txt_rPlace.setText(result.getrPlace());
			txt_rName.setText(result.getrName());
			txt_rId.setText(result.getrId());
			txt_rTel.setText(result.getrTel());
			but_rMdate.setText(result.getrMdate());

			Picasso.with(imageView1_img.getContext()).load(result.getImg_url()).into(imageView1_img);

		}

	}

	public InputStream update(String requestURL, String rNum, String rSort, String rTel, String rPlace,
							  String rKind, String rGender, String rColor, String rContent, String rMdate) {
		Log.i("xxx", "requestGet start");
		if(android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		} try {
			//1.
			HttpClient client = new DefaultHttpClient();

			//폼데이터 저장
			List<NameValuePair> dataList = new ArrayList<NameValuePair>();
			dataList.add(new BasicNameValuePair("rNum", rNum));
			dataList.add(new BasicNameValuePair("rSort", rSort));
			dataList.add(new BasicNameValuePair("rTel", rTel));
			dataList.add(new BasicNameValuePair("rPlace", rPlace));
			dataList.add(new BasicNameValuePair("rKind", rKind));
			dataList.add(new BasicNameValuePair("rGender", rGender));
			dataList.add(new BasicNameValuePair("rColor", rColor));
			dataList.add(new BasicNameValuePair("rContent", rContent));
			dataList.add(new BasicNameValuePair("rMdate", rMdate));
			requestURL = requestURL + "?" + URLEncodedUtils.format(dataList, "UTF-8");

			Log.d("lecture", "rMdate : " + rMdate);

			HttpGet get = new HttpGet(requestURL);

			//2. 요청
			HttpResponse response = client.execute(get);

			//3. 결과 받기
			HttpEntity entity = response.getEntity();
			InputStream up = entity.getContent();
			return up;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}//end requestGet()

	@Override
	public void onDestroy(){
		super.onDestroy();
	}
}