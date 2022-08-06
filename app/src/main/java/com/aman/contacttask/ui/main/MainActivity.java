package com.aman.contacttask.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.aman.contacttask.R;
import com.aman.contacttask.databinding.ActivityMainBinding;
import com.aman.contacttask.models.Datum;
import com.aman.contacttask.models.UserResponse;
import com.aman.contacttask.ui.base.BaseActivity;
import com.aman.contacttask.ui.details.DetailsActivity;
import com.aman.contacttask.ui.registration.RegisterActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements UserRecyclerAdapter.UserListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    UserRecyclerAdapter recyclerAdapter;
    MainViewModel viewModel;

    @androidx.annotation.NonNull
    @Override
    protected MainViewModel createViewModel() {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, new UserMutableData());
        viewModel.getErrorMutableLiveData().observe(this, new ErrorMutable());
        showProgress(this);
        viewModel.getUser(this);
        return viewModel;
    }

    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onUserClicked(Datum userId) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(this, RegisterActivity.class));
                return true;
            case R.id.action_search:
                Toast.makeText(this, "To be implement...", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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