package com.example.nutter.dongruancp.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {
    private TextView titleView;
    private RadioGroup radioGroup;

    private Shop_listFragment shopListFragment;
    private Shop_colFragment shopColFragment;
    private Shop_searchFragment shopSearchFragment;
    private Shop_meFragment shopMeFragment;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

 //       titleView = (TextView) view.findViewById(R.id.textView_title);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        manager=getChildFragmentManager();
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
               switch(checkedId){
                   case R.id.radio_shop_list:
                       shopListFragment = new Shop_listFragment();
                       replace(shopListFragment);
                       break;
                   case R.id.radio_shop_collect:
                       shopColFragment = new Shop_colFragment();
                       replace(shopColFragment);
                       break;
                   case R.id.radio_shop_search:
                       shopSearchFragment = new Shop_searchFragment();
                       replace(shopSearchFragment);
                       break;
                   case R.id.radio_shop_me:
                       shopMeFragment = new Shop_meFragment();
                       replace(shopMeFragment);
                       break;
           }
           }
       });
        radioGroup.check(R.id.radio_shop_list);
    }

    private void replace(Fragment fragment){
        transaction = manager.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
