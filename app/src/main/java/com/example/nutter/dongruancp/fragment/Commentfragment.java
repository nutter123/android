package com.example.nutter.dongruancp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.activity.FoodActivity;
import com.example.nutter.dongruancp.activity.MainActivity;
import com.example.nutter.dongruancp.activity.OrderActivity;
import com.example.nutter.dongruancp.adapter.FoodAdapter;
import com.example.nutter.dongruancp.adapter.ShoplistAdapter;
import com.example.nutter.dongruancp.base.BaseFragment;
import com.example.nutter.dongruancp.bean.food;
import com.example.nutter.dongruancp.bean.order;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.interf.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nutter on 2017/5/27.
 */

public class Commentfragment extends BaseFragment {
    public RecyclerView recyclerView;
    public FoodAdapter adapter;


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_View_comment);

        //请求数据->显示数据 retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();

        RetrofitService service=
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
        Call<List<order>> call=service.getAllUserComment(Integer.parseInt(getUserId()));

        call.enqueue(new Callback<List<order>>() {

            @Override
            public void onResponse(Call<List<order>> arg0, Response<List<order>> arg1) {
                // TODO Auto-generated method stub
                List<order> lista = arg1.body();
                OrderActivity orderActivity = (OrderActivity) getActivity();
                adapter = new FoodAdapter(orderActivity,lista);
                LinearLayoutManager layoutManager = new LinearLayoutManager(orderActivity);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<List<order>> arg0, Throwable arg1) {
                // TODO Auto-generated method stub

            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_comment, container, false);
    }
}
