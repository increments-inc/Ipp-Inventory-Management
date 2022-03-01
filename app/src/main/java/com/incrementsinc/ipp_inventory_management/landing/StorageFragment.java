package com.incrementsinc.ipp_inventory_management.landing;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.incrementsinc.ipp_inventory_management.R;
import com.incrementsinc.ipp_inventory_management.adapter.StorageListAdapter;
import com.incrementsinc.ipp_inventory_management.database.ProductDB;
import com.incrementsinc.ipp_inventory_management.databinding.FragmentStorageBinding;
import com.incrementsinc.ipp_inventory_management.model.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StorageFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    private static final int RESULT_CODE = 1001;
    private static final int PERMISSION_TO_READ_EXTERNAL_FILE = 2001;
    private static final String TAG = StorageFragment.class.getSimpleName();
    private FragmentStorageBinding mStorageBinding;
    private RecyclerView mRecyclerView;
    private StorageListAdapter mAdapter;
    private Toolbar mToolbar;
    private File mFile;

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

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select Seed file to upload"), RESULT_CODE);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "Please install a file manager", Toast.LENGTH_SHORT).show();
        }
    }

    private void actionSearch() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_TO_READ_EXTERNAL_FILE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.tbSearch) {
            actionSearch();
        } else {
            actionReadRecordFromExternalStorage();
        }
        return true;
    }

    private void actionReadRecordFromExternalStorage() {
        String state = Environment.getExternalStorageState();
        ArrayList<Product> products = new ArrayList<>();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (getReadPermission()) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (dir.exists()) {
                    File file = new File(dir, "UPC.txt");
                    FileOutputStream os = null;
                    StringBuilder text = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            text.append("\n");

                        }
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onActivityResult: inFile: " + text);
                }
            } else {
                requestPermission();
            }
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), "Write External Storage permission allows us to read files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_TO_READ_EXTERNAL_FILE);
        }
    }

    private boolean getReadPermission() {
        int result = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

}
