package com.example.nutter.dongruancp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutter.dongruancp.activity.FoodActivity;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.bean.food;
import com.example.nutter.dongruancp.bean.getshop;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.holder.CollectionitemViewHolder;
import com.example.nutter.dongruancp.interf.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nutter on 2017/5/3.
 */

public class SearchAdapter extends RecyclerView.Adapter {

    private List<food> collist = new ArrayList<>();
    private Context context;

    public SearchAdapter(Context context, List<food> food) {
        this.context = context;
        this.collist.addAll(food);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        CollectionitemViewHolder holder = new CollectionitemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CollectionitemViewHolder collectionitemViewHolder = (CollectionitemViewHolder) holder;
        final food food = collist.get(position);

        final String foodName = food.getFoodname();
        final String shopPrace = food.getIntro();

        Picasso.with(context).load(food.getPic()).into(((CollectionitemViewHolder) holder).imageView);
        collectionitemViewHolder.shopNameView.setText(foodName);
        collectionitemViewHolder.shopAddView.setText(shopPrace);
        if(food.getRecommand()==1){
            collectionitemViewHolder.imageView_recommand.setBackgroundResource(R.drawable.recommand);
        }
        collectionitemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodActivity.class);
                intent.putExtra("food", food);
                context.startActivity(intent);

            }

        });
    }

    @Override
    public int getItemCount() {
        return collist.size();
    }
}