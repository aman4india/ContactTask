package com.aman.contacttask.data.api;

import com.aman.contacttask.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("api/users")
    Call<UserResponse> getUsers();


}
