package com.kenny.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 29003
 * @description
 * @date 2019/9/12
 */
public class CommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Fruit> mDataList;

    public CommonAdapter(List<Fruit> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
        recyclerViewHolder.fruitImage.setImageResource(mDataList.get(position).getImageId());
        recyclerViewHolder.fruitName.setText(mDataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView fruitImage;
        TextView fruitName;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }
}
