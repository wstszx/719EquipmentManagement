package com.example.a719equipmentmanagement.ui.device;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.DeviceSearchAdapter;
import com.example.a719equipmentmanagement.entity.Device;
import com.example.a719equipmentmanagement.entity.DeviceSearch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends DialogFragment {
    private static SearchFragment instance;

    public static SearchFragment getInstance() {
        if (instance == null) {
            instance = new SearchFragment();
        }
        return instance;
    }

    @BindView(R.id.recyclerview_search)
    RecyclerView recyclerView;

    private DeviceSearchAdapter adapter;
    private List<DeviceSearch> deviceSearchList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_search, container, false);

        Dialog dialog = getDialog();
        final Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(layoutParams);
        View view = inflater.inflate(R.layout.fragment_search,dialogWindow.findViewById(R.id.recyclerview_search), false);

        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogWindow.setLayout(-1, -2);
        initData();
        initAdapter(view);
        return view;

    }



    private void initAdapter(View view){
        adapter=new DeviceSearchAdapter(R.layout.base_device_search);
        adapter.setNewData(deviceSearchList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        deviceSearchList=new ArrayList<>();
        DeviceSearch deviceChoose=new DeviceSearch();
        deviceChoose.setId(456);
        deviceChoose.setName("三室1组");
        deviceSearchList.add(deviceChoose);

    }
}
