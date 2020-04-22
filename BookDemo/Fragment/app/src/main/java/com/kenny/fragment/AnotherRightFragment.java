package com.kenny.fragment;
/*
 *
 * File: AnotherRightFragment.java
 * Author: luohuojin
 * Create: 2019/9/5 10:05
 * Changes (from 2019/9/5)
 * -----------------------------------------------------------------
 * 2019/9/5 : Create AnotherRightFragment.java (29003);
 * -----------------------------------------------------------------
 * description:动态替换的碎片
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
public class AnotherRightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.another_right_fragment,container,false);
        return view;
    }
}
