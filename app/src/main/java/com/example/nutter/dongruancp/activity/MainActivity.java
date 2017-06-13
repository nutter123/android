package com.example.nutter.dongruancp.activity;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.base.BaseActivity;
import com.example.nutter.dongruancp.fragment.LoginFragment;
import com.example.nutter.dongruancp.fragment.MainFragment;
import com.example.nutter.dongruancp.fragment.RegFragment;

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private LoginFragment loginFragment;
    private MainFragment mainFragment;
    private RegFragment regFragment;

    private SharedPreferences preferences;
    private final String fileName="food";
    private final int mode=MODE_PRIVATE;

    private String usernum;
    public String getUsernum(){
        return usernum;
    };
    public void setUsernum(String a){
        this.usernum = a;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences=getSharedPreferences(fileName,mode);
        //获取存储对象

        fragmentManager = getSupportFragmentManager();
        //判断是否登录  如果登录进入主界面
        boolean loginstate = isLogined();
        if (loginstate)
            showMainfragment();
        else
            showLoginfragment();
        setUserId(getSavedUserId());
    }

    private void showLoginfragment(){
        transaction = fragmentManager.beginTransaction ();
        loginFragment = new LoginFragment();
        transaction.add(R.id.fragment_Holder,loginFragment);
        transaction.commit();

    }

    private void showMainfragment(){
        transaction = fragmentManager.beginTransaction ();
        mainFragment = new MainFragment();
        transaction.add(R.id.fragment_Holder,mainFragment);
        transaction.commit();
    }
    public void replaceMainFragment(){
        transaction = fragmentManager.beginTransaction ();
        mainFragment = new MainFragment();
        transaction.replace(R.id.fragment_Holder,mainFragment);
        transaction.commit();
    }
    public void replaceLoginFragment(){
        transaction = fragmentManager.beginTransaction ();
        mainFragment = new MainFragment();
        transaction.replace(R.id.fragment_Holder,loginFragment);
        transaction.commit();
    }
    public void replaceRegFragment(){
        transaction = fragmentManager.beginTransaction ();
        regFragment = new RegFragment();
        transaction.replace(R.id.fragment_Holder,regFragment);
        transaction.commit();
    }
    private boolean isLogined(){
        String userId=getSavedUserId();
        return !userId.equals("-1");
    }
    //获取数据
    private String getSavedUserId(){
        String userId=preferences.getString("user_id","-1");
        return userId;
    }
    //插入数据
    public void setSaveUserId(String userId){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("user_id",userId);
        editor.commit();
    }
}

