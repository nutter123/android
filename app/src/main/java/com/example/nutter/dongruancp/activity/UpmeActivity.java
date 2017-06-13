package com.example.nutter.dongruancp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.base.BaseActivity;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.bean.simpleResult;
import com.example.nutter.dongruancp.bean.user;
import com.example.nutter.dongruancp.interf.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpmeActivity extends BaseActivity {
    private EditText etname, etpass,etphone,etadd;
    Button btnreg;
    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upme);
        final user user = getIntent().getParcelableExtra("me");

        etname = (EditText) findViewById(R.id.up_username);
        etpass = (EditText) findViewById(R.id.up_userpass);
        etphone = (EditText) findViewById(R.id.up_mobilenum);
        etadd = (EditText) findViewById(R.id.up_address);
        btnreg = (Button)findViewById(R.id.up_button);
        btnback= (ImageButton) findViewById(R.id.up_fanhui);

        insertData(user);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpmeActivity.this.finish();
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strname=etname.getText().toString();
                String strpassword=etpass.getText().toString();
                String strphone=etphone.getText().toString();
                String stradd=etadd.getText().toString();
                Retrofit retrofit=new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://60.205.189.39/")
                        .build();

                RetrofitService service=
                        ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
                Call<simpleResult> call=service.updateUserById(Integer.parseInt(getUserId()),strname,strpassword,strphone,stradd);

                call.enqueue(new Callback<simpleResult>() {

                    @Override
                    public void onResponse(Call<simpleResult> arg0, Response<simpleResult> arg1) {
                        // TODO Auto-generated method stub
                        simpleResult lista = arg1.body();
                        if (lista.getSuccess().toString().equals("0")) {
                            Toast.makeText(UpmeActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpmeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            if (UpmeActivity.this != null) {
                                UpmeActivity.this.finish();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<simpleResult> arg0, Throwable arg1) {
                        // TODO Auto-generated method stub

                        //Toast

                    }
                });

            }
        });

    }

    private void insertData(user user){
        etname.setText(user.getUsername());
        etphone.setText(user.getMobilenum());
        etadd.setText(user.getAddress());
        etpass.setText(user.getUserpass());
    }

}
