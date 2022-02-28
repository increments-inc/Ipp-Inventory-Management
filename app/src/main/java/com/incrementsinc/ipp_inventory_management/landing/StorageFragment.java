package com.incrementsinc.ipp_inventory_management.landing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incrementsinc.ipp_inventory_management.adapter.StorageListAdapter;
import com.incrementsinc.ipp_inventory_management.database.ProductDB;
import com.incrementsinc.ipp_inventory_management.databinding.FragmentStorageBinding;

public class StorageFragment extends Fragment {
    private FragmentStorageBinding mStorageBinding;
    private RecyclerView mRecyclerView;
    private StorageListAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mStorageBinding = FragmentStorageBinding.inflate(inflater, container, false);
        return mStorageBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = mStorageBinding.itemRecyclerView;
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new StorageListAdapter(getContext());
        ProductDB db = new ProductDB(getContext());
        mAdapter.setProducts(db.selectRecords());
        mRecyclerView.setAdapter(mAdapter);


    }
}
