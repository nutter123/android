package com.example.nutter.dongruancp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nutter.dongruancp.R;

/**
 * Created by nutter on 2017/5/8.
 */

public class CommentitemViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public TextView content;
    public TextView date;
    public View itemView;

    public CommentitemViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        userName= (TextView) itemView.findViewById(R.id.comment_username);
        content = (TextView) itemView.findViewById(R.id.comment_content);
        date = (TextView) itemView.findViewById(R.id.comment_date);

    }
}
