package com.aman.contacttask;

import android.util.Log;

import androidx.annotation.NonNull;

import com.aman.contacttask.data.api.ApiServices;
import com.aman.contacttask.data.services.ServiceGenerator;
import com.aman.contacttask.models.CreateRequest;
import com.aman.contacttask.models.RegResponse;
import com.aman.contacttask.models.SingleUserResponse;
import com.aman.contacttask.models.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceManager {

    private static final String TAG = ServiceManager.class.getSimpleName();
    private static ServiceManager instance = null;

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public <T extends BaseViewModel> void getUsers(T viewModel, String url) {
        Log.e(TAG, "getUsers: " + url);
        ApiServices serviceInterface = ServiceGenerator.createService(ApiServices.class);
        Call<UserResponse> userResponseCall = serviceInterface.getUsers(url);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse listResponse = response.body();
                    viewModel.onSuccess(listResponse);
                } else {
                    viewModel.onFailure(new Throwable());
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
        Call<SingleUserResponse> userResponseCall = serviceInterface.getSingleUser(url);
        userResponseCall.enqueue(new Callback<SingleUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<SingleUserResponse> call, @NonNull Response<SingleUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SingleUserResponse listResponse = response.body();
                    viewModel.onSuccess(listResponse);
                } else {
                    viewModel.onFailure(new Throwable());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SingleUserResponse> call, @NonNull Throwable t) {
                viewModel.onFailure(t);
            }
        });
    }

    public <T extends BaseViewModel> void createUser(T viewModel, String url, CreateRequest request) {
        ApiServices serviceInterface = ServiceGenerator.createService(ApiServices.class);
        Call<RegResponse> userResponseCall = serviceInterface.createUser(request, url);
        userResponseCall.enqueue(new Callback<RegResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegResponse> call, @NonNull Response<RegResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegResponse listResponse = response.body();
                    viewModel.onSuccess(listResponse);
                } else {
                    viewModel.onFailure(new Throwable());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegResponse> call, @NonNull Throwable t) {
                viewModel.onFailure(t);
            }
        });
    }
}
