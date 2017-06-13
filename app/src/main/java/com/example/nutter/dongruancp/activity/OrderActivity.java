package com.example.nutter.dongruancp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.adapter.PagerAdapter;
import com.example.nutter.dongruancp.base.BaseActivity;
import com.example.nutter.dongruancp.fragment.Commentfragment;
import com.example.nutter.dongruancp.fragment.Orderfragment;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends BaseActivity {

    private List<String> nameList;
    private List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ViewPager vp = (ViewPager) findViewById(R.id.main_vp);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);

        nameList = new ArrayList<>();
        nameList.add("订单列表");
        nameList.add("评论列表");

        list = new ArrayList<>();
        list.add(new Orderfragment());
        list.add(new Commentfragment());


        vp.setAdapter(new PagerAdapter(getSupportFragmentManager(),nameList,list));
        tabLayout.setupWithViewPager(vp);

    }
    public void refresh() {
        onCreate(null);
    }

}
