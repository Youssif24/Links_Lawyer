package com.saad.youssif.arabiclawyer.View;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("checkUser2.php")
    @FormUrlEncoded
    Call<String> check_user(@Field("name") String name,
                                  @Field("password") String password

    );

    @POST("lawyerRegister.php")
    @FormUrlEncoded
    Call<String> insertUser(@Field("name") String name,
                            @Field("password") String password,
                            @Field("phone") String phone,
                            @Field("address") String address

    );

}
