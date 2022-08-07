package com.aman.contacttask.ui.details;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aman.contacttask.BaseResponse;
import com.aman.contacttask.BaseViewModel;
import com.aman.contacttask.models.UserResponse;

public class DetailsActivityViewModel extends BaseViewModel {
    private static final String TAG = DetailsActivityViewModel.class.getSimpleName();
    private MutableLiveData<UserResponse> userResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorMutableLiveData = new MutableLiveData<>();


    public DetailsActivityViewModel(Application application) {
        super(application);
    }

    @Override
    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onSuccess(T response) {
        Log.e(TAG, "onSuccess: ");
    }

    @Override
    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onFailure(Throwable response) {
        Log.e(TAG, "onFailure: ");
    }


}
