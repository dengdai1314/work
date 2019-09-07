package com.kenny.fragmentqualifier;
/*
 *
 * File: LeftFragment.java
 * Author: luohuojin
 * Create: 2019/9/5 10:04
 * Changes (from 2019/9/5)
 * -----------------------------------------------------------------
 * 2019/9/5 : Create LeftFragment.java (29003);
 * -----------------------------------------------------------------
 * description:左侧碎片
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author 29003
 * @description
 * @date 2019/9/4
 */
public class LeftFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment,container,false);
        return view;
    }
}
