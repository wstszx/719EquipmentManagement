package com.example.a719equipmentmanagement.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.InventoriesAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Inventories;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InventoryTaskFragment extends BaseFragment {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.constraint)
    ConstraintLayout constraint;
    private InventoriesAdapter adapter;
    private boolean isCanClick;
    private int inventoryId;


    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initAdapter();
        initData();
    }

    private void initAdapter() {
        adapter = new InventoriesAdapter(R.layout.inventory_task_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClickListener((adapter, view, position) -> {
//            Inventories.RowsBean rowsBean = (Inventories.RowsBean) adapter.getData().get(position);
//            int id = rowsBean.getId();
//            Bundle bundle = new Bundle();
//            bundle.putInt("id", id);
//            Navigation.findNavController(view).navigate(R.id.scanFragment, bundle);
//        });
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
//            @Override
//            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
//                Inventories.RowsBean rowsBean = (Inventories.RowsBean) adapter.getData().get(position);
//                id = rowsBean.getId();
//                switch (view.getId()) {
//                    case R.id.tv_cancel:
//                        cancelInventory();
//                        break;
//                    case R.id.tv_continue:
//                        continueInventory(view);
//                        break;
//                }
//                return false;
//            }
//        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Inventories.RowsBean rowsBean = (Inventories.RowsBean) adapter.getData().get(position);
                inventoryId = rowsBean.getId();
                switch (view.getId()) {
                    case R.id.tv_cancel:
                        cancelInventory();
                        break;
                    case R.id.tv_continue:
                        continueInventory(view);
                        break;
                }
            }
        });
    }

    /**
     * 继续盘点
     */
    private void continueInventory(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("inventoryId", inventoryId);
        Navigation.findNavController(view).navigate(R.id.scanFragment, bundle);
    }

    /**
     * 取消盘点
     */
    private void cancelInventory() {
        RetrofitClient.getInstance().getService().cancelInventoryTask(inventoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(getContext()) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response.getCode() == 0) {
                            initData();
                        }
                    }
                });
    }

    private void initTopbar() {
        topbar.setTitle("盘点任务");
//        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.scanFragment);
//        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> Objects.requireNonNull(getActivity()).finish());
    }

    private void initData() {
        RetrofitClient.getInstance().getService().getInventories()
                .compose(CommonCompose.io2main(getContext()))
                .subscribe(new BaseSubscriber<Inventories>(getContext()) {
                    @Override
                    public void onSuccess(Inventories inventories) {
                        if (inventories != null) {
                            List<Inventories.RowsBean> rows = inventories.getRows();
                            if (rows != null && rows.size() > 0) {
                                isCanClick = false;
                                adapter.setNewData(rows);
                            } else {
                                adapter.setNewData(rows);
                                isCanClick = true;
                            }
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory_task;
    }

    @OnClick(R.id.constraint)
    public void onViewClicked(View view) {
        if (isCanClick) {
            Navigation.findNavController(view).navigate(R.id.newInventoryTaskFragment);
        } else {
            ToastUtils.showShort("已存在盘点任务");
        }
    }
}
