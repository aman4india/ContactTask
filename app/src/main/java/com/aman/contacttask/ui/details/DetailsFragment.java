package com.aman.contacttask.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aman.contacttask.R;
import com.aman.contacttask.databinding.DetailsFragmentBinding;
import com.aman.contacttask.models.SingleUserResponse;
import com.aman.contacttask.ui.base.BaseFragment;
import com.bumptech.glide.Glide;

public class DetailsFragment extends BaseFragment<DetailsFragmentBinding, DetailsViewModel> {

    private static final String TAG = DetailsFragment.class.getSimpleName();
    private static int userId;

    public static DetailsFragment getFragment(Integer userId) {
        Log.e(TAG, "getFragment: " + userId);
        DetailsFragment.userId = userId;
        return new DetailsFragment();
    }


    @NonNull
    @Override
    protected DetailsViewModel createViewModel() {
        return null;
    }

    @NonNull
    @Override
    protected DetailsFragmentBinding createViewBinding(LayoutInflater layoutInflater) {
        return null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetailsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        showProgress(getContext());
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        viewModel.getUser(userId);
        viewModel.getUserMutableLiveData().observe(getActivity(), new UserMutableData());
        viewModel.getErrorMutableLiveData().observe(getActivity(), new ErrorMutable());

        binding.supportUrl.setOnClickListener(view1 -> {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(getContext(), R.color.toolbar_background));
            CustomTabsIntent build = builder.build();
            String packageName = "com.android.chrome";
            if (packageName != null) {
                build.intent.setPackage(packageName);
                build.launchUrl(getContext(), Uri.parse(binding.supportUrl.getText().toString()));
            } else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(binding.supportUrl.getText().toString())));
            }

        });
        return view;
    }

    public class UserMutableData implements Observer<SingleUserResponse> {

        @Override
        public void onChanged(SingleUserResponse userResponse) {
            dismissProgress();
            dismissProgress();
            if (userResponse.getData() != null) {
                Glide.with(getContext()).load(userResponse.getData().getAvatar()).into(binding.userImage);
                binding.userName.setText(userResponse.getData().getFirstName() + " " + userResponse.getData().getLastName());
                binding.userEmail.setText(userResponse.getData().getEmail());
                binding.supportUrl.setText(userResponse.getSupport().getUrl());
                binding.desc.setText(userResponse.getSupport().getText());
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
