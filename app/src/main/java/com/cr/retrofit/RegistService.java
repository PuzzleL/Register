package com.cr.retrofit;

import com.cr.register.activity.model.RegistResult;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Blacktea on 2016/5/17.
 */
public interface RegistService {
    @FormUrlEncoded
    @POST("/public/api/user/register/")
    Call<RegistResult> getRegistResult(@Field("username") String username,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("passwordConfirm") String passwordConfirm);
}
