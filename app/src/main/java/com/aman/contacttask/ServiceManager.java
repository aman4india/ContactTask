package com.aman.contacttask;

import android.content.Context;

import androidx.annotation.NonNull;

import com.aman.contacttask.data.api.ApiServices;
import com.aman.contacttask.data.services.ServiceGenerator;
import com.aman.contacttask.models.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceManager {

    private static ServiceManager instance = null;
    private UserResponse userResponse;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public <T extends BaseViewModel> void getUsers(T viewModel, Context context) {
        ApiServices serviceInterface = ServiceGenerator.createService(ApiServices.class);
        Call<UserResponse> userResponseCall = serviceInterface.getUsers();
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    UserResponse listResponse = response.body();
                    viewModel.onSuccess(listResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                viewModel.onFailure(t);
            }
        });
    }
}
