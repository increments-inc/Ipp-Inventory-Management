package com.incrementsinc.ipp_inventory_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.incrementsinc.ipp_inventory_management.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private NavController mNavController;
    private FragmentContainerView mFragmentContainer;
    private BottomAppBar mBottomAppBar;
    private FloatingActionButton mScannerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        //layout inflation
        mFragmentContainer = mainBinding.fragmentContainerView;
        mBottomAppBar = mainBinding.bottomAppBar;
        mScannerButton = mainBinding.floatingActionButton;

        //init settings
        mNavController = NavHostFragment.findNavController(mFragmentContainer.getFragment());
        mScannerButton.setOnClickListener(view -> {

        });

        mBottomAppBar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.bm_storage){
                mNavController.navigate(R.id.storageFragment);
            }else {
                mNavController.navigate(R.id.homeFragment);
            }
            return false;
        });
        mBottomAppBar.setNavigationOnClickListener(view -> {
            mNavController.navigate(R.id.historyFragment);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavController.navigate(R.id.homeFragment);
    }
}
