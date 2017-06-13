package com.example.nutter.dongruancp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nutter.dongruancp.R;

/**
 * Created by nutter on 2017/5/8.
 */

public class OrderitemViewHolder extends RecyclerView.ViewHolder {
    public TextView foodname;
    public TextView price;
    public TextView num;
    public TextView date;
    public ImageView imageView;
    public View itemView;

    public OrderitemViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        foodname= (TextView) itemView.findViewById(R.id.order_foodname);
        price = (TextView) itemView.findViewById(R.id.order_price);
        num= (TextView) itemView.findViewById(R.id.order_num);
        date = (TextView) itemView.findViewById(R.id.order_date);
        imageView= (ImageView) itemView.findViewById(R.id.imageView_order);
    }
}
