package com.incrementsinc.ipp_inventory_management.landing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.incrementsinc.ipp_inventory_management.R;
import com.incrementsinc.ipp_inventory_management.database.ProductDB;
import com.incrementsinc.ipp_inventory_management.databinding.FragmentManualSeedBinding;
import com.incrementsinc.ipp_inventory_management.model.Product;

public class ManualSeedFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {
    private FragmentManualSeedBinding mManualSeedBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mManualSeedBinding = FragmentManualSeedBinding.inflate(inflater, container, false);
        return mManualSeedBinding.getRoot();
    }

    private TextInputLayout tiClass, tiProduct, tiDescription, tiCrossRef, tiVendor;
    private EditText etClass, etProduct, etDescription, etCrossRef, etVendor;
    private Button btnSubmit;
    private TextView btnCancel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tiClass = mManualSeedBinding.tiClass;
        etClass = mManualSeedBinding.proClass;

        tiProduct = mManualSeedBinding.tiProduct;
        etProduct = mManualSeedBinding.proProduct;

        tiDescription = mManualSeedBinding.tiDescription;
        etDescription = mManualSeedBinding.proDescription;

        tiCrossRef = mManualSeedBinding.tiCrossRef;
        etCrossRef = mManualSeedBinding.proCrossRef;

        tiVendor = mManualSeedBinding.tiVendor;
        etVendor = mManualSeedBinding.proVendor;

        btnSubmit = mManualSeedBinding.btnSubmit;
        btnCancel = mManualSeedBinding.btnCancel;

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        etVendor.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_submit){
            onSubmit();
        }else {
            onCancel();
        }
    }

    private void onCancel() {

    }

    private void onSubmit() {
        Product product = new Product();
        product.setpClass(etClass.getText().toString().trim());
        if(TextUtils.isEmpty(product.getpClass())){
            tiClass.setError("Class cannot be empty!");
            return;
        }
        product.setProduct(etProduct.getText().toString().trim());
        if (TextUtils.isEmpty(product.getProduct())){
            tiProduct.setError("Product cannot be empty!");
            return;
        }
        product.setDescription(etDescription.getText().toString().trim());
        product.setCrossRef(etCrossRef.getText().toString().trim());
        if(TextUtils.isEmpty(product.getCrossRef())){
            tiCrossRef.setError("Reference cannot be empty");
            return;
        }

        product.setVendor(etVendor.getText().toString().trim());

        setRecordsToDatabase(product);
    }

    private void setRecordsToDatabase(Product product) {
        ProductDB db = new ProductDB(getContext());
        long report = db.createRecords(product.getpClass(), product.getProduct(), product.getDescription(), product.getCrossRef(), product.getVendor());
        if(report != -1){
            Toast.makeText(getContext(), "Manual Seeding Successful!", Toast.LENGTH_SHORT).show();
            etClass.setText(null);
            etProduct.setText(null);
            etDescription.setText(null);
            etCrossRef.setText(null);
            etVendor.setText(null);
            return;
        }
        Toast.makeText(getContext(), "Error! Try again later.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(i == EditorInfo.IME_ACTION_GO){
            onSubmit();
        }
        return false;
    }
}
