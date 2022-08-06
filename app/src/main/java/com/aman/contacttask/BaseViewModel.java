package com.aman.contacttask;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

public abstract class BaseViewModel extends AndroidViewModel {

    protected BaseViewModel(Application application){
        super(application);
    }

    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onSuccess(T response) {
    }

    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onFailure(Throwable response) {
    }

}
