package com.incrementsinc.ipp_inventory_management.landing;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.incrementsinc.ipp_inventory_management.R;
import com.incrementsinc.ipp_inventory_management.adapter.StorageListAdapter;
import com.incrementsinc.ipp_inventory_management.database.ProductDB;
import com.incrementsinc.ipp_inventory_management.databinding.FragmentStorageBinding;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

public class StorageFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    private static final int RESULT_CODE = 1001;
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
        mRecyclerView = mStorageBinding.itemRecyclerView;
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new StorageListAdapter(getContext());
        ProductDB db = new ProductDB(getContext());
        mAdapter.setProducts(db.selectRecords());
        mRecyclerView.setAdapter(mAdapter);

        mToolbar.setOnMenuItemClickListener(this);

    }

    private void actionUpload() {
        new MaterialFilePicker().withSupportFragment(this)
                .withCloseMenu(true)
                .withFilter(Pattern.compile(".*\\.(txt)$"))
                .withFilterDirectories(false)
                .withRequestCode(RESULT_CODE)
                .withTitle("Barcode Scanner")
                .start();
    }

    private void actionSearch() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_CODE){
            if(resultCode == RESULT_OK){
                String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.tbSearch){
            actionSearch();
        }else{
            actionUpload();
        }
        return true;
    }
}
