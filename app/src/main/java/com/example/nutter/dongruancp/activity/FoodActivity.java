package com.example.nutter.dongruancp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.adapter.FoodAdapter;
import com.example.nutter.dongruancp.base.BaseActivity;
import com.example.nutter.dongruancp.bean.food;
import com.example.nutter.dongruancp.bean.isCollected;
import com.example.nutter.dongruancp.bean.order;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.bean.simpleResult;
import com.example.nutter.dongruancp.interf.RetrofitService;
import com.squareup.picasso.Picasso;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodActivity extends BaseActivity {
    private TextView food_name;
    private ImageView food_pic;
    private TextView food_price;
    private TextView food_intro;
    private TextView food_phone;
    private ImageButton food_favor;
    private ImageButton food_back;
    int favor;

    private RecyclerView recyclerView;
    private FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        final food food = getIntent().getParcelableExtra("food");


        food_phone = (TextView) findViewById(R.id.textView3_food_phone);
        food_name = (TextView) findViewById(R.id.textView3_food_name);
        food_pic = (ImageView) findViewById(R.id.imageView3_food_pic);
        food_price = (TextView) findViewById(R.id.textView3_food_price);
        food_intro = (TextView) findViewById(R.id.textView3_food_intro);
        food_back= (ImageButton) findViewById(R.id.imageButton3_back);
        food_favor= (ImageButton) findViewById(R.id.imageButton_favor1);
        getphone(food,food_phone);
        food_favor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doFavor(Integer.parseInt(getUserId()),food.getFood_id());
            }
        });
        food_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodActivity.this.finish();
            }
        });
        food_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用intent启动拨打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + food_phone.getText()));
                if (ActivityCompat.checkSelfPermission(FoodActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
        food_name.setText(food.getFoodname());
        Picasso.with(FoodActivity.this).load(food.getPic()).into(food_pic);
        food_intro.setText(food.getIntro());
        food_price.setText(Integer.toString(food.getPrice()));

        recyclerView = (RecyclerView)findViewById(R.id.recycler_View_pinglun);

        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();

        RetrofitService service=
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
        Call<List<order>> call=service.getAllUserFoodOrder(food.getFood_id());

        call.enqueue(new Callback<List<order>>() {

            @Override
            public void onResponse(Call<List<order>> arg0, Response<List<order>> arg1) {
                // TODO Auto-generated method stub
                List<order> lista = arg1.body();
                adapter = new FoodAdapter(FoodActivity.this,lista);
                LinearLayoutManager layoutManager = new LinearLayoutManager(FoodActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                iscollected();
            }

            @Override
            public void onFailure(Call<List<order>> arg0, Throwable arg1) {
                // TODO Auto-generated method stub

            }
            //访问服务器获取数据

            //加载数据

        });
    }
    private void getphone(food food, final TextView food_phone){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();

        RetrofitService service =
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
        Call<shop> call = service.getShopById(food.getShop_id());

        call.enqueue(new Callback<shop>() {

            @Override
            public void onResponse(Call<shop> arg0, Response<shop> arg1) {
                // TODO Auto-generated method stub
                shop lista = arg1.body();
                food_phone.setText(lista.getPhonenum());
            }

            @Override
            public void onFailure(Call<shop> arg0, Throwable arg1) {
                // TODO Auto-generated method stub

            }

        });
    }

    private void iscollected(){
        food food = getIntent().getParcelableExtra("food");
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();
        RetrofitService service=
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);

        Call<isCollected> call=service.isCollected(Integer.parseInt(getUserId()),food.getFood_id(),0);

        call.enqueue(new Callback<isCollected>() {

            @Override
            public void onResponse(Call<isCollected> arg0, Response<isCollected> arg1) {
                // TODO Auto-generated method stub
                isCollected lista = arg1.body();
                System.out.println(lista.getCollected());
                if(lista.getCollected()=="1"){
                        food_favor.setBackgroundResource(R.drawable.shoucang);
                        favor=1;
                    }else{
                        favor=0;
                        food_favor.setBackgroundResource(R.drawable.unshoucang);
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
    private void doFavor(int userid,int foodid){
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://60.205.189.39/")
                .build();
        RetrofitService service=
                ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);

        Call<simpleResult> call=service.userCollectFood(userid,foodid);

        call.enqueue(new Callback<simpleResult>() {

            @Override
            public void onResponse(Call<simpleResult> arg0, Response<simpleResult> arg1) {
                // TODO Auto-generated method stub
                simpleResult lista = arg1.body();
                if(lista.getSuccess().equals("1")){
                    Toast.makeText(FoodActivity.this,"操作成功",Toast.LENGTH_SHORT).show();
                    favor=(favor+1)%2;
                    if(favor==1){
                        food_favor.setBackgroundResource(R.drawable.shoucang);
                    }else{
                        food_favor.setBackgroundResource(R.drawable.unshoucang);
                    }

                }else{
                    Toast.makeText(FoodActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
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
