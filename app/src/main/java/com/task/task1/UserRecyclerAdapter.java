package com.task.task1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.task.task1.databinding.ProductRowBinding;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{
    private static final String TAG = "UserRecyclerAdapter";
    List<User> userList;

    public UserRecyclerAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductRowBinding productRowBinding= ProductRowBinding.inflate(layoutInflater,parent,false);
        return  new ViewHolder(productRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.ViewHolder holder, int position) {
      User user = userList.get(position);
      holder.productRowBinding.setUser(user);
      holder.productRowBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ProductRowBinding productRowBinding;

        public ViewHolder(@NonNull ProductRowBinding productRowBinding) {
            super(productRowBinding.getRoot());
            this.productRowBinding = productRowBinding;

            productRowBinding.active.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG,"onClick:" +userList.get(getAdapterPosition()));
                }
            });

        }

    }
}
