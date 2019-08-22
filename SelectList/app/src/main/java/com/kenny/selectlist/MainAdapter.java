package com.kenny.selectlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import androidx.appcompat.widget.AppCompatCheckBox;

/**
 * @author 29003
 * @description
 * @date 2019/8/22
 */
public class MainAdapter extends BaseAdapter {
    private static final int type_singul = 1;
    private static final int type_even = 2;
    HashMap<Integer,View> lmap = new HashMap<Integer, View>();
    List<Main> data;
    ViewHolder1 holder1 ;
    ViewHolder2 holder2;
    Context mContext;
    LayoutInflater resultinflater;
    private boolean isShowCheckBox = false;//表示当前是否是多选状态。
    private SparseBooleanArray stateCheckedMap = new SparseBooleanArray();//用来存放CheckBox的选中状态，true为选中,false为没有选中

    public MainAdapter(List<Main> resultList, Context mContext, SparseBooleanArray stateCheckedMap){
        this.resultinflater = LayoutInflater.from(mContext);
        this.data = resultList;
        this.mContext = mContext;
        this.stateCheckedMap = stateCheckedMap;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0){
            return type_even;
        }else if(position%2 != 0){
            return type_singul;
        }else{
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewItem1 = null;
        View viewItem2 = null;
        int type = getItemViewType(position);
        if(lmap.get(position) == null){
            switch (type){
                case type_singul:
                    viewItem1 = resultinflater.inflate(R.layout.result_list,parent,false);
                    holder1 = new ViewHolder1();
                    holder1.mTvData = convertView.findViewById(R.id.result_name);
                    lmap.put(position,convertView);
                    convertView.setTag(holder1);
                    break;
                case  type_even:
                    viewItem2 = resultinflater.inflate(R.layout.image_list,parent,false);
                    holder2 = new ViewHolder2();
                    holder2.image = convertView.findViewById(R.id.image_2);
                    holder2.title = convertView.findViewById(R.id.title_2);
                    holder2.describe = convertView.findViewById(R.id.describe);
                    lmap.put(position,convertView);
                    convertView.setTag(holder1);
                    break;
            }
        }else {
            switch (type){
                case type_singul:
                    convertView = lmap.get(position);
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case type_even:
                    convertView = lmap.get(position);
                    holder2 = (ViewHolder2) convertView.getTag();
            }
        }
        Object obj = data.get(position);
        switch (type){
            case type_singul:
                Main main = (Main) obj;
                if (main != null){
                    holder1.checkBox.setChecked(stateCheckedMap.get(position));
                    holder1.mTvData.setText(main.getName());
                }
                break;
            case type_even:
                Even even = (Even) obj;
                if(even != null){
                    holder2.checkBox.setChecked(stateCheckedMap.get(position));
                    holder2.image.setImageResource(even.getImage());
                    holder2.title.setText(even.getImage());
                    holder2.describe.setText(even.getDescribe());
                }
                break;
        }
        showAndHideCheckBox();//控制CheckBox的那个的框显示与隐藏
        return convertView;
    }

    public class ViewHolder1{
        AppCompatCheckBox checkBox;
        TextView mTvData;
    }
    public class ViewHolder2{
        AppCompatCheckBox checkBox;
        ImageView image;
        TextView title;
        TextView describe;
    }

    private void showAndHideCheckBox() {
        if (isShowCheckBox) {
            holder1.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder1.checkBox.setVisibility(View.GONE);
        }
    }

    public boolean isShowCheckBox() {
        return isShowCheckBox;
    }

    public void setShowCheckBox(boolean showCheckBox) {
        isShowCheckBox = showCheckBox;
    }
}
