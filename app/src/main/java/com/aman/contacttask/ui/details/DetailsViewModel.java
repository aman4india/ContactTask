package com.aman.contacttask.ui.details;

import static com.aman.contacttask.data.services.ServiceGenerator.BASE_URL;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aman.contacttask.BaseResponse;
import com.aman.contacttask.BaseViewModel;
import com.aman.contacttask.ServiceManager;
import com.aman.contacttask.models.Data;
import com.aman.contacttask.models.SingleUserResponse;
import com.aman.contacttask.models.UserResponse;

public class DetailsViewModel extends BaseViewModel {
    private static final String TAG = DetailsViewModel.class.getSimpleName();
    private MutableLiveData<SingleUserResponse> userResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorMutableLiveData = new MutableLiveData<>();

    public DetailsViewModel(Application application) {
        super(application);
    }

    public MutableLiveData<SingleUserResponse> getUserMutableLiveData() {
        return userResponseMutableLiveData;
    }

    public MutableLiveData<Throwable> getErrorMutableLiveData() {
        return errorMutableLiveData;
    }

    @Override
    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onSuccess(T response) {
        Log.e(TAG, "onSuccess: ");
        setUserData(((SingleUserResponse) response).getData());
        userResponseMutableLiveData.setValue((SingleUserResponse) response);
    }

    @Override
    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onFailure(Throwable response) {
        Log.e(TAG, "onFailure: ");
        errorMutableLiveData.setValue(response);
    }

    public void getUser(int userId) {
        Log.e(TAG, "getUser: " + userId);
        ServiceManager.getInstance().getSingleUsers(this, BASE_URL + "api/users/" + userId);
    }

    private void setUserData(Data data) {
        if (data != null) {
            Log.e(TAG, "setUserData: " + data.getFirstName());
        }
    }
}
