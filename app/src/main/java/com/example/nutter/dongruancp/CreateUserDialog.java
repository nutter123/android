package com.example.nutter.dongruancp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutter.dongruancp.activity.OrderActivity;
import com.example.nutter.dongruancp.adapter.OrderAdapter;
import com.example.nutter.dongruancp.bean.order;
import com.example.nutter.dongruancp.bean.simpleResult;
import com.example.nutter.dongruancp.interf.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nutter on 2017/5/29.
 */
public class CreateUserDialog extends Dialog {

    /**
     * 上下文对象 *
     */
    Activity context;

    private Button btn_save;
    private int n;
    public EditText text;
    private int order;
    private String order_content;



    public CreateUserDialog(Activity context, int order,String order_content,int n) {
        super(context);
        this.context=context;
        this.order = order;
        this.n=n;
        this.order_content=order_content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.dialog_addcomment);

        text = (EditText) findViewById(R.id.editText_comment);
        if(n==1){
            text.setText(order_content);
        }

  /*
   * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
   * 对象,这样这可以以同样的方式改变这个Activity的属性.
   */
        final Window dialogWindow = this.getWindow();

        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);
        dialogWindow.setTitle("订单评论");
        dialogWindow.setBackgroundDrawableResource(R.color.colorChecked);
        // 根据id在布局中找到控件对象
        btn_save = (Button) findViewById(R.id.button_add);

        // 为按钮绑定点击事件监听器
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n==0) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("http://60.205.189.39/")
                            .build();

                    RetrofitService service =
                            ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
                    Call<simpleResult> call = service.insertComment(order, text.getText().toString());

                    call.enqueue(new Callback<simpleResult>() {

                        @Override
                        public void onResponse(Call<simpleResult> arg0, Response<simpleResult> arg1) {
                            // TODO Auto-generated method stub
                            if (arg1.body().getSuccess().equals("1")) {
                                Toast.makeText(context, "添加成功~", Toast.LENGTH_SHORT).show();
//                             context.finish();

                                dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<simpleResult> arg0, Throwable arg1) {
                            // TODO Auto-generated method stub

                        }
                        //访问服务器获取数据

                        //加载数据

                    });
                }else{
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("http://60.205.189.39/")
                            .build();

                    RetrofitService service =
                            ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
                    Call<simpleResult> call = service.updateComment(order, text.getText().toString());

                    call.enqueue(new Callback<simpleResult>() {

                        @Override
                        public void onResponse(Call<simpleResult> arg0, Response<simpleResult> arg1) {
                            // TODO Auto-generated method stub
                            if (arg1.body().getSuccess().equals("1")) {
                                Toast.makeText(context, "修改成功~", Toast.LENGTH_SHORT).show();
//                             context.finish();
                                dismiss();
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
        });

        this.setCancelable(true);
    }
}