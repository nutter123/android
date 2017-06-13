package com.example.nutter.dongruancp.bean;

/**
 * Created by nutter on 2017/4/10.
 */

public class getshop {
    /**
     * shop_id : 1
     * shopname : 满口香川菜2
     * address : 东软食堂三期
     * phonenum : 12345678901
     * intro : 地道的川菜
     * pic : http://img3.redocn.com/tupian/20141126/xiangxiwaipocai_3613936.jpg
     * comment : null
     * level : 5
     */

    private int shop_id;
    private String shopname;
    private String address;
    private String phonenum;
    private String intro;
    private String pic;
    private Object comment;
    private int level;

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getShop_id() {
        return shop_id;
    }

    public String getShopname() {
        return shopname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getIntro() {
        return intro;
    }

    public String getPic() {
        return pic;
    }

    public Object getComment() {
        return comment;
    }

    public int getLevel() {
        return level;
    }
}
