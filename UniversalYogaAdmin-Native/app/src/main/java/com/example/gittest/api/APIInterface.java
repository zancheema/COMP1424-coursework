package com.example.gittest.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {
    @FormUrlEncoded
    @POST("SubmitClasses")
    Call<ClassesPostResponse> submitClasses(@Field("jsonpayload") ClassesPayload payload, @Field("b1") String b1);
}
