package com.aman.contacttask.ui.registration;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.aman.contacttask.R;
import com.aman.contacttask.databinding.ActivityRegisterBinding;
import com.aman.contacttask.models.CreateRequest;
import com.aman.contacttask.models.RegResponse;
import com.aman.contacttask.models.UserResponse;
import com.aman.contacttask.ui.base.BaseActivity;
import com.aman.contacttask.ui.main.MainActivity;
import com.aman.contacttask.ui.main.UserRecyclerAdapter;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    @NonNull
    @Override
    protected RegisterViewModel createViewModel() {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(RegisterViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, new CreateUserMutableData());
        viewModel.getErrorMutableLiveData().observe(this, new ErrorMutable());

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

        binding.toolbar.imageButtonBack.setOnClickListener(view -> onBackPressed());
        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString("I'm already a member. ");
        str1.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str1.length(), 0);
        builder.append(str1);

        SpannableString str2 = new SpannableString("Sing In");
        str2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.toolbar_background)), 0, str2.length(), 0);
        builder.append(str2);

        binding.alreadyMember.setText(builder, TextView.BufferType.SPANNABLE);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateRequest request = new CreateRequest();
                request.setJob(binding.emailEditText.getText().toString());
                request.setName(binding.nameEditText.getText().toString());
                showProgress(RegisterActivity.this);
                viewModel.createUser(request);
            }
        });


    }

    public class CreateUserMutableData implements Observer<RegResponse> {

        @Override
        public void onChanged(RegResponse userResponse) {
            dismissProgress();
            binding.emailEditText.setText("");
            binding.passwordEditText.setText("");
            binding.nameEditText.setText("");
            Toast.makeText(RegisterActivity.this, "User Create successfully! User ID: "+userResponse.getId(), Toast.LENGTH_SHORT).show();
        }
    }

    public class ErrorMutable implements Observer<Throwable> {
        @Override
        public void onChanged(@Nullable Throwable throwable) {
            dismissProgress();
        }
    }
}