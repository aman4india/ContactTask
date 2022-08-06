package com.aman.contacttask.ui.main;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aman.contacttask.BaseResponse;
import com.aman.contacttask.BaseViewModel;
import com.aman.contacttask.ServiceManager;
import com.aman.contacttask.models.Datum;
import com.aman.contacttask.models.UserResponse;

import java.util.List;

public class MainViewModel extends BaseViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    private MutableLiveData<UserResponse> userResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorMutableLiveData = new MutableLiveData<>();
    private UserRecyclerAdapter recyclerAdapter;

    public MainViewModel(Application application) {
        super(application);
    }

    public MutableLiveData<UserResponse> getUserMutableLiveData() {
        return userResponseMutableLiveData;
    }

    public MutableLiveData<Throwable> getErrorMutableLiveData() {
        return errorMutableLiveData;
    }

    @Override
    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onSuccess(T response) {
        Log.e(TAG, "onSuccess: ");
        setUserData(((UserResponse) response).getData());
        userResponseMutableLiveData.setValue((UserResponse) response);
    }

    @Override
    @SuppressWarnings("all")
    protected <T extends BaseResponse> void onFailure(Throwable response) {
        Log.e(TAG, "onFailure: ");
        errorMutableLiveData.setValue(response);
    }

    public void setUserData(List<Datum> data) {
        if (recyclerAdapter != null) {
            recyclerAdapter.setItems(data);
            recyclerAdapter.notifyDataSetChanged();
        }
    }

//    private void loadUserList(Context context, UserResponse userResponse) {
//        recyclerAdapter = new UserRecyclerAdapter(this, userResponse.getData());
//        setupUser(context, userResponse);
//        ServiceManager.getInstance().setUserResponse(userResponse);
//        recyclerAdapter.setItems(ServiceManager.getInstance().getUserResponse().getData());
//    }

    public void getUser(Context context) {
        Log.e(TAG, "getUser: ");
        ServiceManager.getInstance().getUsers(this, context);
    }

//    private void setupUser(Context context, UserResponse userResponse) {
//        List<String> upcList = new ArrayList<>();
//        Log.e(TAG, "setupUser: " + userResponse.getData().size());
//        ServiceManager.getInstance().getUsers(this, context);
//    }


}
