package com.aman.contacttask.util;

import android.content.Context;

/**
 * Interface to handle progress bar.
 */
public interface LoaderHelper {

    void showProgress(Context context);

    void dismissProgress();
}
