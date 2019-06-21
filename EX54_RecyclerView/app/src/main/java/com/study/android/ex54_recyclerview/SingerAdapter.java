package com.study.android.ex54_recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.SingerItemViewHolder> {
    public interface ItemClick{
        public void onClick(View viw, int position);
    }
    private ItemClick itemClick;

    public void setItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    Context context;
    ArrayList<SingerItem> items = new ArrayList<>();

    public class SingerItemViewHolder extends RecyclerView.ViewHolder{
        protected TextView textView1;
        protected TextView textView2;
        protected ImageView imageView1;

        public SingerItemViewHolder(View view){
            super(view);
            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);
            imageView1 = view.findViewById(R.id.imageView1);
        }
    }
    public SingerAdapter(Context context){
        this.context = context;
    }
    public void addItem(SingerItem item){
        items.add(item);
    }

    @Override
    public SingerItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.singer_item_view, viewGroup,false);
        SingerItemViewHolder viewHolder = new SingerItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SingerItemViewHolder viewholder, int position){
        viewholder.textView1.setText(items.get(position).getName());
        viewholder.textView2.setText(items.get(position).getAge());
        viewholder.imageView1.setImageResource(items.get(position).getReId());

        final int num = position;

        viewholder.imageView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(itemClick != null){
                    itemClick.onClick(v, num);
                }
            }
        });
    }
    @Override
    public int getItemCount(){
        return (null != items ? items.size() : 0);
    }
    public Object getItem(int position){
        return items.get(position);
    }

}