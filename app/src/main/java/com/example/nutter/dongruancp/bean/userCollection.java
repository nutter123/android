package com.example.nutter.dongruancp.bean;

/**
 * Created by nutter on 2017/4/10.
 */

public class userCollection {
    /**
     * user_id : 1
     * food_id : 0
     * shop_id : 2
     * collect_id : 117
     * username : lnn
     * foodname : null
     * shopname : 东北一家人
     * flag : 0
     * pic : http://picapi.ooopic.com/01/43/03/11b1OOOPIC53.jpg
     * price : 0
     * address : 东软食堂三期
     */

    private int user_id;
    private int food_id;
    private int shop_id;
    private int collect_id;
    private String username;
    private String foodname;
    private String shopname;
    private int flag;
    private String pic;
    private int price;
    private String address;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public void setCollect_id(int collect_id) {
        this.collect_id = collect_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public int getCollect_id() {
        return collect_id;
    }

    public String getUsername() {
        return username;
    }

    public String getFoodname() {
        return foodname;
    }

    public String getShopname() {
        return shopname;
    }

    public int getFlag() {
        return flag;
    }

    public String getPic() {
        return pic;
    }

    public int getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }
}
