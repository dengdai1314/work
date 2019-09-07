package com.kenny.fragment_newdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author 29003
 * @description
 * @date 2019/9/7
 */
public class NewsContentFragment extends Fragment {
    private View view;

    //更新左侧内容项
    public void refresh(String newsTitle,String newsContent){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);                   //显示左侧内容项
        TextView newsTitleText = view.findViewById(R.id.news_title);
        TextView newsContentText = view.findViewById(R.id.news_content);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }

}
