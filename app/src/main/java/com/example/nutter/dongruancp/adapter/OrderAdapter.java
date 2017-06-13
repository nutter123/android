package com.example.nutter.dongruancp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nutter.dongruancp.CreateUserDialog;
import com.example.nutter.dongruancp.PopupList;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.activity.OrderActivity;
import com.example.nutter.dongruancp.bean.order;
import com.example.nutter.dongruancp.fragment.Orderfragment;
import com.example.nutter.dongruancp.holder.CommentitemViewHolder;
import com.example.nutter.dongruancp.holder.FooditemViewHolder;
import com.example.nutter.dongruancp.holder.OrderitemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nutter on 2017/5/15.
 */

public class OrderAdapter extends BaseListAdapter {
    private List<String> popupMenuItemList = new ArrayList<>();
    private float mRawX;
    private float mRawY;
    private CreateUserDialog createUserDialog;
    public OrderAdapter(Context context, List data) {
        super(context,data);
        popupMenuItemList.add("添加评论");
    }

    @Override
    protected RecyclerView.ViewHolder onCreateVH(ViewGroup parent, LayoutInflater layoutInflater, int viewType) {
        View itemView = layoutInflater.from(context).inflate(R.layout.item_order,parent,false);
        OrderitemViewHolder holder = new OrderitemViewHolder(itemView);
        return holder;
    }

    @Override
    protected void onBindVH(final RecyclerView.ViewHolder holder, List data, final int position) {


        final order order = (order) data.get(position);
        final OrderitemViewHolder viewHolder = (OrderitemViewHolder) holder;
        final String foodname=order.getFoodname();
        final int sum=order.getSum();
        int num=order.getNum();
        String date=order.getOrdertime();


        viewHolder.foodname.setText(foodname);
        viewHolder.price.setText("$"+sum);
        viewHolder.num.setText("*"+num);
        viewHolder.date.setText(date);
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mRawX = event.getRawX();
                mRawY = event.getRawY();
                return false;
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupList popupList = new PopupList(view.getContext());
                popupList.showPopupListWindow(view, position, mRawX, mRawY, popupMenuItemList, new PopupList.PopupListListener() {
                    @Override
                    public boolean showPopupList(View adapterView, View contextView, int contextPosition) {
                        return true;
                    }

                    @Override
                    public void onPopupListClick(View contextView, int contextPosition, int position) {
                        Toast.makeText(contextView.getContext(), contextPosition + "," + position, Toast.LENGTH_SHORT).show();
                        if(position==0){
                            OrderActivity orderActivity = (OrderActivity) context;
                            createUserDialog = new CreateUserDialog(orderActivity,order.getOrder_id(),order.getContent(),0);
                            createUserDialog.show();
                        }
                    }
                });
                return true;
            }
        });
        /*if (mItemTouchListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemTouchListener.onItemClick(foodname);
                }
            });

            if (viewHolder.mLeftMenu != null) {
                viewHolder.mLeftMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mItemTouchListener.onLeftMenuClick("left " + viewHolder.getAdapterPosition());
                        viewHolder.mSwipeItemLayout.close();
                    }
                    });
            }

            if (viewHolder.mRightMenu != null) {
                viewHolder.mRightMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mItemTouchListener.onRightMenuClick("right " + viewHolder.getAdapterPosition());
                        viewHolder.mSwipeItemLayout.close();
                    }
                });
            }
        }*/


    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}