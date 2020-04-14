package com.demo.tablayout.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.tablayout.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:40
 * @description 表格布局
 */
public class TabFragment extends Fragment {
    private RecyclerView recyclerView;
    CommonAdapter<String> adapter;
    String[] imageDatas = new String[]{"智能","红润","日系","自然","艺术黑白","甜美","蜜粉","清新","夏日阳光","唯美","蜜粉",};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab,container,false);
        initView(view);
        return view;
    }

    public void initView(View view){
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        adapter = new CommonAdapter<String>(getActivity(),R.layout.tab_item, Arrays.asList(imageDatas)) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {}
        };
        recyclerView.setAdapter(adapter);
    }

}
