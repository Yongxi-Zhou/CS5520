package edu.neu.numad22sp_yongxi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private ArrayList<ItemURL> itemList;
    private Context context;
    public RecycleViewAdapter(ArrayList<ItemURL> itemList, LinkControllerPage linkControllerPage) {
        this.itemList = itemList;
        this.context = linkControllerPage;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindThisData(itemList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
