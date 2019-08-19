package com.kenny.factorytest;
/*
 *
 * File: resultRecyclerAdapter.java
 * Author: 29003
 * Create: 2019/8/19 11:02
 * Changes (from 2019/8/19)
 * -----------------------------------------------------------------
 * 2019/8/19 : Create resultRecyclerAdapter.java (29003);
 * -----------------------------------------------------------------
 * description:Recyclerview Adapter
 */
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 29003
 * @description
 * @date 2019/8/17
 */
public class resultRecyclerAdapter extends RecyclerView.Adapter<resultRecyclerAdapter.ViewHolder> {
    private List<ResultJson> mResultJson;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView result;
        public ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.result_name);
            result = view.findViewById(R.id.result);
        }
    }
    public resultRecyclerAdapter(List<ResultJson> resultJsonList){
        mResultJson = resultJsonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_list,null,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultJson adapter = mResultJson.get(position);
        holder.name.setText(adapter.getName()+":  ");
        if (mResultJson.get(position).getResult().contains("成功")){
            holder.name.setTextColor(Color.RED);
        } else{
            holder.name.setTextColor(Color.GREEN);
        }
        holder.result.setText(adapter.getResult());
    }

    @Override
    public int getItemCount() {
        return mResultJson.size();
    }
}
