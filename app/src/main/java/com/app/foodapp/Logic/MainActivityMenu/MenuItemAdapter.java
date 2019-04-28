package com.app.foodapp.Logic.MainActivityMenu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.foodapp.R;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    private ArrayList<MenuItem> mMenuItems;
    final private OnListItemClickListener mOnListItemClickListener;

    MenuItemAdapter(ArrayList<MenuItem> menuItems, OnListItemClickListener listener){
        mMenuItems = menuItems;
        mOnListItemClickListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menu_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.name.setText(mMenuItems.get(position).getName());
        viewHolder.price.setText(Integer.toString(mMenuItems.get(position).getPrice()));
        viewHolder.icon.setImageResource(mMenuItems.get(position).getIconId());
    }

    public int getItemCount() {
        return mMenuItems.size();
    }

    public MenuItem getItem(int position) {
        return mMenuItems.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView price;
        ImageView icon;

        ViewHolder(View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
            icon = itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}