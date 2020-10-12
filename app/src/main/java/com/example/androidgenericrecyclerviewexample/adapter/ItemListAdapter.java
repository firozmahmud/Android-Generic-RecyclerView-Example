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
import com.example.androidgenericrecyclerviewexample.databinding.ItemDemoExpendedBinding;
import com.example.androidgenericrecyclerviewexample.model.Model;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Model> list;
    private int typeNormalList = 1, typeExpendedList = 2;
    private ItemClick itemClick;

    public ItemListAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
        itemClick = (ItemClick) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == typeExpendedList) {
            // for expended list
            ItemDemoExpendedBinding binding;
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_demo_expended, parent, false);
            return new ExpViewHolder(binding);
        } else {
            // for normal list
            ItemDemoBinding binding;
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_demo, parent, false);
            return new ViewHolder(binding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == typeExpendedList) {
            showExpendedList(((ExpViewHolder) holder).binding, list.get(position), position);
        } else {
            showNormalList(((ViewHolder) holder).binding, list.get(position));
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType().equals("chapter")) {
            return typeExpendedList;
        } else {
            return typeNormalList;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemDemoBinding binding;

        public ViewHolder(@NonNull ItemDemoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    class ExpViewHolder extends RecyclerView.ViewHolder {

        ItemDemoExpendedBinding binding;

        public ExpViewHolder(@NonNull ItemDemoExpendedBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }


    private void showExpendedList(ItemDemoExpendedBinding binding, final Model model, final int position) {


        binding.tvTitle.setText(model.getName());

        if (model.isExpended()) {
            binding.indicator.setImageResource(R.drawable.arrow_down);
            // init another recyclerview and list
            binding.rvInnerRecycler.setVisibility(View.VISIBLE);
            ItemInnerListAdapter itemInnerListAdapter = new ItemInnerListAdapter(context, model.getInnerListModels());
            binding.rvInnerRecycler.setAdapter(itemInnerListAdapter);
        } else {
            binding.indicator.setImageResource(R.drawable.arrow_right);
            // close inner recyclerview and list
            binding.rvInnerRecycler.setVisibility(View.GONE);
        }


        binding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemClick != null) {

                    boolean isExpanded = model.isExpended();

                    isExpanded = !isExpanded;

                    model.setExpended(isExpanded);

                    itemClick.itemClick(model);

                    notifyItemChanged(position);
                }
            }
        });

    }

    private void showNormalList(ItemDemoBinding binding, final Model model) {

        binding.tvTitle.setText(model.getName());


        binding.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {
                    itemClick.itemClick(model);
                }
            }
        });
    }


    public interface ItemClick {
        void itemClick(Model model);
    }
}
