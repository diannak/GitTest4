package com.dog.android.happy_dog;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class AllSingerAdapter extends BaseAdapter {
    private static final String TAG = "happy";

    ImageButton button1;

    Context context;
    ArrayList<AllSingerItem> items = new ArrayList<>();

    public AllSingerAdapter(Context context) {
        this.context = context;
    }

    public void  addItem(AllSingerItem item){
            items.add(item);
        }

        @Override
        public int getCount() { return  items.size(); }
        @Override
        public Object getItem(int position) { return items.get(position); }
        @Override
        public long getItemId(int position) { return position; }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            AllSingerItemView view = null;
            if (convertView == null)
            {
                view = new AllSingerItemView(context);
            } else {
                view = (AllSingerItemView) convertView;
            }

            final AllSingerItem item = items.get(position);
            view.setImage(item.getResId());

            //**************************************************************
            //리스트 뷰안의 버튼 클릭 이벤트 처리
            button1 = view.findViewById(R.id.button1);
            button1.setFocusable(false);
            button1.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v)
                {
                    Intent lntent = new Intent(context.getApplicationContext(), Dog1Activity.class);
                    context.startActivity(lntent);
                    // Toast.makeText(context, "토스트", Toast.LENGTH_SHORT).show();
                }
            });
            //**************************************************************

            return view;
        }

}
