package com.example.nutter.dongruancp.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.activity.MainActivity;
import com.example.nutter.dongruancp.adapter.PagerAdapter;
import com.example.nutter.dongruancp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Shop_colFragment extends BaseFragment {


    private List<String> nameList;
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_col,container,false);
        bindView(view);
        return view;
    }
    private void bindView(View view) {
        ViewPager vp = (ViewPager) view.findViewById(R.id.ViewPager_col);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.Tablayout_col);

        nameList = new ArrayList<>();
        nameList.add("店铺收藏");
        nameList.add("菜品收藏");


        if (list == null){
            list = new ArrayList<>();
            list.add(new ShopFavoritesFragment());
            list.add(new FoodFavoritesFragment());
            vp.setAdapter(new PagerAdapter(getChildFragmentManager(),nameList,list));
            tabLayout.setupWithViewPager(vp);

        }



    }



}
