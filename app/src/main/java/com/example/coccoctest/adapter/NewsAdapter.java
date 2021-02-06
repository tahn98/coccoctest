package com.example.coccoctest.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coccoctest.databinding.NewsRowItemBinding;
import com.example.coccoctest.model.RssItem;
import com.example.coccoctest.util.OnNewsItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<RssItem> listItem = new ArrayList<>();
    private OnNewsItemClickListener listener;

    public NewsAdapter() {
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsRowItemBinding binding = NewsRowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private NewsRowItemBinding binding;
        private OnNewsItemClickListener listener;

        public NewsViewHolder(NewsRowItemBinding binding, OnNewsItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void bind(RssItem item) {
            binding.setItem(item);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(view -> {
                listener.onNewsItemClick(item);
            });
        }
    }

    public void setNewsList(List<RssItem> listItem) {
        this.listItem.clear();
        this.listItem.addAll(listItem);
        notifyDataSetChanged();
    }

    public void setNewsItemClickListener(OnNewsItemClickListener listener) {
        this.listener = listener;
    }
}
