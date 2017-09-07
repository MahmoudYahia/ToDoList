package com.project.todolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.todolist.callback.OnUserSelectedListener;
import com.project.todolist.R;
import com.project.todolist.datamodel.User;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mah_y on 8/14/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userViewHolder> {
    private Context mContext;
    private List<User> ContactsList;
    OnUserSelectedListener onUserSelectedListener;

    public UserAdapter(Context context, OnUserSelectedListener userSelectedListener) {
        this.ContactsList = new ArrayList<>();
        this.mContext = context;
        this.onUserSelectedListener = userSelectedListener;

    }

    @Override
    public userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        userViewHolder viewHolder = new userViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(userViewHolder holder, int position) {
        holder.contactName.setText(ContactsList.get(position).getUemail());
        Glide.with(mContext).load(ContactsList.get(position).getUemail()).placeholder(R.mipmap.ic_launcher).into(holder.contactImg);
        holder.itemView.setOnClickListener(v -> onUserSelectedListener.onUserSelected(ContactsList.get(position).getUid()));
    }


    @Override
    public int getItemCount() {
        return ContactsList.size();
    }

    public void setList(List list) {
        this.ContactsList = list;
    }


    class userViewHolder extends RecyclerView.ViewHolder {

        TextView contactName;
        CircleImageView contactImg;

        public userViewHolder(View itemView) {
            super(itemView);
            contactImg = (CircleImageView) itemView.findViewById(R.id.user_item_img);
            contactName = (TextView) itemView.findViewById(R.id.user_item_name);
        }

    }
}
