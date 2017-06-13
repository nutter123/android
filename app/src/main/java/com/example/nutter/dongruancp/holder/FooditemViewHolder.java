package com.example.nutter.dongruancp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.bean.food;

/**
 * Created by nutter on 2017/5/8.
 */

public class FooditemViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView foodName;
    public TextView foodInfo;
    public TextView foodPrace,tvAdd,tvMinus,tvCount;
    public View itemView;
    private food item;

    public FooditemViewHolder(View itemView) {
        super(itemView);


        this.itemView = itemView;
        tvMinus = (TextView) itemView.findViewById(R.id.tvMinus);
        tvAdd = (TextView) itemView.findViewById(R.id.tvAdd);
        tvCount = (TextView) itemView.findViewById(R.id.count);
        imageView= (ImageView) itemView.findViewById(R.id.imageView_food);
        foodName = (TextView) itemView.findViewById(R.id.textView_food);
        foodInfo = (TextView) itemView.findViewById(R.id.textView_food_info);
        foodPrace = (TextView) itemView.findViewById(R.id.textView_food_price);


    }
}
