package com.kenny.home.adapter;
/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: HomeAdapter.java
 * Author: 29003
 * Version: V100R001C01
 * Create: 2019/11/27 10:29
 * Changes (from 2019/11/27 )
 * -----------------------------------------------------------------
 * 2019/11/27 : Create HomeAdapter.java (29003);
 * -----------------------------------------------------------------
 * description:
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kenny.api.R;
import com.kenny.home.model.ArticleBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @author 29003
 * @description
 * @date 2019/11/27
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<ArticleBean.DataBean.DatasBean> mArticleBeanList;

    public HomeAdapter(List<ArticleBean.DataBean.DatasBean> articleBeanList){
        this.mArticleBeanList = articleBeanList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_top;
        TextView tv_new;
        TextView tv_author;
        TextView tv_tag;
        TextView tv_time;
        ImageView iv_img;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_chapterName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_top = itemView.findViewById(R.id.tv_top);
            tv_new = itemView.findViewById(R.id.tv_new);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_tag = itemView.findViewById(R.id.tv_tag);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_chapterName = itemView.findViewById(R.id.tv_chapter_name);
        }
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homes_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //        holder.tv_author.setText(mArticleBeanList.get(position).getAuthor());
        //        holder.tv_title.setText(mArticleBeanList.get(position).getTitle());
        //        holder.tv_chapterName.setText(mArticleBeanList.get(position).getSuperChapterName()+"."+ mArticleBeanList.get(position).getChapterName());
        ArticleBean.DataBean.DatasBean item = new ArticleBean.DataBean.DatasBean();
        if (item.isTop()) {
            holder.tv_top.setVisibility(View.VISIBLE);
        } else {
            holder.tv_top.setVisibility(View.GONE);
        }
        if (item.isFresh()) {
            holder.tv_new.setVisibility(View.VISIBLE);
        } else {
            holder.tv_new.setVisibility(View.GONE);
        }
//        holder.tv_author.setText(item.getAuthor());
//        if (item.getTags() != null && item.getTags().size() > 0) {
//            holder.tv_tag.setVisibility(View.VISIBLE);
//        } else {
//            holder.tv_tag.setVisibility(View.GONE);
//        }
//        holder.tv_time.setText(item.getNiceDate());
//        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
//            holder.iv_img.setVisibility(View.VISIBLE);
//        } else {
//            holder.iv_img.setVisibility(View.GONE);
//        }
////        holder.tv_title.setText(Html.fromHtml(item.getTitle()));
//        if (TextUtils.isEmpty(item.getDesc())) {
//            holder.tv_desc.setVisibility(View.GONE);
//            holder.tv_title.setSingleLine(false);
//        } else {
//            holder.tv_desc.setVisibility(View.VISIBLE);
//            holder.tv_title.setSingleLine(true);
//            String desc = Html.fromHtml(item.getDesc()).toString();
//            desc = StringUtils.removeAllBank(desc, 2);
//            holder.tv_desc.setText(desc);
//        }
//        final int finalPosition = position;
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(onItemClickListener != null){
//                    onItemClickListener.onItemClick(finalPosition);
//                }
//            }
//        });
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    @Override
    public int getItemCount() {
        return mArticleBeanList.size();
    }

}
