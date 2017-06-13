package com.example.nutter.dongruancp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by nutter on 2017/4/12.
 */

public abstract class BaseFragment extends Fragment {
    public String getUserId(){
    String userId=BaseActivity.getUserId();
    return userId;
    }
    public void setUserId(String userid){
    BaseActivity.setUserId(userid);
    }

}
