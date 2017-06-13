package com.example.nutter.dongruancp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.activity.FoodActivity;
import com.example.nutter.dongruancp.activity.FoodListActivity;
import com.example.nutter.dongruancp.activity.MainActivity;
import com.example.nutter.dongruancp.bean.food;
import com.example.nutter.dongruancp.bean.getshop;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.bean.userCollection;
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

public class CollectionAdapter extends RecyclerView.Adapter {

    private List<userCollection> collist = new ArrayList<>();
    private Context context;

    public CollectionAdapter(Context context, List<userCollection>collection){
        this.context = context;
        this.collist.addAll(collection);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView=LayoutInflater.from(context).inflate(R.layout.item_collection,parent,false);
        CollectionitemViewHolder holder=new CollectionitemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CollectionitemViewHolder collectionitemViewHolder = (CollectionitemViewHolder)holder;
        final userCollection collection = collist.get(position);

        final int flag = collection.getFlag();

        final String shopName = collection.getShopname();
        final String foodName = collection.getFoodname();
        final String shopPrace = Integer.toString(collection.getPrice());
        final String shopAdd = collection.getAddress();

        final String pic = collection.getPic();

        final int shopId=collection.getShop_id();

        collectionitemViewHolder.shopNameView.setText(shopName);
        if(flag==0){
            collectionitemViewHolder.shopNameView.setText(shopName);
            collectionitemViewHolder.shopAddView.setText(shopAdd);
        }else {
            collectionitemViewHolder.shopNameView.setText(foodName);
            collectionitemViewHolder.shopAddView.setText("$ "+shopPrace);
        }
        collectionitemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag==0){
                    //请求数据->显示数据 retrofit
                    Retrofit retrofit=new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("http://60.205.189.39/")
                            .build();

                    RetrofitService service=
                            ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
                    Call<shop> call=service.getShopById(collection.getShop_id());

                    call.enqueue(new Callback<shop>() {

                        @Override
                        public void onResponse(Call<shop> arg0, Response<shop> arg1) {
                            // TODO Auto-generated method stub
                            shop shop=arg1.body();
                                Intent intent=new Intent(context,FoodListActivity.class);
                                intent.putExtra("shop",shop);
                                context.startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<shop> arg0, Throwable arg1) {
                            // TODO Auto-generated method stub

                            //Toast

                        }
                    });

                }else{
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("http://60.205.189.39/")
                            .build();

                    RetrofitService service =
                            ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
                    Call<food> call = service.getFoodById(collection.getFood_id());

                    call.enqueue(new Callback<food>() {

                        @Override
                        public void onResponse(Call<food> arg0, Response<food> arg1) {
                            // TODO Auto-generated method stub
                            food lista = arg1.body();
                            Intent intent = new Intent(context, FoodActivity.class);
                            intent.putExtra("food", lista);
                            context.startActivity(intent);
                            //启动菜品详情activity
                        }

                        @Override
                        public void onFailure(Call<food> arg0, Throwable arg1) {
                            // TODO Auto-generated method stub

                        }

                    });

                }
            }
        });

        Picasso.with(context).load(collection.getPic()).into(((CollectionitemViewHolder) holder).imageView);
    }

    @Override
    public int getItemCount() {
        return collist.size();
    }
}
