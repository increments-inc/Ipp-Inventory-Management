package com.incrementsinc.ipp_inventory_management.landing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incrementsinc.ipp_inventory_management.R;
import com.incrementsinc.ipp_inventory_management.adapter.HistoryListAdapter;
import com.incrementsinc.ipp_inventory_management.databinding.FragmentHistoryBinding;
import com.incrementsinc.ipp_inventory_management.listener.ItemCardClickListener;

public class HistoryFragment extends Fragment implements ItemCardClickListener {
    private FragmentHistoryBinding mHistoryBinding;
    private RecyclerView mRecyclerView;
    private HistoryListAdapter mAdapter;
    private NavController mNavController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mHistoryBinding = FragmentHistoryBinding.inflate(inflater, container, false);
        return mHistoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = NavHostFragment.findNavController(getParentFragment());
        mRecyclerView = mHistoryBinding.itemRecyclerView;
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HistoryListAdapter(getContext(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void OnClickListener(View view) {
        mNavController.navigate(R.id.itemDetailsFragment);
    }
}