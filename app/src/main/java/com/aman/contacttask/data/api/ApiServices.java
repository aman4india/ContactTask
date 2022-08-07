package com.aman.contacttask.data.api;

import com.aman.contacttask.models.SingleUserResponse;
import com.aman.contacttask.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiServices {

    @GET
    Call<UserResponse> getUsers(@Url String url);

    @GET
    Call<SingleUserResponse> getSingleUsers(@Url String url);

}
