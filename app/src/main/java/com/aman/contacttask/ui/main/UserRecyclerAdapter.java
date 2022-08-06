package com.aman.contacttask.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.contacttask.databinding.ListItemBinding;
import com.aman.contacttask.models.Datum;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.CustomViewHolder> {

    private List<Datum> listUser;
    private UserListener userListener;

    public interface UserListener {
        void onUserClicked(Datum userId);
    }

    public UserRecyclerAdapter(UserListener userListener, List<Datum> listUser){
        this.listUser = listUser;
        this.userListener = userListener;
    }

    public void setItems(List<Datum> items) {
        this.listUser = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    private Datum getItem(int position) {
        return listUser.get(position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ListItemBinding binding;
        public CustomViewHolder(@NonNull ListItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(int position) {
            Datum data = getItem(position);

            setClickListener(data);
            setTitle(data.getFirstName()+" "+data.getLastName());
            setImage(data.getAvatar());
            setDescription(data.getEmail());
        }

        private void setTitle(String title) {
            binding.username.setText(title);
        }

        private void setImage(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl).circleCrop().into(binding.avatar);
        }

        private void setDescription(String description) {
            binding.emailtxtview.setText("Email: "+ description);
        }

        private void setClickListener(Datum datum) {
            itemView.setTag(datum);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            userListener.onUserClicked((Datum) view.getTag());
        }

    }
}
