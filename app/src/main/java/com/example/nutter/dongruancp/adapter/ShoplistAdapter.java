package com.example.nutter.dongruancp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutter.dongruancp.activity.FoodListActivity;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.holder.ShopitemViewHolder;
import com.example.nutter.dongruancp.bean.shop;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nutter on 2017/5/3.
 */

public class ShoplistAdapter extends RecyclerView.Adapter {

    private List<shop> shopList = new ArrayList<>();
    private Context context;

    public ShoplistAdapter(Context context,List<shop>shopList){
        this.context = context;
        this.shopList.addAll(shopList);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView=LayoutInflater.from(context).inflate(R.layout.item_shop,parent,false);
        ShopitemViewHolder holder=new ShopitemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShopitemViewHolder shopitemViewHolder = (ShopitemViewHolder)holder;

        final shop shop = shopList.get(position);

        final String shopName = shop.getShopname();
        final String shopInfo = shop.getIntro();
        final int shopBar = shop.getLevel();
        final String shopAdd = shop.getAddress();

        final String pic = shop.getPic();

        int level = shop.getLevel();
        final int shopId=shop.getShop_id();

        shopitemViewHolder.shopNameView.setText(shopName);
        shopitemViewHolder.shopInfoView.setText(shopInfo);
        shopitemViewHolder.shopBarView.setNumStars(shopBar);
        shopitemViewHolder.shopAddView.setText(shopAdd);

        if(shop.getPic().equals("")){
            Picasso.with(context).load("http://img3.redocn.com/tupian/20141126/xiangxiwaipocai_3613936.jpg").into(((ShopitemViewHolder) holder).imageView);
        }else{
            Picasso.with(context).load(shop.getPic()).into(((ShopitemViewHolder) holder).imageView);
        }

        shopitemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动新的Acrivity 店铺详情（菜品列表）
                Intent  intent=new Intent(context,FoodListActivity.class);
                intent.putExtra("shop",shop);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }
}
