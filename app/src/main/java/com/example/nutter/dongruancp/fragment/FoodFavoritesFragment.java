package com.example.nutter.dongruancp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nutter.dongruancp.activity.MainActivity;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.adapter.CollectionAdapter;
import com.example.nutter.dongruancp.base.BaseFragment;
import com.example.nutter.dongruancp.bean.userCollection;
import com.example.nutter.dongruancp.interf.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFavoritesFragment extends BaseFragment {
    public RecyclerView recyclerView;
    public CollectionAdapter adapter;



    public FoodFavoritesFragment() {
        // Required empty public constructor
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_View_foodfavor);

        //请求数据->显示数据 retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();

        RetrofitService service=
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
        Call<List<userCollection>> call=service.getAllUserCollection(Integer.parseInt(getUserId()),1);

        call.enqueue(new Callback<List<userCollection>>() {

            @Override
            public void onResponse(Call<List<userCollection>> arg0, Response<List<userCollection>> arg1) {
                // TODO Auto-generated method stub
                if(getActivity()!=null) {
                    List<userCollection> lista = arg1.body();
                    MainActivity mainActivity = (MainActivity) getActivity();
                    adapter = new CollectionAdapter(mainActivity, lista);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(new GridLayoutManager(mainActivity,2));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                }
            }

            @Override
            public void onFailure(Call<List<userCollection>> arg0, Throwable arg1) {
                // TODO Auto-generated method stub

                //Toast

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_favorites, container, false);
    }

}
