package com.kenny.rewritefactorytest;

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
 * @date 2019/8/22
 */
public class ResultRecycleAdapter extends RecyclerView.Adapter<ResultRecycleAdapter.ViewHolder> {

    private List<Result> resultList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView result_name;
        TextView result;
        public ViewHolder(View view){
            super(view);
            result_name = view.findViewById(R.id.result_name);
            result = view.findViewById(R.id.result);
        }
    }

    public ResultRecycleAdapter(List<Result> resultList){
        resultList = resultList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list,null,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultRecycleAdapter.ViewHolder holder, int position) {
        Result adapter =
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
