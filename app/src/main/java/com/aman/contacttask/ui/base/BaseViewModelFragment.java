package com.aman.contacttask.ui.base;

import com.aman.contacttask.BaseViewModel;

public abstract class BaseViewModelFragment<T extends BaseViewModel> extends BaseFragment {

    protected abstract T onCreateViewModel();


}
