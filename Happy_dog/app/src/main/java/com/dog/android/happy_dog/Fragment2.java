package com.dog.android.happy_dog;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private static final String TAG = "happy";
    TextView userId, authId;
    private FirebaseAuth mAuth;

    private RecyclerView recyclerView0;
    GridLayoutManager layoutManager;
    private ArrayList<ReportItem> list = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        userId = rootView.findViewById( R.id.userId );
        authId = rootView.findViewById( R.id.authId );
        mAuth = FirebaseAuth.getInstance();
        userCheck();

        Button button = rootView.findViewById(R.id.button); //임시새로고침
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);
            }
        });

        recyclerView0 = rootView.findViewById(R.id.recyclerview_0);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                Intent intent = new Intent(getActivity(), ReportInsertActivity.class);
                startActivity(intent);
            }
        });

        //AsyncTask 작동시킴(파싱)
        new Description().execute();
        return rootView;
    }

    private void userCheck() {
        // 아이디값 저장 체크
        SharedPreferences pref = getContext().getSharedPreferences("login", Activity.MODE_PRIVATE);
        // 아이디 값 가져오기
        String sid = pref.getString("id", "");
        // 아이디 값 textView에 저장

        Log.d( "lecture","아이디 : " + sid );

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // 아이디 체크
            if(sid.equals( "" )) {
                // 로그인 상태 x
                userId.setText("test");
            } else {
                // 로그인 상태
                userId.setText(sid);
            }
        } else {
            authId.setText(user.getEmail());
        }
    }

    public class Description extends AsyncTask<String, Void, ReportItem[]> {

        //진행바표시
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //진행다일로그 시작
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        @Override
        protected ReportItem[] doInBackground(String... params) {
            try {
                Document doc = Jsoup.connect("http://192.168.219.106:8081/guest/report_sub2_a").get();
                Elements mElementDataSize = doc.select("div[id=pc] table").select("tr"); //필요한 녀석만 꼬집어서 지정

                int mElementSize = mElementDataSize.size(); //목록이 몇개인지 알아낸다. 그만큼 루프를 돌려야 하나깐.

                for(Element elem : mElementDataSize) { //<li> 에서 다시 원하는 데이터를 추출해 낸다.

                    String my_imgUrl = elem.select(".tdimg img").attr("src");
                    String txt_rNum = elem.select("td #RNum").text();
                    String txt_rSort = elem.select("td #RSort").text();

                    if(txt_rSort.equals("0")){
                        txt_rSort = "실종";
                    } else {
                        txt_rSort = "제보";
                    }
                    String txt_rKind = elem.select("td #RKind").text();
                    String txt_rAll = elem.select("td #RAll").text();
                    String txt_rDate = elem.select("td #RMdate").text();
                    String txt_rPlace = elem.select("td #RPlace").text();

                    list.add(new ReportItem("http://192.168.219.106:8081" + my_imgUrl, txt_rNum, txt_rSort, txt_rKind, txt_rAll, txt_rDate, txt_rPlace));
                    // list.add(new ReportItem(my_title));
                }
                //추출한 전체 <li> 출력해 보자.
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ReportItem[] result) {
//            ArraList를 인자로 해서 어답터와 연결한다.
            ReportAdapter ReportAdapter = new ReportAdapter(list);
            //           RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());


            layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int gridPosition = position % 2;
                    switch (gridPosition) {
                        case 0:
                        case 1:
                            return 1;
                    }
                    return 0;
                }
            });

            recyclerView0.setLayoutManager(layoutManager);
            recyclerView0.setAdapter(ReportAdapter);
            progressDialog.dismiss();
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        recyclerView0.notifyDataSetChanged();
//
//    }
}
