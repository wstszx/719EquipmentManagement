package com.example.a719equipmentmanagement.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<VDB extends ViewDataBinding> extends Fragment {


    protected VDB mbinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mbinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        init(savedInstanceState);
        return mbinding.getRoot();
    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getLayoutId();


}
