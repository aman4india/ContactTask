package com.aman.contacttask.ui.registration;

import static com.aman.contacttask.data.services.ServiceGenerator.BASE_URL;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aman.contacttask.BaseResponse;
import com.aman.contacttask.BaseViewModel;
import com.aman.contacttask.ServiceManager;
import com.aman.contacttask.models.CreateRequest;
import com.aman.contacttask.models.RegResponse;
import com.aman.contacttask.models.SingleUserResponse;
import com.aman.contacttask.models.UserResponse;
import com.aman.contacttask.ui.details.DetailsActivityViewModel;

public class RegisterViewModel extends BaseViewModel {

    private static final String TAG = RegisterViewModel.class.getSimpleName();
    private MutableLiveData<RegResponse> userResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorMutableLiveData = new MutableLiveData<>();

    public RegisterViewModel(Application application) {
        super(application);
    }

    @Override
    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onSuccess(T response) {
        Log.e(TAG, "onSuccess: ");
        userResponseMutableLiveData.setValue((RegResponse) response);
    }

    @Override
    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onFailure(Throwable response) {
        Log.e(TAG, "onFailure: ");
        errorMutableLiveData.setValue(response);
    }

    public MutableLiveData<RegResponse> getUserMutableLiveData() {
        return userResponseMutableLiveData;
    }

    public MutableLiveData<Throwable> getErrorMutableLiveData() {
        return errorMutableLiveData;
    }

    public void createUser(CreateRequest request){
        ServiceManager.getInstance().createUser(this,BASE_URL + "api/users" , request );
    }



}
