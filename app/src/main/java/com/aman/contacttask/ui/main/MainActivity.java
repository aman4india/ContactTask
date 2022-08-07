package com.aman.contacttask.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.aman.contacttask.databinding.ActivityMainBinding;
import com.aman.contacttask.models.Datum;
import com.aman.contacttask.models.UserResponse;
import com.aman.contacttask.ui.base.BaseActivity;
import com.aman.contacttask.ui.details.DetailsActivity;
import com.aman.contacttask.ui.registration.RegisterActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements UserRecyclerAdapter.UserListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    UserRecyclerAdapter recyclerAdapter;

    @androidx.annotation.NonNull
    @Override
    protected MainViewModel createViewModel() {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, new UserMutableData());
        viewModel.getErrorMutableLiveData().observe(this, new ErrorMutable());
        showProgress(this);
        viewModel.getUser(this);
        recyclerAdapter = new UserRecyclerAdapter(MainActivity.this, new ArrayList<>());
        viewModel.setRecyclerAdapter(recyclerAdapter);
        return viewModel;
    }

    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.toolbar.imageButtonBack.setVisibility(View.GONE);
        binding.toolbar.imageAdd.setVisibility(View.VISIBLE);
        binding.toolbar.imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }


    @Override
    public void onUserClicked(Datum userId) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("id", userId.getId());
        startActivity(new Intent(intent));
    }

    public class UserMutableData implements Observer<UserResponse> {

        @Override
        public void onChanged(UserResponse userResponse) {
            dismissProgress();
            Log.e(TAG, "onChanged: " + userResponse.getData().size());
            dismissProgress();
            if (userResponse.getData().size() > 0) {
                recyclerAdapter = new UserRecyclerAdapter(MainActivity.this, userResponse.getData());
                viewModel.setUserData(userResponse.getData());
                binding.recyclerview.setAdapter(recyclerAdapter);
            }
        }
    }

    public class ErrorMutable implements Observer<Throwable> {
        @Override
        public void onChanged(@Nullable Throwable throwable) {
            dismissProgress();
        }
    }
}