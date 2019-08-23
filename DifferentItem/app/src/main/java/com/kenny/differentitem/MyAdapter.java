package com.kenny.differentitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author 罗火金
 * @description
 * @date 2019/8/22
 */
public class MyAdapter  {
    private Context mContext = null;//上下文
    private LayoutInflater mInflater = null;

    private List<BaseItem> mData = null;//要显示的数据

    public MyAdapter(Context context, List<BaseItem> data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    //添加一个新的Item，并通知listview进行显示刷新
    public void addItem(BaseItem newItem){
        this.mData.add(newItem);
//        this.notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        return mData.get(position).getItem_type();
    }

    public int getViewTypeCount() {
        return ItemType.ITEM_TYPE_MAX_COUNT;
    }

    public int getCount() {
        if(mData == null){
            return 0;
        }
        return this.mData.size();
    }

    public Object getItem(int i) {

        return mData.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View viewItem1 = null;
        View viewItem2 = null;

        int itemType = this.getItemViewType(position);
        if(itemType == ViewHolder1.ITEM_VIEW_TYPE_1){
            //第一种item
            ViewHolder1 viewHolder1 = null;
            if(convertView == null){
                //没有缓存过
                viewHolder1 = new ViewHolder1();
                viewItem1 = this.mInflater.inflate(R.layout.list_view_item_1, null, false);
                viewHolder1.textView1 = (TextView)viewItem1.findViewById(R.id.textview1);
                viewHolder1.imageView1 = (ImageView)viewItem1.findViewById(R.id.imageview1);
                viewItem1.setTag(viewHolder1);
                convertView = viewItem1;
            }else{
                viewHolder1 = (ViewHolder1)convertView.getTag();
            }
            viewHolder1.textView1.setText(((ItemBean1) mData.get(position)).getName());
            viewHolder1.imageView1.setBackgroundResource(R.drawable.ic_launcher_background);
        }else if(itemType == ViewHolder2.ITEM_VIEW_TYPE_2){
            //第二种item
            ViewHolder2 viewHolder2 = null;
            if(convertView == null){
                //没有缓存过
                viewHolder2 = new ViewHolder2();
                viewItem2 = this.mInflater.inflate(R.layout.list_view_item_2, null, false);
                viewHolder2.textView2 = (TextView)viewItem2.findViewById(R.id.
                        textview2);
                viewHolder2.textView22 = (TextView)viewItem2.findViewById(R.id.
                        textview22);
                viewItem2.setTag(viewHolder2);
                convertView = viewItem2;
            }else{
                viewHolder2 = (ViewHolder2)convertView.getTag();
            }
            viewHolder2.textView2.setText(((ItemBean2)mData.get(position)).getName());
            viewHolder2.textView22.setText(((ItemBean2)mData.get(position)).getName2());
        }

        return convertView;
    }
    public final class ViewHolder1 {
        public static final int ITEM_VIEW_TYPE_1 = 0;
        public TextView textView1 = null;
        public ImageView imageView1 = null;
    }
    public final class ViewHolder2 {
        public static final int ITEM_VIEW_TYPE_2 = 1;
        public TextView textView2 = null;
        public TextView textView22 = null;
    }
    public class ItemType {
        public static final int ITEM_TYPE_MAX_COUNT = 2;

    }

}
