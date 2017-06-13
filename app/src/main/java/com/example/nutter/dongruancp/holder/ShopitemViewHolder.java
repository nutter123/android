package com.example.nutter.dongruancp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nutter.dongruancp.R;

import org.w3c.dom.Text;

/**
 * Created by nutter on 2017/5/3.
 */

public class ShopitemViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView shopNameView;
    public TextView shopInfoView;
    public RatingBar shopBarView;
    public TextView shopAddView;

    public View itemView;

    public ShopitemViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;

        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        shopNameView= (TextView) itemView.findViewById(R.id.textView_shop_name);
        shopInfoView = (TextView) itemView.findViewById(R.id.textView_shop_info);
        shopBarView = (RatingBar) itemView.findViewById(R.id.textView_shop_Bar);
        shopAddView = (TextView) itemView.findViewById(R.id.textView_shop_address);
    }
}
