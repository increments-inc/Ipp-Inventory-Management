package com.incrementsinc.ipp_inventory_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
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
            new IntentIntegrator(this).initiateScan();
        });

        mBottomAppBar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.bm_storage){
                mNavController.navigate(R.id.storageFragment);
            }
            return false;
        });
        mBottomAppBar.setNavigationOnClickListener(view -> {
            mNavController.navigate(R.id.homeFragment);
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavController.navigate(R.id.homeFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                Toast.makeText(this, "Code: "+result.getContents(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Scan Cancelled!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
