package com.aman.contacttask.ui.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.aman.contacttask.R;
import com.aman.contacttask.databinding.ActivityMainBinding;
import com.aman.contacttask.databinding.ActivityRegisterBinding;
import com.aman.contacttask.ui.base.BaseActivity;
import com.aman.contacttask.ui.main.MainActivity;
import com.aman.contacttask.ui.main.MainViewModel;
import com.aman.contacttask.ui.main.UserRecyclerAdapter;

import java.util.ArrayList;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {

    @NonNull
    @Override
    protected RegisterViewModel createViewModel() {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(RegisterViewModel.class);
        return viewModel;
    }

    @NonNull
    @Override
    protected ActivityRegisterBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityRegisterBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}