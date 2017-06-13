package com.example.nutter.dongruancp.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Lists {
    private int pageCount;
    @SerializedName("results")
    private List<shop> list;
    //����get  set����
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public List<shop> getList() {
        return list;
    }
    public void setList(List<shop> list) {
        this.list = list;
    }

}
