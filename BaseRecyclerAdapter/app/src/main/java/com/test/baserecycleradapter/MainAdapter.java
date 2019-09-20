package com.test.baserecycleradapter;


import android.os.AsyncTask;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author 29003
 * @description
 * @date 2019/9/17
 */
public class MainAdapter extends BaseQuickAdapter<AsyncTask.Status> {
    public MainAdapter(){
        super(R.layout.activity_main,DataServer.getSampleData())
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.setText(R.id.tweetName, item.getUserName())
                .setText(R.id.tweetText, item.getText())
                .setText(R.id.tweetDate, item.getCreatedAt())
                .setVisible(R.id.tweetRT, item.isRetweet())
                .linkify(R.id.tweetText);
        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
