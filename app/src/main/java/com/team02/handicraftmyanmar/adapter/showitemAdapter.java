package com.team02.handicraftmyanmar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.team02.handicraftmyanmar.DetailActivity;
import com.team02.handicraftmyanmar.R;
import com.team02.handicraftmyanmar.model.itemModel;

import java.util.List;

public class showitemAdapter extends RecyclerView.Adapter<showitemAdapter.ItemViewHolder> {

    private Context mContext;
    private List<itemModel.Item> itemList;

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView itemImage;
        TextView tvitemName, tvitemAddress;

        itemModel.Item item;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.iv_itemImage);
            tvitemName = (TextView) itemView.findViewById(R.id.tv_itemName);
            tvitemAddress = (TextView) itemView.findViewById(R.id.tv_itemAddress);
            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(mContext, DetailActivity.class);
                    Log.i("ID DATA", item.getItemName());
                    detailIntent.putExtra("data", item.getItemId());
                    mContext.startActivity(detailIntent);


                    //  Log.i("id", itemList.get(getAdapterPosition()).getItemId());
                    //mContext.startActivity(detailIntent);
                }
            });

        }

        public void bindView(itemModel.Item item) {

            this.item = item;
            Glide.with(mContext)
                    .load(item.getItemImage())
                    .into(itemImage);
            tvitemName.setText(item.getItemName());
            tvitemAddress.setText(item.getItemAddress());
        }

        @Override
        public void onClick(View v) {

        }

    }

    public showitemAdapter( List<itemModel.Item> itemList,Context context) {
        this.itemList = itemList;
        mContext=context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewGroup) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int postion) {
        itemModel.Item item = itemList.get(postion);
        holder.bindView(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }





}
