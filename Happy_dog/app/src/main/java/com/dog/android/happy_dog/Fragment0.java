package com.dog.android.happy_dog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class Fragment0 extends Fragment {
    private static final String TAG = "happy";

    Button button;
    GridView listView1;
    AllSingerAdapter adapter;
    ImageButton imageButton1;

    public static Fragment0 newInstance() {
        return new Fragment0();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment0, container, false);

        //adapter = new MyAdapter();
        adapter = new AllSingerAdapter(getActivity());
        // 어댑터 설정

        adapter.addItem(new AllSingerItem(R.drawable.dog_01));
        adapter.addItem(new AllSingerItem(R.drawable.dog_02));
        adapter.addItem(new AllSingerItem(R.drawable.dog_03));
        adapter.addItem(new AllSingerItem(R.drawable.dog_04));
        adapter.addItem(new AllSingerItem(R.drawable.dog_05));
        adapter.addItem(new AllSingerItem(R.drawable.dog_06));
        adapter.addItem(new AllSingerItem(R.drawable.dog_07));
        adapter.addItem(new AllSingerItem(R.drawable.dog_08));

        listView1 = rootView.findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                AllSingerItem item = (AllSingerItem) adapter.getItem(position);

                Toast.makeText(getActivity().getApplicationContext(),
                        "selected",
                        Toast.LENGTH_SHORT).show();

            }
        });


        imageButton1 = rootView.findViewById(R.id.imageButton1);

        // Dog1Activity -> 유기견 공고로 보내기
        button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Dog1Activity -> 유기견 공고로 보내기");

                Intent mlntent = new Intent(getActivity(), Dog1Activity.class);
                startActivity(mlntent);
            }
        });

        // 동물등록제 안내 URI
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.animal.go.kr/portal_rnl/vicarious/public_info.jsp"));
                startActivity(intent);

                Log.d(TAG, "동물등록제 안내 URI");
            }
        });


        return rootView;
    }
}

