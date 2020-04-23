package com.kenny.fragment_newdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 29003
 * @description
 * @date 2019/9/7
 */
public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        //RecycleView
        RecyclerView newsTileRecyclerView = view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTileRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(getNews());
        newsTileRecyclerView.setAdapter(newsAdapter);
        return view;
    }

    //RecycleView数据
    private List<News> getNews(){
        List<News> newsList = new ArrayList<>();
        for (int i=1; i<=50; i++){
            News news = new News();
            news.setTitle("This is news title" + i);
            news.setContent(getRandomLengthContent("This is news content"+i+"."));
            newsList.add(news);
        }
        return newsList;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取当前活动，便判断当前活动加载的布局是否存在news_content_layout
        if(getActivity().findViewById(R.id.news_content_layout) != null){
            isTwoPane = true;
        }else {
            isTwoPane = false;

        }
    }
    //随机生成新闻内容的长度
    private String getRandomLengthContent(String content){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<length;i++){
            builder.append(content);
        }
        return builder.toString();
    }

    //RecycleAdapter
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                newsTitleText = itemView.findViewById(R.id.news_title);
            }
        }

        public NewsAdapter (List<News> NewsList){
            this.mNewsList = NewsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    News news = mNewsList.get(position);
                    if(isTwoPane){
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }else {
                        //调用NewsContentActivity中的actionStart方法，并传输数据
                        //getActivity()：得到和当前碎片相关联的活动实例
                        //获取当前点击项的title及content
                        NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

}
