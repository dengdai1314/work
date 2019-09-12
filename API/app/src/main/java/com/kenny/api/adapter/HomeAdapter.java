package com.kenny.api.adapter;
/*
 *
 * File: HomeAdapter.java
 * Author: luohuojin
 * Create: 2019/9/12 10:37
 * Changes (from 2019/9/12)
 * -----------------------------------------------------------------
 * 2019/9/12 : Create HomeAdapter.java (29003);
 * -----------------------------------------------------------------
 * description:首页article列表adapter
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kenny.api.model.ArticleBean;
import com.kenny.api.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 29003
 * @description
 * @date 2019/9/11
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<ArticleBean> mArticleBeanList;

    public HomeAdapter (List<ArticleBean> articleBeanList){
        this.mArticleBeanList = articleBeanList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homes_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        ArticleBean articleBean = mArticleBeanList.get(position);
        holder.tv_author.setText(articleBean.getData().getDatas().get(position).getAuthor());
        holder.tv_title.setText(articleBean.getData().getDatas().get(position).getTitle());
        holder.tv_chapterName.setText(articleBean.getData().getDatas().get(position).getSuperChapterName()+"."+ articleBean.getData().getDatas().get(position).getChapterName());
    }

    @Override
    public int getItemCount() {
        return mArticleBeanList.size();
    }

    static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_author;
        TextView tv_title;
        TextView tv_chapterName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_chapterName = itemView.findViewById(R.id.tv_chapterName);
        }
    }

}
