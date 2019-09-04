package com.kenny.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 29003
 * @description
 * @date 2019/7/12
 */
//新建FruitAdapter类，继承RecyclerView.Adapter，并将泛型指定为FruitAdapter.ViewHolder
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;//定义全局变量

    //内部类，继承自RecyclerView.ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        //传入View参数，作为RecyclerView子项最外层布局
        public ViewHolder(View view){
            //super()调整调用父类构造函数的顺序
            super(view);
            fruitView = view;
            //获取布局中View的实例
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    //方法主要用于把要展示的数据源传过来，并赋值给一个全局变量mFruitList
    public FruitAdapter(List<Fruit> fruitList){ mFruitList = fruitList; }

    @Override
    //创建ViewHolder实例，并把加载出来的布局传入到构造函数当中，最后将ViewHolder的实例返回
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(),"you clicked view"+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(),"you clicked image"+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
//        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    //用于对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候被执行。
    //通过position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的ImageView和TextView中；
    public  void  onBindViewHolder (ViewHolder holder,int position){
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.fruitImage.setImageResource(fruit.getImageId());
    }

    @Override
    //用于告诉RecyclerView一共有多少子项，直接返回数据源的长度
    public int getItemCount(){
        return mFruitList.size();
    }
}
