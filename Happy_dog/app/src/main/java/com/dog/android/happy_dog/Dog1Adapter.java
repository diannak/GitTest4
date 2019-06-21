package com.dog.android.happy_dog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Dog1Adapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<Dog1Item> items;

    public Dog1Adapter(Context context, ArrayList<Dog1Item> items){
        this.context = context;
        this.items = items;
    }

    public void addItem(Dog1Item item){
        items.add(item);
    }

    @Override
    public int getCount(){return items.size();}

    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Dog1ItemView view = null;
        if(convertView == null){
            view = new Dog1ItemView(context);
        }else{
            view = (Dog1ItemView)convertView;
        }
        final Dog1Item item = items.get(position);
        view.setnoticeNo(item.getNoticeNo());
        view.sethappenDt(item.getHappenDt());
        view.setimage(item.getImage());
        view.setkindCd(item.getKindCd());
        view.setsexCd(item.getSexCd());
        view.setprocessState(item.getProcessState());




        return view;
    }
}