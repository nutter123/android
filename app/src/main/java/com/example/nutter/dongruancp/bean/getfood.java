package com.example.nutter.dongruancp.bean;

/**
 * Created by nutter on 2017/4/10.
 */

public class getfood {
    /**
     * food_id : 2
     * foodname : 口水鸡
     * intro : 用健康的鸡肉做成
     * pic : http://images.meishij.net/p/20100714/3ad48d2a0d31630f800c40c4e53266b7.jpg
     * price : 20
     * shop_id : 1
     * type_id : 1
     * recommand : 1
     */

    private int food_id;
    private String foodname;
    private String intro;
    private String pic;
    private int price;
    private int shop_id;
    private int type_id;
    private int recommand;

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public void setRecommand(int recommand) {
        this.recommand = recommand;
    }

    public int getFood_id() {
        return food_id;
    }

    public String getFoodname() {
        return foodname;
    }

    public String getIntro() {
        return intro;
    }

    public String getPic() {
        return pic;
    }

    public int getPrice() {
        return price;
    }

    public int getShop_id() {
        return shop_id;
    }

    public int getType_id() {
        return type_id;
    }

    public int getRecommand() {
        return recommand;
    }
}
