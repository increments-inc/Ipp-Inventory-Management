package com.incrementsinc.ipp_inventory_management.landing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.incrementsinc.ipp_inventory_management.R;
import com.incrementsinc.ipp_inventory_management.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding mHomeBinding;
    private Button mManualSeed;
    private NavController mNavController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mHomeBinding =  FragmentHomeBinding.inflate(inflater, container, false);
        return mHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = NavHostFragment.findNavController(getParentFragment());
        mManualSeed = mHomeBinding.btnManualSeed;
        mManualSeed.setOnClickListener(view1 -> mNavController.navigate(R.id.action_homeFragment_to_manualSeedFragment));
    }
}