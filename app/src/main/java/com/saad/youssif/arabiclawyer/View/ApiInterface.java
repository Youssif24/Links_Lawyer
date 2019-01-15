package com.saad.youssif.arabiclawyer.View;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("checkUser2.php")
    @FormUrlEncoded
    Call<String> check_user(@Field("name") String name,
                               @Field("password") String password

    );

}
