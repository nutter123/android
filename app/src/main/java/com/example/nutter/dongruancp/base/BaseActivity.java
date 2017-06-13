package com.example.nutter.dongruancp.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nutter.dongruancp.adapter.BaseListAdapter;

/**
 * Created by nutter on 2017/4/12.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected  static String userId;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    public static void setUserId(String uid){
        userId=uid;
    }
    public static String getUserId(){
        return userId;
    }

}
