package com.dog.android.happy_dog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Dog2Activity extends AppCompatActivity {
    private static final String TAG = "lecture";

    ListView listView;
    String data;
    private String animalCnterNm;
    private String rdnmadr;
    private String phoneNumber;
    private String weekdayOperOpenHhmm;
    private String weekdayOperColseHhmm;
    private String wkendOperOpenHhmm;
    private String wkendOperCloseHhmm;
    private String rstde;
    private String latitude;
    private String hardness;
    String str1 = "tel) ";
    Button btn_Dog1, btn_Dog2, btn_Dog3;

    ArrayList<Dog2Item> items = new ArrayList<Dog2Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog2);
        Log.d("lecture", "위도");

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
        listView = findViewById(R.id.listView2);

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

    public void initAdapter(final ArrayList<Dog2Item> items) {
        Dog2Adapter adapter = new Dog2Adapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Dog2Item item = items.get(position);
                Intent intent = new Intent(getApplicationContext(), Dog2selectedItem.class);
                intent.putExtra("animalCnterNm", item.getAnimalCnterNm());
                intent.putExtra("rdnmadr", item.getRdnmadr());
                intent.putExtra("phoneNumber", item.getPhoneNumber());
                intent.putExtra("weekdayOperOpenHhmm", item.getWeekdayOperOpenHhmm());
                intent.putExtra("weekdayOperColseHhmm", item.getWeekdayOperColseHhmm());
                intent.putExtra("wkendOperOpenHhmm", item.getWkendOperCloseHhmm());
                intent.putExtra("wkendOperCloseHhmm", item.getWkendOperCloseHhmm());
                intent.putExtra("rstde", item.getRstde());
                intent.putExtra("latitude", item.getLatitude());
                intent.putExtra("hardness", item.getHardness());


                startActivity(intent);
            }
        });
    }


    public ArrayList<Dog2Item> getXmlData() {

        StringBuffer buffer = new StringBuffer();


        String queryUrl = "http://api.data.go.kr/openapi/animalprtccnter-std?serviceKey=%2Bq%2FKqfvXauB%2FGL8kBoM4zrb6%2B2saO6s%2BjR31t8x%2BI%2B4Lbc8CYYazPoTEbHXuBjope2%2B%2BLtrf4kmAIpz48Ym3Ow%3D%3D";


        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기


            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//태그 이름 얻어오기

                        if (tag.equals("item")) ;// 첫번째 검색결과
                        else if (tag.equals("animalCnterNm")) {
                            xpp.next(); //동물보호소 명
                            animalCnterNm = xpp.getText();
                        }
                        else if (tag.equals("rdnmadr")) {
                            xpp.next(); //주소
                            rdnmadr = xpp.getText();
                        }
                        else if (tag.equals("phoneNumber")) {
                            xpp.next(); //전화번호
                            phoneNumber =  xpp.getText();
                        }
                        else if (tag.equals("weekdayOperOpenHhmm")) {
                            xpp.next(); //평일운영시작시각
                            weekdayOperOpenHhmm = xpp.getText();
                        }
                        else if (tag.equals("weekdayOperColseHhmm")) {
                            xpp.next();  //평일운영종료시각
                            weekdayOperColseHhmm = xpp.getText();
                        }
                        else if (tag.equals("wkendOperOpenHhmm")) {
                            xpp.next(); //주말운영시작시각
                            wkendOperOpenHhmm = xpp.getText();
                        }
                        else if (tag.equals("wkendOperCloseHhmm")) {
                            xpp.next(); //주말운영종료시각
                            wkendOperCloseHhmm = xpp.getText();
                        }
                        else if (tag.equals("rstde")) {
                            xpp.next(); // 휴무일
                            rstde = xpp.getText();
                        }
                        else if (tag.equals("latitude")) {
                            xpp.next(); //위도
                            latitude = xpp.getText();
                        }
                        else if (tag.equals("hardness")) {
                            xpp.next(); //경도
                            hardness = xpp.getText();
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if (tag.equals("item")) {
                            Dog2Item dog2Item = new Dog2Item(animalCnterNm , rdnmadr , phoneNumber , weekdayOperOpenHhmm , weekdayOperColseHhmm , wkendOperOpenHhmm
                                    , wkendOperCloseHhmm , rstde , latitude , hardness );
                            items.add( dog2Item );
                            Log.d("lecture", "위도"+ latitude);
                            Log.d("lecture", "경도"+ hardness);
                        }



                        break;
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {

        }

        buffer.append("파싱 끝\n");

        return items;

    }

}
