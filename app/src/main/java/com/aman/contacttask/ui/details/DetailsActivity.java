package com.aman.contacttask.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.aman.contacttask.databinding.ActivityDetailsBinding;
import com.aman.contacttask.ui.base.BaseActivity;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsActivityViewModel> {

    Toolbar toolbar;


    @NonNull
    @Override
    protected DetailsActivityViewModel createViewModel() {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(DetailsActivityViewModel.class);
        return viewModel;
    }

    @NonNull
    @Override
    protected ActivityDetailsBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityDetailsBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            initFragment();
        }

        binding.toolbar.toolbarTitle.setText("test data");
        binding.toolbar.imageButtonBack.setOnClickListener(view -> onBackPressed());

    }

    public void initFragment() {
        Fragment detailsFragment = DetailsFragment.getFragment(getIntent().getIntExtra("id", 0));
        setPrimaryFragment(detailsFragment, false);
    }

    protected void setPrimaryFragment(final Fragment primaryFragment, final boolean isStackNeeded) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(binding.contentContainer.getId(), primaryFragment, primaryFragment.getClass().getName());
        if (isStackNeeded)
            ft.addToBackStack(primaryFragment.getTag());
        if (!isFinishing()) {
            ft.commitAllowingStateLoss();
        }
    }
}