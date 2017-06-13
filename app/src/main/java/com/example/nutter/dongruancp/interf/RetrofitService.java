package com.example.nutter.dongruancp.interf;

import com.example.nutter.dongruancp.bean.food;
import com.example.nutter.dongruancp.bean.getfood;
import com.example.nutter.dongruancp.bean.getshop;
import com.example.nutter.dongruancp.bean.isCollected;
import com.example.nutter.dongruancp.bean.login;
import com.example.nutter.dongruancp.bean.order;
import com.example.nutter.dongruancp.bean.search;
import com.example.nutter.dongruancp.bean.shop;
import com.example.nutter.dongruancp.bean.user;
import com.example.nutter.dongruancp.bean.userCollection;
import com.example.nutter.dongruancp.bean.simpleResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nutter on 2017/4/10.
 *user_id
 food_id
 num
 sum
 suggesttime
 */

public interface RetrofitService {
    @GET("userRegister.do")
    Call<simpleResult> userRegister(@Query("username") String username,
                                    @Query("userpass") String userpass,
                                    @Query("mobilenum") String mobilenum,
                                    @Query("address") String address,
                                    @Query("comment") String comment);
    @GET("getAllShops.do")
    Call<List<shop>> getAllShops();
    @GET("userLogin.do")
    Call<login> userLogin(@Query("username") String username,
                             @Query("userpass") String userpass);
    @GET("getFoodByShop.do")
    Call<List<food>> getFoodByShop(@Query("shop_id") int shop_id);
    @GET("insertOrder.do")
    Call<simpleResult> insertOrder(@Query("user_id") int user_id,
                                    @Query("food_id") int food_id,
                                    @Query("num") int num,
                                    @Query("sum") double sum,
                                    @Query("suggesttime") String suggesttime);
    @GET("getShopById.do")
    Call<shop> getShopById(@Query("shop_id") int shop_id);
    @GET("getFoodById.do")
    Call<food> getFoodById(@Query("food_id") int food_id);
    @GET("getAllUserFoodOrder.do")
    Call<List<order>> getAllUserFoodOrder(@Query("food_id") int food_id);
    @GET("getAllUserCollection.do")
    Call<List<userCollection>> getAllUserCollection(@Query("user_id") int user_id,
                                            @Query("flag") int flag);
    @GET("userCollectShop.do")
    Call<simpleResult> userCollectShop(@Query("user_id") int user_id,
                                    @Query("shop_id") int shop_id);

    @GET("userCollectFood.do")
    Call<simpleResult> userCollectFood(@Query("user_id") int user_id,
                                    @Query("food_id") int food_id);

    @GET("isCollected.do")
    Call<isCollected> isCollected(@Query("user_id") int user_id,
                                   @Query("shop_food_id") int shop_food_id,
                                   @Query("flag") int flag);
    @GET("getFoodBySearch.do")
    Call<List<food>> getFoodBySearch(@Query("search") String search);

    /*user_id
            username
    userpass
            mobilenum
    address*/
    @GET("updateUserById.do")
    Call<simpleResult> updateUserById(@Query("user_id") int user_id,
                                    @Query("username") String username,
                                    @Query("userpass") String userpass,
                                    @Query("mobilenum") String mobilenum,
                                    @Query("address") String address);

    @GET("getAllUserOrder.do")
    Call<List<order>> getAllUserOrder(@Query("user_id") int user_id);

    @GET("getAllUserComment.do")
    Call<List<order>> getAllUserComment(@Query("user_id") int user_id);

    @GET("insertComment.do")
    Call<simpleResult> insertComment(@Query("order_id") int order_id,
                                    @Query("content") String content);
    @GET("updateComment.do")
    Call<simpleResult> updateComment(@Query("order_id") int order_id,
                                    @Query("content") String content);
    @GET("deleteComment.do")
    Call<simpleResult> deleteComment(@Query("order_id") int order_id);

    @GET("getUserById.do")
    Call<user> getUserById(@Query("user_id") int user_id);


}
