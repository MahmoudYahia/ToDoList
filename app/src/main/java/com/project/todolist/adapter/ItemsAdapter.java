package com.project.todolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.todolist.callback.ItemShareListener;
import com.project.todolist.R;
import com.project.todolist.datamodel.ItemKeyVal;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mah_y on 8/29/2017.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private final Context mContext;

    ItemShareListener shareListener;
    List<ItemKeyVal>itemList;
    public ItemsAdapter(Context context, ItemShareListener itemShareListener) {
        this.mContext=context;
        this.itemList=new ArrayList<>();
        this.shareListener=itemShareListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_view,parent,false);
        ItemViewHolder viewHolder= new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.title.setText(itemList.get(position).getItem().getItemTitle());
        holder.desc.setText(itemList.get(position).getItem().getItemDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               shareListener.onItemSelected(itemList.get(position).getItemKey());
            }
        });

    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setAdapterLsit(List<ItemKeyVal> lsit){
        this.itemList=lsit;

    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        public ItemViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.item_title);
            desc= (TextView) itemView.findViewById(R.id.item_desc);
        }
    }
}
