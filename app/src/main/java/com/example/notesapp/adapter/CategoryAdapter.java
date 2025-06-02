package com.example.notesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.listner.onCategoryClickListner;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final onCategoryClickListner categoryListner;
    private List<String> categoryList = new ArrayList<>();

    public CategoryAdapter(onCategoryClickListner categoryListner){
        super();
        this.categoryListner = categoryListner;
    }

    public void setCategoryList(List<String> categoryList){
        this.categoryList.clear();
        this.categoryList.add(0, "All");
        if (categoryList != null) {
            this.categoryList.addAll(categoryList);
            if (categoryList.isEmpty()){
                this.categoryList.clear();
            }
        }
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycler_block, parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        String category = categoryList.get(position);
        holder.categoryItem.setText(category);

        holder.categoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCategory = holder.categoryItem.getText().toString();
                categoryListner.onCategoryClick(selectedCategory);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (categoryList == null) ? 0 : categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryItem;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryItem = itemView.findViewById(R.id.categoryItem);
        }
    }
}
