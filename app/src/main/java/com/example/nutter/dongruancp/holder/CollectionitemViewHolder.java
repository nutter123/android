package com.example.nutter.dongruancp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nutter.dongruancp.R;

/**
 * Created by nutter on 2017/5/3.
 */

public class CollectionitemViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView shopNameView;
    public TextView shopAddView;
    public ImageView imageView_recommand;

    public View itemView;

    public CollectionitemViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;

        imageView = (ImageView) itemView.findViewById(R.id.imageView_collection);
        shopNameView= (TextView) itemView.findViewById(R.id.textView_collection_name);
        shopAddView = (TextView) itemView.findViewById(R.id.textView_collection_address);
        imageView_recommand= (ImageView) itemView.findViewById(R.id.image_recommand);
    }
}
