package com.example.nutter.dongruancp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nutter on 2017/4/10.
 */

public class user implements Parcelable {
    /**
     * user_id : 1
     * username : lnn
     * userpass : 6512bd43d9caa6e02c990b0a82652dca
     * mobilenum :
     13476543211
     * address : å¤§è¿
     * comment : 测试
     */

    private int user_id;
    private String username;
    private String userpass;
    private String mobilenum;
    private String address;
    private String comment;

    protected user(Parcel in) {
        user_id = in.readInt();
        username = in.readString();
        userpass = in.readString();
        mobilenum = in.readString();
        address = in.readString();
        comment = in.readString();
    }

    public static final Creator<user> CREATOR = new Creator<user>() {
        @Override
        public user createFromParcel(Parcel in) {
            return new user(in);
        }

        @Override
        public user[] newArray(int size) {
            return new user[size];
        }
    };

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserpass() {
        return userpass;
    }

    public String getMobilenum() {
        return mobilenum;
    }

    public String getAddress() {
        return address;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(user_id);
        parcel.writeString(username);
        parcel.writeString(userpass);
        parcel.writeString(mobilenum);
        parcel.writeString(address);
        parcel.writeString(comment);
    }
}
