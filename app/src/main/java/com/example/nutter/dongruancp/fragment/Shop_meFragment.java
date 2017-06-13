package com.example.nutter.dongruancp.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.activity.FoodActivity;
import com.example.nutter.dongruancp.activity.MainActivity;
import com.example.nutter.dongruancp.activity.OrderActivity;
import com.example.nutter.dongruancp.activity.UpmeActivity;
import com.example.nutter.dongruancp.base.BaseFragment;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.bean.user;
import com.example.nutter.dongruancp.interf.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Shop_meFragment extends BaseFragment {
    public TextView uname;
    public TextView comment;
    public View upme;
    public TextView pinglu;
    public  TextView dingdan;
    public user lista;
    public Shop_meFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uname= (TextView) view.findViewById(R.id.me_username);
        comment= (TextView) view.findViewById(R.id.me_address);
        upme=view.findViewById(R.id.me_upme);
        dingdan= (TextView) view.findViewById(R.id.me_dingdan);



        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();

        RetrofitService service=
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
        Call<user> call=service.getUserById(Integer.parseInt(getUserId()));

        call.enqueue(new Callback<user>() {

            @Override
            public void onResponse(Call<user> arg0, Response<user> arg1) {
                // TODO Auto-generated method stub
                if(getActivity()!=null) {
                    lista = arg1.body();
                    uname.setText(lista.getUsername());
                    comment.setText(lista.getAddress());
                }
            }

            @Override
            public void onFailure(Call<user> arg0, Throwable arg1) {
                // TODO Auto-generated method stub
            }
        });
        upme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                Intent intent = new Intent(mainActivity, UpmeActivity.class);
                intent.putExtra("me",lista);
                mainActivity.startActivity(intent);
            }
        });
        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                Intent intent = new Intent(mainActivity, OrderActivity.class);
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_me, container, false);
    }

}
