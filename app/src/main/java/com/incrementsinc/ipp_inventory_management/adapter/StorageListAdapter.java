package com.incrementsinc.ipp_inventory_management.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.incrementsinc.ipp_inventory_management.R;
import com.incrementsinc.ipp_inventory_management.databinding.ItemProductBinding;
import com.incrementsinc.ipp_inventory_management.model.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StorageListAdapter extends RecyclerView.Adapter<StorageListAdapter.ViewHolder> implements Filterable {
    private final Context mContext;
    private ArrayList<Product> mProducts;
    private ArrayList<Product> mFilteredProducts;

    public StorageListAdapter(Context context) {
        this.mContext = context;
    }

    public void setProducts(ArrayList<Product> products) {
        mProducts = products;
        mFilteredProducts = products;
    }

    @NonNull
    @Override
    public StorageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StorageListAdapter.ViewHolder holder, int position) {
        Product product = mFilteredProducts.get(position);
        holder.txtProduct.setText(product.getProduct());
        holder.txtDescription.setText(product.getDescription());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        holder.txtDateTime.setText(dateFormat.format(new Date(Long.parseLong(product.getCreatedAt()))));
    }

    @Override
    public int getItemCount() {
        if(mFilteredProducts != null){
            return mFilteredProducts.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding mItemProductBinding;
        public TextView txtProduct, txtDateTime, txtDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemProductBinding = ItemProductBinding.bind(itemView);
            txtProduct = mItemProductBinding.txtProductName;
            txtDateTime = mItemProductBinding.txtDateTime;
            txtDescription = mItemProductBinding.txtDescription;
        }
    }

    private final Filter searchFilter = new Filter() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String query = charSequence.toString();
            if(query.isEmpty()){
                mFilteredProducts = mProducts;
            }else{
                ArrayList<Product> products = new ArrayList<>();
                for (Product product : mProducts) {
                    if(product.getProduct().toLowerCase().contains(query.toLowerCase()) || product.getDescription().toLowerCase().contains(query.toLowerCase())){
                        products.add(product);
                    }
                }
                mFilteredProducts = products;
            }
            FilterResults results = new FilterResults();
            results.values = mFilteredProducts;
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mFilteredProducts = (ArrayList<Product>) filterResults.values;
            notifyDataSetChanged();
        }
    };
}
