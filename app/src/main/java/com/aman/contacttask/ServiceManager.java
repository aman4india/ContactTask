package com.aman.contacttask;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.aman.contacttask.data.api.ApiServices;
import com.aman.contacttask.data.services.ServiceGenerator;
import com.aman.contacttask.models.SingleUserResponse;
import com.aman.contacttask.models.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceManager {

    private static final String TAG = ServiceManager.class.getSimpleName();
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

    public <T extends BaseViewModel> void getUsers(T viewModel, String url) {
        Log.e(TAG, "getUsers: "+url );
        ApiServices serviceInterface = ServiceGenerator.createService(ApiServices.class);
        Call<UserResponse> userResponseCall = serviceInterface.getUsers(url);
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

    public <T extends BaseViewModel> void getSingleUsers(T viewModel, String url) {
        ApiServices serviceInterface = ServiceGenerator.createService(ApiServices.class);
        Call<SingleUserResponse> userResponseCall = serviceInterface.getSingleUsers(url);
        userResponseCall.enqueue(new Callback<SingleUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<SingleUserResponse> call, @NonNull Response<SingleUserResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    SingleUserResponse listResponse = response.body();
                    viewModel.onSuccess(listResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SingleUserResponse> call, @NonNull Throwable t) {
                viewModel.onFailure(t);
            }
        });
    }
}
