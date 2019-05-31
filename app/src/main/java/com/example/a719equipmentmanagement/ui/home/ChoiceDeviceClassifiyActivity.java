package com.example.a719equipmentmanagement.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ChoiceDeviceClassifiyAdapter;
import com.example.a719equipmentmanagement.adapter.DeviceClassifiyAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.DeptOne;
import com.example.a719equipmentmanagement.entity.DeptThree;
import com.example.a719equipmentmanagement.entity.DeptTwo;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceTypeOne;
import com.example.a719equipmentmanagement.entity.DeviceTypeTwo;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class ChoiceDeviceClassifiyActivity extends BaseActivity {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ChoiceDeviceClassifiyAdapter adapter;
    private String name;
    private int id;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findDeviceTypeData()
                .compose(CommonCompose.io2main(ChoiceDeviceClassifiyActivity.this))
                .subscribe(new BaseSubscriber<List<DeviceClassifiy>>(ChoiceDeviceClassifiyActivity.this) {
                    @Override
                    public void onSuccess(List<DeviceClassifiy> baseResponse) {
                        if (baseResponse != null && baseResponse.size() > 0) {
                            createSection(baseResponse);
                        }
                    }
                });
    }

    private void createSection(List<DeviceClassifiy> baseResponse) {
        List<MultiItemEntity> list = new ArrayList<>();
        for (DeviceClassifiy deviceClassifiy1 : baseResponse) {
            DeviceTypeOne typeOne = new DeviceTypeOne(deviceClassifiy1);
            List<DeviceClassifiy.ListBean> list1 = deviceClassifiy1.getList();
            for (DeviceClassifiy.ListBean listBean : list1) {
                DeviceTypeTwo typeTwo = new DeviceTypeTwo(listBean);
                typeOne.addSubItem(typeTwo);
            }
            list.add(typeOne);
        }
        initAdapter(list);
    }

    private void initAdapter(List<MultiItemEntity> list) {
        adapter = new ChoiceDeviceClassifiyAdapter(list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(adapter);

        for (int i = 0; i < adapter.getData().size(); i++) {
            adapter.expand(i, true);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiItemEntity multiItemEntity = (MultiItemEntity) adapter.getData().get(position);
                int itemType = multiItemEntity.getItemType();
                switch (itemType) {
                    case 0:
                        DeviceTypeOne typeOne = (DeviceTypeOne) multiItemEntity;
                        name = typeOne.getDept().getName();
                        id = typeOne.getDept().getId();
                        break;
                    case 1:
                        DeviceTypeTwo typeTwo = (DeviceTypeTwo) multiItemEntity;
                        name = typeTwo.getDept().getName();
                        id = typeTwo.getDept().getId();
                        break;
                }
                setChoice(position, view);
            }
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
        topbar.setTitle("选择设备分类");
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
        return R.layout.activity_choice_device_classifiy;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ChoiceDeviceClassifiyActivity.class);
        context.startActivity(starter);
    }
}
