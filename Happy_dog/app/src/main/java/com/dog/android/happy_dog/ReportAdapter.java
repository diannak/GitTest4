package com.dog.android.happy_dog;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {


    //데이터 배열 선언
    private ArrayList<ReportItem> mList;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView_img;
        private TextView txt_rNum,
                txt_rSort,
                txt_rKind,
                txt_rAll,
                txt_rDate,
                txt_rPlace;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView_img = itemView.findViewById(R.id.imageView_img);
            txt_rNum = itemView.findViewById(R.id.txt_rNum);
            txt_rSort = itemView.findViewById(R.id.txt_rSort);
            txt_rKind = itemView.findViewById(R.id.txt_rKind);
            txt_rAll = itemView.findViewById(R.id.txt_rAll);
            txt_rDate = itemView.findViewById(R.id.txt_rDate);
            txt_rPlace = itemView.findViewById(R.id.txt_rPlace);
        }
    }

    //생성자
    public ReportAdapter( ArrayList<ReportItem> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {


        String rSort = "0";
        //어떤방식으로 나눠야 할지 감이 오지 않음

        View view;

        if(rSort.equals("0")) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item0_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item1_layout, parent, false);
        }
        return new ViewHolder(view);



    }

    @Override
    public void onBindViewHolder( @NonNull ReportAdapter.ViewHolder holder, final int position) {

        holder.txt_rNum.setText(String.valueOf(mList.get(position).getrNum()));
        holder.txt_rSort.setText(String.valueOf(mList.get(position).getrSort()));
        holder.txt_rKind.setText(String.valueOf(mList.get(position).getrKind()));
        holder.txt_rAll.setText(String.valueOf(mList.get(position).getrAll()));
        holder.txt_rDate.setText(String.valueOf(mList.get(position).getrDate()));
        holder.txt_rPlace.setText(String.valueOf(mList.get(position).getrPlace()));

        Picasso.with(holder.itemView.getContext()).load(mList.get(position).getImg_url()).into(holder.imageView_img);

        holder.imageView_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Context context = v.getContext();
//                Toast.makeText(context, "클릭이벤트" + String.valueOf(mList.get(position).getrNum()), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context.getApplicationContext() , ReportSelectActivity.class);
                intent.putExtra("RNUM", String.valueOf(mList.get(position).getrNum()));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
