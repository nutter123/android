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
import com.example.nutter.dongruancp.activity.OrderActivity;
import com.example.nutter.dongruancp.adapter.OrderAdapter;
import com.example.nutter.dongruancp.base.BaseFragment;
import com.example.nutter.dongruancp.bean.order;
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

public class Orderfragment extends BaseFragment {

    public RecyclerView recyclerView;
    public OrderAdapter adapter;


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_View_order);


        //请求数据->显示数据 retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();

        RetrofitService service =
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
        Call<List<order>> call = service.getAllUserOrder(Integer.parseInt(getUserId()));

        call.enqueue(new Callback<List<order>>() {

            @Override
            public void onResponse(Call<List<order>> arg0, Response<List<order>> arg1) {
                // TODO Auto-generated method stub
                List<order> lista = arg1.body();
                OrderActivity orderActivity = (OrderActivity) getActivity();
                adapter = new OrderAdapter(orderActivity, lista);
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
            //访问服务器获取数据

            //加载数据

        });


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_order, container, false);
    }

}
