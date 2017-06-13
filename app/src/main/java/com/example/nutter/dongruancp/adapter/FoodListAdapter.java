package com.example.nutter.dongruancp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.example.nutter.dongruancp.activity.FoodActivity;
import com.example.nutter.dongruancp.activity.FoodListActivity;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.holder.FooditemViewHolder;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.bean.food;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nutter on 2017/5/8.
 */

public class FoodListAdapter extends BaseListAdapter {
    private ArrayList<food> dataList;
    private FoodListActivity mContext;
    private NumberFormat nf;
    private LayoutInflater mInflater;


    
    public FoodListAdapter(Context context, List data) {
        super(context,data);
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateVH(ViewGroup parent, LayoutInflater layoutInflater, int viewType) {
        View itemView = layoutInflater.from(context).inflate(R.layout.item_food,parent,false);
        FooditemViewHolder holder = new FooditemViewHolder(itemView);

        return holder;
    }

    @Override
    protected void onBindVH(RecyclerView.ViewHolder holder, List data, int position) {

        final food food = (food) data.get(position);
        final FooditemViewHolder viewHolder = (FooditemViewHolder) holder;
        String foodname=food.getFoodname();
        String foodinfo=food.getIntro();
        String foodprace= Integer.toString(food.getPrice());


        String pic = food.getPic();


        viewHolder.foodName.setText(foodname);
        viewHolder.foodInfo.setText(foodinfo);
        viewHolder.foodPrace.setText("$"+foodprace);
        if (pic==""){
            Picasso.with(context).load("http://i3.meishichina.com/attachment/recipe/201203/p320_201203302229311333492101.JPG").into(((FooditemViewHolder) holder).imageView);
        }else{
            Picasso.with(context).load(pic).into(((FooditemViewHolder) holder).imageView);
        }
        String contextString = context.toString();
        //if(contextString.toString().substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"))=="FoodListActivity")
            FoodListActivity activity = (FoodListActivity) context;
            food.count = activity.getSelectedItemCountById(food.getFood_id());
            viewHolder.tvCount.setText(String.valueOf(food.count));
            viewHolder.foodPrace.setText(nf.format(food.getPrice()));
            if (food.count < 1) {
                viewHolder.tvCount.setVisibility(View.GONE);
                viewHolder.tvMinus.setVisibility(View.GONE);
            } else {
                viewHolder.tvCount.setVisibility(View.VISIBLE);
                viewHolder.tvMinus.setVisibility(View.VISIBLE);
            }

            viewHolder.tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FoodListActivity activity = (FoodListActivity) context;
                    int count = activity.getSelectedItemCountById(food.getFood_id());
                    if (count < 1) {
                        viewHolder.tvMinus.setAnimation(getShowAnimation());
                        viewHolder.tvMinus.setVisibility(View.VISIBLE);
                        viewHolder.tvCount.setVisibility(View.VISIBLE);
                    }
                    activity.add(food, false);
                    count++;
                    viewHolder.tvCount.setText(String.valueOf(count));
                    int[] loc = new int[2];
                    view.getLocationInWindow(loc);
                    activity.playAnimation(loc);
                }
            });
            viewHolder.tvMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FoodListActivity activity = (FoodListActivity) context;

                    int count = activity.getSelectedItemCountById(food.getFood_id());
                    if (count < 2) {
                        viewHolder.tvMinus.setAnimation(getHiddenAnimation());
                        viewHolder.tvMinus.setVisibility(View.GONE);
                        viewHolder.tvCount.setVisibility(View.GONE);
                    }
                    count--;
                    activity.remove(food, false);//activity.getSelectedItemCountById(item.id)
                    viewHolder.tvCount.setText(String.valueOf(count));
                }
            });

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, FoodActivity.class);
                    intent.putExtra("food", food);
                    Activity activity = (Activity) context;
                    shop shop = activity.getIntent().getParcelableExtra("shop");
                    intent.putExtra("phone", shop.getPhonenum());
                    context.startActivity(intent);
                    //启动菜品详情activity
                }
            });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
}
