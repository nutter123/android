package com.example.nutter.dongruancp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nutter.dongruancp.CreateUserDialog;
import com.example.nutter.dongruancp.PopupList;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.activity.OrderActivity;
import com.example.nutter.dongruancp.bean.order;
import com.example.nutter.dongruancp.bean.simpleResult;
import com.example.nutter.dongruancp.holder.CommentitemViewHolder;
import com.example.nutter.dongruancp.interf.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nutter on 2017/5/15.
 */

public class FoodAdapter extends BaseListAdapter {
    private List<String> popupMenuItemList = new ArrayList<>();
    private float mRawX;
    private float mRawY;
    private CreateUserDialog createUserDialog;
    public FoodAdapter(Context context, List data) {
        super(context,data);
        popupMenuItemList.add("修改评论");
        popupMenuItemList.add("删除评论");
    }

    @Override
    protected RecyclerView.ViewHolder onCreateVH(ViewGroup parent, LayoutInflater layoutInflater, int viewType) {
        View itemView = layoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        CommentitemViewHolder holder = new CommentitemViewHolder(itemView);

        return holder;
    }

    @Override
    protected void onBindVH(RecyclerView.ViewHolder holder, List data, final int position) {

        final order order = (order) data.get(position);
        CommentitemViewHolder viewHolder = (CommentitemViewHolder) holder;
        String username=order.getUsername();
        String content=order.getContent();
        String date=order.getComment_time();



        viewHolder.userName.setText(username);
        viewHolder.content.setText(content);
        viewHolder.date.setText("$"+date);
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mRawX = event.getRawX();
                mRawY = event.getRawY();
                return false;
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupList popupList = new PopupList(view.getContext());
                popupList.showPopupListWindow(view, position, mRawX, mRawY, popupMenuItemList, new PopupList.PopupListListener() {
                    @Override
                    public boolean showPopupList(View adapterView, View contextView, int contextPosition) {
                        return true;
                    }

                    @Override
                    public void onPopupListClick(final View contextView, int contextPosition, int position) {
                        if(position==0){
                                    Toast.makeText(contextView.getContext(), contextPosition + "," + position, Toast.LENGTH_SHORT).show();
                                    if(position==0){
                                        OrderActivity orderActivity = (OrderActivity) context;
                                        createUserDialog = new CreateUserDialog(orderActivity,order.getOrder_id(),order.getContent(),1);
                                        createUserDialog.show();
                                    }
                        }else {
                            Retrofit retrofit = new Retrofit.Builder()
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .baseUrl("http://60.205.189.39/")
                                    .build();

                            RetrofitService service =
                                    ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
                            Call<simpleResult> call = service.deleteComment(order.getOrder_id());

                            call.enqueue(new Callback<simpleResult>() {

                                @Override
                                public void onResponse(Call<simpleResult> arg0, Response<simpleResult> arg1) {
                                    // TODO Auto-generated method stub
                                    if(arg1.body().getSuccess().equals("1")){
                                        OrderActivity orderActivity = (OrderActivity) context;
                                        Toast.makeText(orderActivity, "删除成功~", Toast.LENGTH_SHORT).show();
                                        orderActivity.refresh();

                                    }
                                }

                                @Override
                                public void onFailure(Call<simpleResult> arg0, Throwable arg1) {
                                    // TODO Auto-generated method stub

                                }
                                //访问服务器获取数据

                                //加载数据

                            });
                        };
                    }
                });
                return true;
            }
        });

    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}