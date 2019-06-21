package com.dog.android.happy_dog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

/* 기본 엑티비티 */

public class ReportSelectActivity extends AppCompatActivity {
	private static final String TAG = "happy";

	TextView txt_rName, txt_rId, txt_rSort, txt_rPlace, txt_rKind,
			txt_rGender, txt_rColor, txt_rContent, txt_rMdate;
	ImageView imageView1_img;
	Button btn_rTel;
	String rRNUM;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_select_layout);

		Intent intent = getIntent();
		rRNUM = getIntent().getStringExtra("RNUM");

		txt_rSort = (TextView)findViewById(R.id.txt_rSort);
		txt_rKind = (TextView)findViewById(R.id.txt_rKind);
		txt_rGender = (TextView)findViewById(R.id.txt_rGender);
		txt_rColor = (TextView)findViewById(R.id.txt_rColor);
		txt_rContent = (TextView)findViewById(R.id.txt_rContent);
		txt_rMdate = (TextView)findViewById(R.id.txt_rMdate);
		txt_rPlace = (TextView)findViewById(R.id.txt_rPlace);
		txt_rName = (TextView)findViewById(R.id.txt_rName);
		txt_rId = (TextView)findViewById(R.id.txt_rId);
		btn_rTel = (Button)findViewById(R.id.btn_rTel);
		imageView1_img = (ImageView) findViewById(R.id.imageView1_img);

		FloatingActionButton fab_modify = (FloatingActionButton) findViewById(R.id.fab_modify);
		fab_modify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick( View view ) {
				Intent intent = new Intent(getApplicationContext(), ReportModifyActivity.class);
				intent.putExtra("rNum", rRNUM);
				startActivity(intent);
			}
		});

		FloatingActionButton fab_delete = (FloatingActionButton) findViewById(R.id.fab_delete);
		fab_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick( View view ) {

				/* 파일 삭제 확인 */
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ReportSelectActivity.this);

				// AlertDialog 셋팅
				alertDialogBuilder
						.setMessage("현재 글을 삭제하시겠습니까?")
						.setCancelable(false)
						.setPositiveButton("예", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {

								String rNum = rRNUM;

								String requestURL = "http://192.168.219.107:8081/guest/report_delete";
								InputStream is = requestGet(requestURL, rNum);

								Toast.makeText(getApplicationContext(), rNum + " 를 삭제하셨습니다.", Toast.LENGTH_SHORT).show();

								/* 리프레쉬*/
								Intent mlntent = new Intent(getApplicationContext(), MainActivity.class);
								startActivity(mlntent);

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
		});
		//AsyncTask 작동시킴(파싱)
		new ReportSelect().execute();
	}//end onCreate

	public class ReportSelect extends AsyncTask<String, Void, ReportSelectItem> {

		//진행바표시
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			//진행다일로그 시작
			progressDialog = new ProgressDialog(ReportSelectActivity.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage("잠시 기다려 주세요.");
			progressDialog.show();

		}

		@Override
		protected ReportSelectItem doInBackground(String... params) {
			try {
				Document doc = Jsoup.connect("http://192.168.219.107:8081/guest/view_a?rNum=" + rRNUM).get();

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

				return new ReportSelectItem("http://192.168.219.107:8081" + my_imgUrl, rSort, rKind, rGender, rColor,
						rContent, rMdate, rPlace, rName, rId, rTel);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ReportSelectItem result) {
			super.onPostExecute(result);
			progressDialog.dismiss();

			txt_rSort.setText(result.getrSort());
			txt_rKind.setText(result.getrKind());
			txt_rGender.setText(result.getrGender());
			txt_rColor.setText(result.getrColor());
			txt_rContent.setText(result.getrContent());
			txt_rMdate.setText(result.getrMdate());
			txt_rPlace.setText(result.getrPlace());
			txt_rName.setText(result.getrName());
			txt_rId.setText(result.getrId());
			btn_rTel.setText(result.getrTel());
			Picasso.with(imageView1_img.getContext()).load(result.getImg_url()).into(imageView1_img);

			final String Tel = result.getrTel();
			btn_rTel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick( View v ) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:&"+Tel));
					startActivity(intent);
				}
			});
		}
	}


	public InputStream requestGet(String requestURL, String rNum) {

		if(android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		} try {

			HttpClient client = new DefaultHttpClient();

			List<NameValuePair> dataList = new ArrayList<NameValuePair>();
			dataList.add(new BasicNameValuePair("rNum", rNum));
			requestURL = requestURL + "?" + URLEncodedUtils.format(dataList, "UTF-8");

			HttpGet get = new HttpGet(requestURL);
			HttpResponse response = client.execute(get);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			return is;

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