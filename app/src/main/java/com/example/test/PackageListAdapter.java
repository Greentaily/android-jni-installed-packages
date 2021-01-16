package com.example.test;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.lib.AppPackage;

public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.ViewHolder> {
    PackageListAdapter(List<AppPackage> packages) {
        this.packages = packages;
    }

    @Override
    public int getItemCount() {
        return packages == null ? 0 : packages.size();
    }

    @NonNull
    @Override
    public PackageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PackageListAdapter.ViewHolder holder, int position) {
        TextView name_view = holder.name_view;
        TextView size_view = holder.size_view;
        TextView isSystem_view = holder.isSystem_view;

        name_view.setText(packages.get(position).name);
        size_view.setText("Размер: " + String.valueOf(packages.get(position).size) + "Б");
        isSystem_view.setText(packages.get(position).isSystem ? "Системный" : "Пользовательский");
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name_view;
        public TextView size_view;
        public TextView isSystem_view;

        public ViewHolder(View itemView) {
            super(itemView);

            name_view = itemView.findViewById(R.id.textView_name);
            size_view = itemView.findViewById(R.id.textView_size);
            isSystem_view = itemView.findViewById(R.id.textView_isSystem);
        }
    }

    private List<AppPackage> packages;
}
