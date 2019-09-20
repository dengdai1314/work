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

import com.kenny.api.R;
import com.kenny.api.model.ArticleBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @author 29003
 * @description
 * @date 2019/9/11
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final int type_header = 0;
    private final int type_body = 1;
    private List<ArticleBean.DataBean.DatasBean> mArticleBeanList;

    public HomeAdapter (List<ArticleBean.DataBean.DatasBean> articleBeanList){
        this.mArticleBeanList = articleBeanList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_banner,parent,false);
            return new ZeroViewHolder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homes_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case type_header:
                break;
            default:
                holder.tv_author.setText(mArticleBeanList.get(position).getAuthor());
                holder.tv_title.setText(mArticleBeanList.get(position).getTitle());
                holder.tv_chapterName.setText(mArticleBeanList.get(position).getSuperChapterName()+"."+ mArticleBeanList.get(position).getChapterName());
                final int finalPosition = position;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onItemClickListener != null){
                            onItemClickListener.onItemClick(finalPosition);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mArticleBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
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

    public interface OnItemClickListener{
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
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

    class ZeroViewHolder extends ViewHolder {
        public ZeroViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position ==0){
            return type_header;
        }
        else return type_body;
    }
}
