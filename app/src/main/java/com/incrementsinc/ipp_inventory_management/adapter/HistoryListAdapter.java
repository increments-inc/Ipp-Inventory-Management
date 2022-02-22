package com.incrementsinc.ipp_inventory_management.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.incrementsinc.ipp_inventory_management.R;
import com.incrementsinc.ipp_inventory_management.databinding.ItemProductBinding;
import com.incrementsinc.ipp_inventory_management.landing.ItemDetailsFragment;
import com.incrementsinc.ipp_inventory_management.listener.ItemCardClickListener;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {
    private Context mContext;
    private ItemCardClickListener mItemCardClickListener;

    public HistoryListAdapter(Context context, ItemCardClickListener itemCardClickListener) {
        mContext = context;
        mItemCardClickListener = itemCardClickListener;
    }

    @NonNull
    @Override
    public HistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListAdapter.ViewHolder holder, int position) {
        holder.mItemCard.setOnClickListener(view -> mItemCardClickListener.OnClickListener(view));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mItemCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemCard = itemView.findViewById(R.id.itemCard);
        }
    }
}
