package com.example.nutter.dongruancp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nutter.dongruancp.bean.food;

import java.util.List;

/**
 * Created by nutter on 2017/5/8.
 */

public abstract class BaseListAdapter extends RecyclerView.Adapter {
    protected List data;
    protected Context context;
    private LayoutInflater layoutInflater;

    public BaseListAdapter(Context context,List data){
        this.context  = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);

    }

    abstract protected RecyclerView.ViewHolder onCreateVH(ViewGroup parent,LayoutInflater layoutInflater,int viewType);
    abstract protected void onBindVH(RecyclerView.ViewHolder holder,List data,int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateVH(parent,layoutInflater,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindVH(holder,data,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
