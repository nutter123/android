package com.example.nutter.dongruancp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.nutter.dongruancp.AccordionTransformer;
import com.example.nutter.dongruancp.GridViewForScrollView;
import com.example.nutter.dongruancp.NetworkImageHolderView;
import com.example.nutter.dongruancp.activity.MainActivity;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.adapter.ClassflyListAdapter;
import com.example.nutter.dongruancp.adapter.ShoplistAdapter;
import com.example.nutter.dongruancp.base.BaseFragment;
import com.example.nutter.dongruancp.bean.Classfly;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.interf.RetrofitService;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Shop_listFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    public LRecyclerView recyclerView;
    public ShoplistAdapter adapter;
    private GridViewForScrollView classflyGridview;
    private ConvenientBanner convenientBanner;
    private ClassflyListAdapter classflyListAdapter;
    private LinearLayout pointGroup;
    private List<String> networkImages = new ArrayList<String>();
    private TextView bannerTitleTv;
    private List<String> indexBannerList = new ArrayList<String>();
    private int lastPosition = 0;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private View mView;
    private View header;
    public int pageNum = 1;
    public int pageSize = 4;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_shop_list, container,
                false);

        initView();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (LRecyclerView) view.findViewById(R.id.recycler_View);



    }

    public void initView() {
        //请求数据->显示数据 retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();

        RetrofitService service =
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
        Call<List<shop>> call = service.getAllShops();

        call.enqueue(new Callback<List<shop>>() {

            @Override
            public void onResponse(Call<List<shop>> arg0, Response<List<shop>> arg1) {
                // TODO Auto-generated method stub
                if (getActivity() != null) {
                    List<shop> lista = arg1.body();
                    MainActivity mainActivity = (MainActivity) getActivity();
                    adapter = new ShoplistAdapter(mainActivity, lista);
                    mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setAdapter(mLRecyclerViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    recyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
                    recyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
                    recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

                    //add a HeaderView
                    final View header = LayoutInflater.from(getActivity()).inflate(R.layout.index_header, (ViewGroup) mView.findViewById(android.R.id.content), false);
                    classflyGridview = (GridViewForScrollView) header.findViewById(R.id.classfly_gridview);
                    classflyGridview.setFocusable(false);
                    pointGroup = (LinearLayout) header.findViewById(R.id.point_group);
                    convenientBanner = (ConvenientBanner) header.findViewById(R.id.convenientBanner);
                    bannerTitleTv = (TextView) header.findViewById(R.id.banner_title_tv);
                    mLRecyclerViewAdapter.addHeaderView(header);

                    recyclerView.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            recyclerView.refreshComplete(1);
                        }
                    });

/*                    recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void onLoadMore() {
                            pageNum++;
                            doRequest(false);

                        }
                    });*/
                    //设置头部加载颜色
                    recyclerView.setHeaderViewColor(R.color.colorAccent, R.color.dark, android.R.color.white);
                    //设置底部加载颜色
                    recyclerView.setFooterViewColor(R.color.colorAccent, R.color.dark, android.R.color.white);
                    //设置底部加载文字提示
                    recyclerView.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");
                    recyclerView.refresh();
                    indexBannerList.add("http://images.meishij.net/p/20100714/3ad48d2a0d31630f800c40c4e53266b7.jpg");
                    indexBannerList.add( "http://img2.imgtn.bdimg.com/it/u=1335413898,1555320128&fm=21&gp=0.jpg");
                    indexBannerList.add( "http://cpic2.edushi.com/cn/hz/zh-chs/LocalInfo/Companys/170265/Windows/Photo20081113162305.jpg");
                    indexBannerList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3742541408,409535472&fm=23&gp=0.jpg");
                    initClassfly();
                    setRecommendInfo();
                    initBanner();
                }
            }

            @Override
            public void onFailure(Call<List<shop>> arg0, Throwable arg1) {
                // TODO Auto-generated method stub

                //Toast

            }
        });


    }
    private void initClassfly() {
        List<Classfly> list = new ArrayList<Classfly>();
        Classfly c1 = new Classfly("3", "通知公告", R.mipmap.classfly_bg1);
        Classfly c2 = new Classfly("4", "菜谱学习", R.mipmap.classfly_bg2);
        Classfly c3 = new Classfly("5", "偶像推荐", R.mipmap.classfly_bg3);
        Classfly c4 = new Classfly("6", "新店优惠", R.mipmap.classfly_bg4);
        Classfly c5 = new Classfly("7", "线下活动", R.mipmap.classfly_bg5);
        Classfly c6 = new Classfly("8", "其它", R.mipmap.classfly_bg6);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        classflyListAdapter = new ClassflyListAdapter(getActivity());
        classflyGridview.setAdapter(classflyListAdapter);
        classflyListAdapter.setItems(list);
        classflyGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"功能正在完善~",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void initBanner() {

for (int n=0;n<indexBannerList.size();n++){
    networkImages.add(indexBannerList.get(n));
}
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                .setOnPageChangeListener((ViewPager.OnPageChangeListener) this);//监听翻页事件
        convenientBanner.setPageTransformer(new AccordionTransformer());
        convenientBanner.startTurning(3000);
        convenientBanner.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }
    private void setRecommendInfo() {


        pointGroup.removeAllViews();
        for (int i = 0; i < indexBannerList.size(); i++) {
            // 添加指示点
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i != indexBannerList.size() - 1) {
                params.rightMargin = 10;
            }
            point.setLayoutParams(params);

            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointGroup.addView(point);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 改变指示点的状态
        // 把当前点enbale 为true
        if (indexBannerList != null && indexBannerList.size() > 0) {
            bannerTitleTv.setText("坚果店铺");
        }
        pointGroup.getChildAt(position).setEnabled(true);
        // 把上一个点设为false
        pointGroup.getChildAt(lastPosition).setEnabled(false);
        lastPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}


