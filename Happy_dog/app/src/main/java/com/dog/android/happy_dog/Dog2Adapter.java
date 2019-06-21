package com.dog.android.happy_dog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Dog2Adapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<Dog2Item> items;

    public Dog2Adapter(Context context, ArrayList<Dog2Item> items){
        this.context = context;
        this.items = items;
    }

    public void addItem(Dog2Item item){
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

        Dog2ItemView view = null;
        if(convertView == null){
            view = new Dog2ItemView(context);
        }else{
            view = (Dog2ItemView)convertView;
        }
        final Dog2Item item = items.get(position);
        view.setAnimalCnterNm(item.getAnimalCnterNm());
        view.setRdnmadr(item.getRdnmadr());
        view.setPhoneNumber(item.getPhoneNumber());





        return view;
    }
}