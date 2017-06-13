package com.example.nutter.dongruancp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.adapter.FoodListAdapter;
import com.example.nutter.dongruancp.adapter.SelectAdapter;
import com.example.nutter.dongruancp.base.BaseActivity;
import com.example.nutter.dongruancp.bean.food;
import com.example.nutter.dongruancp.bean.isCollected;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.bean.simpleResult;
import com.example.nutter.dongruancp.holder.ShopitemViewHolder;
import com.example.nutter.dongruancp.interf.RetrofitService;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodListActivity extends BaseActivity implements View.OnClickListener{
    private TextView titleView;
    private TextView shop_name;
    private TextView shop_info;
    private ImageView shop_pic;
    private ImageButton shop_favor;
    private ImageButton shop_back;
//添加
    private ImageView imgCart;
    private ViewGroup anim_mask_layout;
    private RecyclerView rvSelected;
    private TextView tvCount,tvCost,tvSubmit,tvTips;
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private View bottom;


    private SparseArray<food> selectedList;
    private SparseIntArray groupSelect;

    private SelectAdapter selectAdapter;


    private NumberFormat nf;
    private Handler mHanlder;
//到这儿
    public String phonenum;
    public static int favor;
    private RecyclerView recyclerView;
    private FoodListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        final shop shop = getIntent().getParcelableExtra("shop");
        phonenum=shop.getPhonenum();
        Intent intent=new Intent(FoodListActivity.this,FoodActivity.class);
        intent.putExtra("phonennum",phonenum);


        shop_back= (ImageButton) findViewById(R.id.imageButton_back);
        titleView = (TextView) findViewById(R.id.textView_shop_address1);
        shop_name = (TextView) findViewById(R.id.textView_shop_name1);
        shop_info = (TextView) findViewById(R.id.textView_shop_info1);
        shop_pic = (ImageView) findViewById(R.id.imageView_shop_pic1);
        shop_favor= (ImageButton) findViewById(R.id.imageButton_favor);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);
       shop_favor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doFavor(Integer.parseInt(getUserId()),shop.getShop_id());
            }
        });


        shop_name.setText(shop.getShopname());
        shop_info.setText(shop.getIntro());
        if(shop.getPic().equals("")){
            Picasso.with(FoodListActivity.this).load("http://img3.redocn.com/tupian/20141126/xiangxiwaipocai_3613936.jpg").into(shop_pic);
        }else{
            Picasso.with(FoodListActivity.this).load(shop.getPic()).into(shop_pic);
        }
        titleView.setText(shop.getAddress());



        shop_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodListActivity.this.finish();
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recycler_View_food);
        //findviewbyId
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();

        RetrofitService service=
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
        Call<List<food>> call=service.getFoodByShop(shop.getShop_id());

        call.enqueue(new Callback<List<food>>() {

            @Override
            public void onResponse(Call<List<food>> arg0, Response<List<food>> arg1) {
                // TODO Auto-generated method stub
                    List<food> lista = arg1.body();
                    adapter = new FoodListAdapter(FoodListActivity.this,lista);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(FoodListActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    iscollected(Integer.parseInt(getUserId()),shop.getShop_id());
            }

            @Override
            public void onFailure(Call<List<food>> arg0, Throwable arg1) {
                // TODO Auto-generated method stub

            }

    });
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mHanlder = new Handler(getMainLooper());
        selectedList = new SparseArray<>();
        groupSelect = new SparseIntArray();
        initView();

}
    private void initView(){
        tvCount = (TextView) findViewById(R.id.tvCount);
        tvCost = (TextView) findViewById(R.id.tvCost);
        tvTips = (TextView) findViewById(R.id.tvTips);
        tvSubmit  = (TextView) findViewById(R.id.tvSubmit);

        imgCart = (ImageView) findViewById(R.id.imgCart);
        anim_mask_layout = (RelativeLayout) findViewById(R.id.activity_food_list);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);

    }

    public void playAnimation(int[] start_location){
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.animation_add);
        setAnim(img,start_location);
    }

    private Animation createAnim(int startX, int startY){
        int[] des = new int[2];
        imgCart.getLocationInWindow(des);

        AnimationSet set = new AnimationSet(false);

        Animation translationX = new TranslateAnimation(0, des[0]-startX, 0, 0);
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1]-startY);
        translationY.setInterpolator(new AccelerateInterpolator());
        Animation alpha = new AlphaAnimation(1,0.5f);
        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setDuration(500);

        return set;
    }

    private void addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {

        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y-loc[1]);
        vg.addView(view);
    }
    private void setAnim(final View v, int[] start_location) {

        addViewToAnimLayout(anim_mask_layout, v, start_location);
        Animation set = createAnim(start_location[0],start_location[1]);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_mask_layout.removeView(v);
                    }
                },100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(set);
    }

    private View createBottomSheetView(){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet,(ViewGroup) getWindow().getDecorView(),false);
        rvSelected = (RecyclerView) view.findViewById(R.id.selectRecyclerView);
        rvSelected.setLayoutManager(new LinearLayoutManager(this));
        selectAdapter = new SelectAdapter(this,selectedList);
        rvSelected.setAdapter(selectAdapter);
        return view;
    }
    private void showBottomSheet(){
        if(bottomSheet==null){
            bottomSheet = createBottomSheetView();
        }
        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if(selectedList.size()!=0){
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bottom:
                showBottomSheet();
                break;
            case R.id.clear:
                clearCart();
                break;
            case R.id.tvSubmit:
                try {
                    buy(Integer.parseInt(getUserId()),selectedList);
                    Toast.makeText(FoodListActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
                    FoodListActivity.this.finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    public void buy(int userid, final SparseArray<food> foodlist) throws IOException {
        final int num[] = {0};
        for(int i = 0, nsize = foodlist.size(); i < nsize; i++) {
            food food = foodlist.valueAt(i);
            Retrofit retrofit=new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://60.205.189.39/")
                    .build();
            RetrofitService service=
                    ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);

            Call<simpleResult> example = service.insertOrder(userid,food.getFood_id(),food.count,food.getPrice(),"2017-2018");
            example.enqueue(new Callback<simpleResult>() {

                @Override
                public void onResponse(Call<simpleResult> arg0, Response<simpleResult> arg1) {
                    // TODO Auto-generated method stub
                    simpleResult lista = arg1.body();

                    if(lista.getSuccess().equals("1")){
                        num[0]++;
                    }else{
                        num[1]++;
                    }
                    if(num[0]==foodlist.size()){
                        Toast.makeText(FoodListActivity.this,"送餐小哥正在接受订单~",Toast.LENGTH_SHORT).show();
                        FoodListActivity.this.finish();
                    }

                }

                @Override
                public void onFailure(Call<simpleResult> arg0, Throwable arg1) {
                    // TODO Auto-generated method stub

                }
            });
        }


    }

    public void onClick1(View v){
        switch (v.getId()){
            case R.id.clear:
                clearCart();
                break;
            default:
                break;
        }
    }
    //清空购物车
    public void clearCart(){
        selectedList.clear();
        groupSelect.clear();
        update(true);

    }
    //添加商品
    public void add(food item, boolean refreshGoodList){
        food temp = selectedList.get(item.getFood_id());
        if(temp==null){
            item.count=1;
            selectedList.append(item.getFood_id(),item);
        }else{
            temp.count++;
        }
        update(refreshGoodList);
    }
    //移除商品
    public void remove(food item, boolean refreshGoodList){


        food temp = selectedList.get(item.getFood_id());
        if(temp!=null){
            if(temp.count<2){
                selectedList.remove(item.getFood_id());
            }else{
                item.count--;
            }
        }
        update(refreshGoodList);
    }
    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList){
        int size = selectedList.size();
        int count =0;
        double cost = 0;
        for(int i=0;i<size;i++){
           food item = selectedList.valueAt(i);
            count += item.count;
            cost += item.count*item.getPrice();
        }

        if(count<1){
            tvCount.setVisibility(View.GONE);
        }else{
            tvCount.setVisibility(View.VISIBLE);
        }

        tvCount.setText(String.valueOf(count));

        if(cost > 99.99){
            tvTips.setVisibility(View.GONE);
            tvSubmit.setVisibility(View.VISIBLE);
        }else{
            tvSubmit.setVisibility(View.GONE);
            tvTips.setVisibility(View.VISIBLE);
        }

        tvCost.setText(nf.format(cost));

        if(adapter!=null && refreshGoodList){
            adapter.notifyDataSetChanged();
        }
        if(selectAdapter!=null){
            selectAdapter.notifyDataSetChanged();
        }
        if(bottomSheetLayout.isSheetShowing() && selectedList.size()<1){
            bottomSheetLayout.dismissSheet();
        }
    }
    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id){
        food temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.count;
    }


private void iscollected(int userid,int shopid){
    Retrofit retrofit=new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://60.205.189.39/")
            .build();
    RetrofitService service=
            ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);

    Call<isCollected> call=service.isCollected(userid,shopid,0);

    call.enqueue(new Callback<isCollected>() {

        @Override
        public void onResponse(Call<isCollected> arg0, Response<isCollected> arg1) {
            // TODO Auto-generated method stub
            isCollected lista = arg1.body();
            if(lista.getCollected().equals("1")){
                shop_favor.setBackgroundResource(R.drawable.shoucang);
                favor=1;
            }else{
                shop_favor.setBackgroundResource(R.drawable.unshoucang);
                favor=0;
            }
        }

        @Override
        public void onFailure(Call<isCollected> arg0, Throwable arg1) {
            // TODO Auto-generated method stub

        }
        //访问服务器获取数据

        //加载数据

    });
}
 private void doFavor(int userid,int shopid){

     Retrofit retrofit=new Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl("http://60.205.189.39/")
             .build();
     RetrofitService service=
             ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);

     Call<simpleResult> call=service.userCollectShop(userid,shopid);

     call.enqueue(new Callback<simpleResult>() {

         @Override
         public void onResponse(Call<simpleResult> arg0, Response<simpleResult> arg1) {
             // TODO Auto-generated method stub
             simpleResult lista = arg1.body();
             if(lista.getSuccess().equals("1")){
                 Toast.makeText(FoodListActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
                 favor=(favor+1)%2;
                 if(favor==1){
                     shop_favor.setBackgroundResource(R.drawable.shoucang);
                 }else{
                     shop_favor.setBackgroundResource(R.drawable.unshoucang);
                 }

             }else{
                 Toast.makeText(FoodListActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
             }
         }

         @Override
         public void onFailure(Call<simpleResult> arg0, Throwable arg1) {
             // TODO Auto-generated method stub

         }
         //访问服务器获取数据

         //加载数据

     });
 }

}