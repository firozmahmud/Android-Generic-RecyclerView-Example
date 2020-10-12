package com.example.androidgenericrecyclerviewexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidgenericrecyclerviewexample.R;
import com.example.androidgenericrecyclerviewexample.databinding.ItemDemoBinding;
import com.example.androidgenericrecyclerviewexample.model.InnerListModel;


import java.util.List;

public class ItemInnerListAdapter extends RecyclerView.Adapter<ItemInnerListAdapter.ViewHolder> {

    private Context context;
    private List<InnerListModel> models;
    private ItemClick itemClick;

    public ItemInnerListAdapter(Context context, List<InnerListModel> models) {
        this.context = context;
        this.models = models;

        itemClick = (ItemClick) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDemoBinding binding;
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_demo, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.binding.tvTitle.setText(models.get(position).getName());


        holder.binding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemClick != null) {
                    itemClick.innerItemClick(models.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemDemoBinding binding;

        public ViewHolder(@NonNull ItemDemoBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }


    public interface ItemClick {
        void innerItemClick(InnerListModel model);
    }
}
