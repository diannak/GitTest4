package com.dog.android.happy_dog;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

public class Dog3StartActivity extends Activity {
    Button btn_Dog1, btn_Dog2, btn_Dog3;


    private EditText et;
    AsyncTask<?, ?, ?> searchTask;
    ArrayList<Dog3SearchData> sdata = new ArrayList<Dog3SearchData>();

    final String serverKey="AIzaSyCVMIfJmEbl-QaALUIIM51jeuutv0FcTT0"; //콘솔에서 받아온 서버키를 넣어줍니다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog3_start );

        searchTask = new searchTask().execute();

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
    }

    private class searchTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                paringJsonData(getUtube());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            ListView searchlist = (ListView) findViewById(R.id.searchlist);

            StoreListAdapter mAdapter = new StoreListAdapter(
                    Dog3StartActivity.this, R.layout.activity_dog3_listview_start, sdata); //Json파싱해서 가져온 유튜브 데이터를 이용해서 리스트를 만들어줍니다.

            searchlist.setAdapter(mAdapter);

        }
    }

    public JSONObject getUtube() {

        HttpGet httpGet = new HttpGet(
                "https://www.googleapis.com/youtube/v3/search?"
                        + "part=snippet&maxResults=50&q=유기견"
                        + "&key="+ serverKey);  //EditText에 입력되 값으로 겁색을 합니다.
        // part(snippet),  q(검색값) , key(서버키)
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonObject;
    }

    //파싱을 하면 여러가지 값을 얻을 수 있는데 필요한 값들을 세팅하셔서 사용하시면 됩니다.
    private void paringJsonData(JSONObject jsonObject) throws JSONException {
        sdata.clear();

        JSONArray contacts = jsonObject.getJSONArray("items");

        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);
            String vodid = c.getJSONObject("id").getString("videoId");  //유튜브 동영상 아이디 값입니다. 재생시 필요합니다.

            String title = c.getJSONObject("snippet").getString("title"); //유튜브 제목을 받아옵니다
            String changString = "";
            try {
                changString = new String(title.getBytes("8859_1"), "utf-8"); //한글이 깨져서 인코딩 해주었습니다
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String date = c.getJSONObject("snippet").getString("publishedAt") //등록날짜
                    .substring(0, 10);
            String imgUrl = c.getJSONObject("snippet").getJSONObject("thumbnails")
                    .getJSONObject("default").getString("url");  //썸내일 이미지 URL값

            sdata.add(new Dog3SearchData(vodid, changString, imgUrl, date));
        }

    }

    String vodid = "";

    public class StoreListAdapter extends ArrayAdapter<Dog3SearchData> {
        private ArrayList<Dog3SearchData> items;
        Dog3SearchData fInfo;

        public StoreListAdapter(Context context, int textViewResourseId,
                                ArrayList<Dog3SearchData> items) {
            super(context, textViewResourseId, items);
            this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {// listview

            // 출력
            View v = convertView;
            fInfo = items.get(position);

            LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = vi.inflate(R.layout.activity_dog3_listview_start, null);
            ImageView img = (ImageView) v.findViewById(R.id.img);

            String url = fInfo.getUrl();

            String sUrl = "";
            String eUrl = "";
            sUrl = url.substring(0, url.lastIndexOf("/") + 1);
            eUrl = url.substring(url.lastIndexOf("/") + 1, url.length());
            try {
                eUrl = URLEncoder.encode(eUrl, "EUC-KR").replace("+", "%20");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String new_url = sUrl + eUrl;
            Picasso.with(getContext()).load(new_url).into(img);
//            DM.fetchDrawableOnThread(new_url, img);  //비동기 이미지 로더

            v.setTag(position);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (Integer) v.getTag();

                    Intent intent = new Intent( Dog3StartActivity.this,
                            Dog3Activity.class);
                    intent.putExtra("id", items.get(pos).getVideoId());
                    startActivity(intent); //리스트 터치시 재생하는 엑티비티로 이동합니다. 동영상 아이디를 넘겨줍니다..
                }
            });

            ((TextView) v.findViewById(R.id.title)).setText(fInfo.getTitle());
            ((TextView) v.findViewById(R.id.date)).setText(fInfo
                    .getPublishedAt());

            return v;
        }
    }
}