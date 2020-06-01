package com.practice.textaxle_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/2215:44
 * @description
 */
public class MyAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<HashMap<String,Object>> listItem;

    public MyAdapter(Context context,ArrayList<HashMap<String,Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView itemTitle,itemText;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.Itemtitle);
            itemText = itemView.findViewById(R.id.ItemText);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_cell,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 绑定数据到ViewHolder里面
        MyHolder myHolder = (MyHolder) holder;
        myHolder.itemTitle.setText((String) listItem.get(position).get("ItemTitle"));
        myHolder.itemText.setText((String) listItem.get(position).get("ItemText"));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
