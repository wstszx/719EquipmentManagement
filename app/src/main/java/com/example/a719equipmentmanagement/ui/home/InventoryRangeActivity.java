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

import com.blankj.utilcode.util.ThreadUtils;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ChoiceContainerAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.ContainerOne;
import com.example.a719equipmentmanagement.entity.ContainerTwo;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.entity.InventoryRevordId;
import com.example.a719equipmentmanagement.entity.TreeData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class InventoryRangeActivity extends BaseActivity {

    private ChoiceContainerAdapter adapter;
    private int inventoryId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory_range;
    }

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

//    private void initData() {
//        RetrofitClient.getInstance().getService().findContainerData()
//                .compose(CommonCompose.io2main(InventoryRangeActivity.this))
//                .subscribe(new BaseSubscriber<List<ContainerData>>(InventoryRangeActivity.this) {
//                    @Override
//                    public void onSuccess(List<ContainerData> baseResponse) {
//                        if (baseResponse != null && baseResponse.size() > 0) {
//                            createSection(baseResponse);
//                        }
//                    }
//                });
//    }

    private void initData() {
        Single<List<ContainerData>> containers = RetrofitClient.getInstance().getService().findContainerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single<Integer> inventoryRevordIdSingle = RetrofitClient.getInstance().getService().findInventoryRevordId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single.zip(containers, inventoryRevordIdSingle, (baseResponse, inventoryRevordId) -> {
            boolean mainThread = ThreadUtils.isMainThread();
            if (mainThread) {
                if (baseResponse != null && baseResponse.size() > 0) {
                    createSection(baseResponse);
                }
                InventoryRangeActivity.this.inventoryId = inventoryRevordId;
            }
            return new Object();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Object>(this){

                });
//        Single.zip(deviceData2Single, treeData1,
//                (deviceData2, treeData, deviceClassifiys) -> {
//                    boolean mainThread = ThreadUtils.isMainThread();
//                    if (mainThread) {
//                        if (deviceData2 != null) {
//                            rows = deviceData2.getRows();
//                            if (rows != null && rows.size() > 0) {
//                                adapter.setNewData(rows);
//                            }
//                        }
//                        if (treeData != null && treeData.size() > 0) {
////                            createSeaction(treeData);
//                            createSection_deptClassify(treeData);
//                        }
//                        if (deviceClassifiys != null && deviceClassifiys.size() > 0) {
//                            createSection_classify(deviceClassifiys);
//                        }
//                    }
//                    return new Object();
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseSubscriber<Object>(this) {
//                    @Override
//                    public void onSuccess(Object o) {
//                        super.onSuccess(o);
//                    }
//                });
    }

    private void createSection(List<ContainerData> baseResponse) {
        List<MultiItemEntity> list = new ArrayList<>();
        for (ContainerData containerData : baseResponse) {
            ContainerOne containerOne = new ContainerOne(containerData);
            List<ContainerData.ContainersBean> list1 = containerData.getContainers();
            if (list1 != null && list1.size() > 0) {
                for (ContainerData.ContainersBean listBean : list1) {
                    ContainerTwo containerTwo = new ContainerTwo(listBean);
                    containerOne.addSubItem(containerTwo);
                }
            }
            list.add(containerOne);
        }
        initAdapter(list);
    }

    private void initAdapter(List<MultiItemEntity> list) {
        adapter = new ChoiceContainerAdapter(list);
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
                    multipleChoice(position, view, containerTwo);
                    break;
            }
//            setChoice(position, view);
        });
    }

    /**
     * 多选
     *
     * @param position
     */
    private List<Integer> containerIds = new ArrayList<>();

    private void multipleChoice(int position, View view, ContainerTwo containerTwo) {
        boolean select = ((ContainerTwo) adapter.getData().get(position)).isSelect();
        int containerId = containerTwo.getData().getId();
        if (select) {
            containerIds.remove(((Object) containerId));
            view.setBackgroundResource(R.color.white);
            ((ContainerTwo) adapter.getData().get(position)).setSelect(false);
        } else {
            containerIds.add(containerId);
            view.setBackgroundResource(R.color.app_color_blue);
            ((ContainerTwo) adapter.getData().get(position)).setSelect(true);
        }
    }

//    private int mPosition = -1;
//    private SparseArray choiceArray = new SparseArray();

//    private void setChoice(int position, View view) {
//        if (mPosition == position) {
//            view.setBackgroundResource(R.color.white);
//            mPosition = -1;
//            choiceArray.delete(mPosition);
//        } else if (mPosition != -1) {
//            View mView = (View) choiceArray.get(mPosition);
//            mView.setBackgroundResource(R.color.white);
//            view.setBackgroundResource(R.color.app_color_blue);
//            choiceArray.put(position, view);
//            mPosition = position;
//        } else {
//            view.setBackgroundResource(R.color.app_color_blue);
//            choiceArray.put(position, view);
//            mPosition = position;
//        }
//    }


    private void initTopbar() {
        topbar.setTitle("选择盘点范围");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            setInventoryRange();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    private void setInventoryRange() {
        JSONArray jsonArray = new JSONArray();
        for (Integer containerId : containerIds) {
            JSONObject jsonObject = new JSONObject();
            jsonArray.put(jsonObject);
            try {
                jsonObject.put("containerId", containerId);
                jsonObject.put("inventoryId", inventoryId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonArray.toString());
        RetrofitClient.getInstance().getService().setInventoryContainer(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(InventoryRangeActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {

                    }
                });
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, InventoryRangeActivity.class);
        context.startActivity(starter);
    }
}
