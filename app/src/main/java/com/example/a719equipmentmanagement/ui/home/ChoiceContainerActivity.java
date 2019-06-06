package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ChoiceContainerAdapter;
import com.example.a719equipmentmanagement.adapter.ChoiceDeviceClassifiyAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.ContainerOne;
import com.example.a719equipmentmanagement.entity.ContainerTwo;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceTypeOne;
import com.example.a719equipmentmanagement.entity.DeviceTypeTwo;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class ChoiceContainerActivity extends BaseActivity {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String name;
    private int id;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findContainerData()
                .compose(CommonCompose.io2main(ChoiceContainerActivity.this))
                .subscribe(new BaseSubscriber<List<ContainerData>>(ChoiceContainerActivity.this) {
                    @Override
                    public void onSuccess(List<ContainerData> baseResponse) {
                        if (baseResponse != null && baseResponse.size() > 0) {
                            createSection(baseResponse);
                        }
                    }
                });
    }

    private void createSection(List<ContainerData> baseResponse) {
        List<MultiItemEntity> list = new ArrayList<>();
        for (ContainerData containerData : baseResponse) {
            ContainerOne containerOne = new ContainerOne(containerData);
            List<ContainerData.ContainersBean> list1 = containerData.getContainers();
            for (ContainerData.ContainersBean listBean : list1) {
                ContainerTwo containerTwo = new ContainerTwo(listBean);
                containerOne.addSubItem(containerTwo);
            }
            list.add(containerOne);
        }
        initAdapter(list);
    }

    private void initAdapter(List<MultiItemEntity> list) {
        ChoiceContainerAdapter adapter = new ChoiceContainerAdapter(list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);

        for (int i = 0; i < adapter.getData().size(); i++) {
            adapter.expand(i, true);
        }
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            MultiItemEntity multiItemEntity = (MultiItemEntity) adapter1.getData().get(position);
            int itemType = multiItemEntity.getItemType();
            switch (itemType) {
                case 0:
                    ContainerOne containerOne = (ContainerOne) multiItemEntity;
                    name = containerOne.getData().getName();
                    id = containerOne.getData().getId();
                    break;
                case 1:
                    ContainerTwo containerTwo = (ContainerTwo) multiItemEntity;
                    name = containerTwo.getData().getName();
                    id = containerTwo.getData().getId();
                    break;
            }
            setChoice(position, view);
        });
    }

    private int mPosition = -1;
    private SparseArray choiceArray = new SparseArray();

    private void setChoice(int position, View view) {
        if (mPosition == position) {
            view.setBackgroundResource(R.color.white);
            mPosition = -1;
            choiceArray.delete(mPosition);
        } else if (mPosition != -1) {
            View mView = (View) choiceArray.get(mPosition);
            mView.setBackgroundResource(R.color.white);
            view.setBackgroundResource(R.color.app_color_blue);
            choiceArray.put(position, view);
            mPosition = position;
        } else {
            view.setBackgroundResource(R.color.app_color_blue);
            choiceArray.put(position, view);
            mPosition = position;
        }
    }


    private void initTopbar() {
        topbar.setTitle("选择货柜");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("id", id);
            intent.putExtra("name", name);
            setResult(RESULT_OK, intent);
            finish();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_choice_container;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ChoiceContainerActivity.class);
        context.startActivity(starter);
    }
}
