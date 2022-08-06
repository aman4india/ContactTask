/*
 * ©2019 the GSK group of companies or its licensor.
 * All rights reserved.
 */
package com.aman.contacttask.ui.base;

import android.app.Dialog;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.aman.contacttask.R;
import com.aman.contacttask.util.LoaderHelper;

/**
 * Base Fragment
 */
public abstract class BaseFragment extends Fragment implements LoaderHelper {

    private Dialog dialog = null;

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
