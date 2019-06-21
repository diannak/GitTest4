package com.dog.android.happy_dog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Dog1Activity extends AppCompatActivity {

    ListView listView;


    String data;
    private String kindCd;
    private String processState;
    private String image;
    private String happenDt;
    private String careTel;
    private String noticeNo;
    private String sexCd;
    private String happenPlace;
    private String specialMark;
    private String careNm;
    Button btn_Dog1, btn_Dog2, btn_Dog3, btn_Dog4   ;

    ArrayList<Dog1Item> items = new ArrayList<Dog1Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog1);

        btn_Dog1 = findViewById(R.id.btn_Dog1);
        btn_Dog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog1Activity.class);
                startActivity(mlntent);
            }
        });

        btn_Dog2 = findViewById(R.id.btn_Dog2);
        btn_Dog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog2Activity.class);
                startActivity(mlntent);
            }
        });
        // 영상 파싱
        btn_Dog3 = findViewById(R.id.btn_Dog3);
        btn_Dog3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mlntent = new Intent(getApplicationContext(), Dog3StartActivity.class);
                startActivity(mlntent);
            }
        });



        // edit= findViewById(R.id.edit);
        listView= findViewById(R.id.listView2);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                items = getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initAdapter(items);
                    }
                });
            }
        }).start();

    }
    public void initAdapter(final ArrayList<Dog1Item> items){
        Dog1Adapter adapter = new Dog1Adapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Dog1Item item = items.get(position);
                String temp = item.getImage();
                Intent intent = new Intent(getApplicationContext(), Dog1selectedItem.class);
                intent.putExtra("image", item.getImage());
                intent.putExtra("noticeNo",item.getNoticeNo());
                intent.putExtra("happenDt",item.getHappenDt());
                intent.putExtra("kindCd",item.getKindCd());
                intent.putExtra("happenPlace",item.getHappenPlace());
                intent.putExtra("processState",item.getProcessState());
                intent.putExtra("sexCd",item.getSexCd());
                intent.putExtra("specialMark",item.getSpecialMark());
                intent.putExtra("careNm",item.getCareNm());
                intent.putExtra("careTel",item.getCareTel());


                startActivity(intent);
            }
        });
    }



    public ArrayList<Dog1Item> getXmlData(){

        StringBuffer buffer=new StringBuffer();



        String queryUrl = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20180301&endde=20190430&pageNo=1&numOfRows=50&upkind=417000"
                + "&ServiceKey=jxdNXHd7MviV0OG96kcvMLDsmrrUH4VJcJ5gizCALgi1jMmyz5tA4sJ3PCByGqHd381IPz3UCtTCnGuX0dOZgQ%3D%3D";


        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기



            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기

                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("filename")){
                            xpp.next(); //사진
                            image = xpp.getText();
                        }
                        else if(tag.equals("noticeNo")) {
                            xpp.next(); //등록제목
                            noticeNo = xpp.getText();
                        }
                        else if(tag.equals("processState")){
                            xpp.next(); //상태
                            processState = xpp.getText();
                        }
                        else if(tag.equals("happenDt")){
                            xpp.next(); //접수일
                            happenDt = xpp.getText();
                        }
                        else if(tag.equals("kindCd")){
                            xpp.next();  //품종
                            String kindC = xpp.getText();
                            kindCd = kindC.replace("[개]","");

                        }
                        else if(tag.equals("sexCd")){
                            xpp.next(); //성별
                            sexCd = xpp.getText();
                        }
                        else if(tag.equals("happenPlace")){
                            xpp.next(); //발견
                            happenPlace = xpp.getText();
                        }
                        else if(tag.equals("specialMark")) {
                            xpp.next(); // 특징
                            specialMark = xpp.getText();
                        }
                        else if(tag.equals("careNm")) {
                            xpp.next(); //보호소 이름
                            careNm = xpp.getText();
                        }
                        else if(tag.equals("careTel")) {
                            xpp.next(); //보호소 전화번호
                            careTel = xpp.getText();
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName();
                        if(tag.equals("item")) {
                            Dog1Item dog1Item = new Dog1Item(kindCd, image, sexCd, careTel,processState,happenPlace
                                    , specialMark, noticeNo, happenDt, careNm);
                            items.add( dog1Item );
                        };


                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e) {

        }

        buffer.append("파싱 끝\n");

        return items;

    }

}