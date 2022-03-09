package com.incrementsinc.ipp_inventory_management.landing;

import android.app.SearchManager;
import android.widget.SearchView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.incrementsinc.ipp_inventory_management.R;
import com.incrementsinc.ipp_inventory_management.adapter.StorageListAdapter;
import com.incrementsinc.ipp_inventory_management.database.ProductDB;
import com.incrementsinc.ipp_inventory_management.databinding.FragmentStorageBinding;

public class StorageFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    private FragmentStorageBinding mStorageBinding;
    private RecyclerView mRecyclerView;
    private StorageListAdapter mAdapter;
    private Toolbar mToolbar;

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
        mToolbar = mStorageBinding.toolBar;
        ((AppCompatActivity)requireActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle(" ");
        setHasOptionsMenu(true);

        mRecyclerView = mStorageBinding.itemRecyclerView;
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new StorageListAdapter(getContext());
        ProductDB db = new ProductDB(getContext());
        mAdapter.setProducts(db.selectRecords());
        mRecyclerView.setAdapter(mAdapter);

        mToolbar.setOnMenuItemClickListener(this);

    }

    private void actionSearch() {

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.tbSearch) {
            actionSearch();
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.storage_toolbar_menu, menu);

        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.tbSearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.requireActivity().getComponentName()));
    }
}
