/*
 * ©2019 the GSK group of companies or its licensor.
 * All rights reserved.
 */
package com.aman.contacttask.ui.base;

import android.app.Dialog;
import android.app.FragmentBreadCrumbs;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.aman.contacttask.BaseViewModel;
import com.aman.contacttask.R;
import com.aman.contacttask.databinding.DetailsFragmentBinding;
import com.aman.contacttask.util.LoaderHelper;

import org.xmlpull.v1.XmlPullParser;

/**
 * Base Fragment
 */
public abstract class BaseFragment<BINDING extends ViewBinding, VM extends BaseViewModel> extends Fragment implements LoaderHelper {

    protected VM viewModel;
    protected BINDING binding;
    private Dialog dialog = null;

    @NonNull
    protected abstract VM createViewModel();

    @NonNull
    protected abstract BINDING createViewBinding(LayoutInflater layoutInflater);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = (BINDING) DetailsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        viewModel = createViewModel();
        binding = createViewBinding(getLayoutInflater());
        return view;
    }



    @Override
    public void showProgress(Context context) {
        if (dialog == null && context != null) {
            dialog = new Dialog(context, R.style.CustomProgressTheme);
            dialog.setContentView(R.layout.custom_progress);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    @Override
    public void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
    }
}
