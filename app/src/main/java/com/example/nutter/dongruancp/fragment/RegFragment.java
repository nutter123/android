package com.example.nutter.dongruancp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutter.dongruancp.activity.MainActivity;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.base.BaseFragment;
import com.example.nutter.dongruancp.bean.simpleResult;
import com.example.nutter.dongruancp.interf.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nutter on 2017/4/19.
 */

public class RegFragment extends BaseFragment {
    private EditText etname, etpass,etphone,etadd,etcom;
    Button btnreg,btnback;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etname = (EditText) view.findViewById(R.id.r_username);
        etpass = (EditText) view.findViewById(R.id.r_userpass);
        etphone = (EditText) view.findViewById(R.id.r_mobilenum);
        etadd = (EditText) view.findViewById(R.id.r_address);
        etcom = (EditText) view.findViewById(R.id.r_comment);
        btnreg = (Button)view.findViewById(R.id.r_reg);
        btnback=(Button)view.findViewById(R.id.r_fanhui);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.replaceLoginFragment();
                }
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strname=etname.getText().toString();
                String strpassword=etpass.getText().toString();
                String strphone=etphone.getText().toString();
                String stradd=etadd.getText().toString();
                String strcom=etcom.getText().toString();
                Retrofit retrofit=new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://60.205.189.39/")
                        .build();

                RetrofitService service=
                        ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
                Call<simpleResult> call=service.userRegister(strname,strpassword,strphone,stradd,strcom);

                call.enqueue(new Callback<simpleResult>() {

                    @Override
                    public void onResponse(Call<simpleResult> arg0, Response<simpleResult> arg1) {
                        // TODO Auto-generated method stub
                        simpleResult lista = arg1.body();
                        if (lista.getSuccess().toString().equals("0")) {
                            Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                            MainActivity mainActivity = (MainActivity) getActivity();
                            if (mainActivity != null) {
                                mainActivity.replaceLoginFragment();
                                mainActivity.setUsernum(strname);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}
