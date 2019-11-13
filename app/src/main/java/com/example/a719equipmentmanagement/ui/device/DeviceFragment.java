package com.example.a719equipmentmanagement.ui.device;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.BaseFilterAdapter;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseSingleFilter;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.view.DropDownMenu;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class DeviceFragment extends BaseFragment {

    private static final int DEVICE_DETAIL = 1;
    private static DeviceFragment fragment;
    @BindView(R.id.constrainlayout)
    ConstraintLayout constrainlayout;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @BindView(R.id.topbar)
    QMUITopBar topbar;

    private String[] filterArray = {"科室分类", "设备分类", "状态"};
    private String[] options = {"可用", "借用", "送检占用", "送检", "报废占用", "报废", "封存", "解封占用", "过期", "外借", "未送检", "不限"};

    private List<BaseSingleFilter> filters = new ArrayList<>();
    private DeviceAdapter adapter;
    private BaseFilterAdapter adapter11;
    private BaseFilterAdapter adapter21;
    private BaseFilterAdapter adapter22;
    private Map<String, Object> map = new HashMap<>();
    private List<DeviceData2.RowsBean> rows;
    private int pageNum = 1;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerview5;

    public static DeviceFragment newInstance() {
        if (fragment == null) {
            fragment = new DeviceFragment();
        }
        return fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initView();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_device;
    }

    private void initData() {
        for (String option : options) {
            BaseSingleFilter filter = new BaseSingleFilter();
            filter.setName(option);
            filters.add(filter);
        }
        convertRequest();
    }

    private void convertRequest() {
        map.put("pageNum", 1);
        map.put("pageSize", 10);
        Single<DeviceData2> deviceData2Single = RetrofitClient.getInstance().getService().findDeviceData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single<List<DeptList>> listSingle1 = RetrofitClient.getInstance().getService().getDeptList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single<List<DeviceClassifiy>> listSingle = RetrofitClient.getInstance().getService().findDeviceTypeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single.zip(deviceData2Single, listSingle1, listSingle,
                (deviceData2, deptLists, deviceClassifiys) -> {
                    boolean mainThread = ThreadUtils.isMainThread();
                    if (mainThread) {
                        if (deviceData2 != null) {
                            rows = deviceData2.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                                if (rows.size() != 10) {
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                                adapter.setEmptyView(R.layout.empty, recyclerview5);
                            }
                        }
                        if (deptLists != null && deptLists.size() > 0) {
                            createSection_deptClassify(deptLists);
                        }
                        if (deviceClassifiys != null && deviceClassifiys.size() > 0) {
                            createSection_classify(deviceClassifiys);
                        }
                    }
                    return new Object();
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Object>(Objects.requireNonNull(getContext())) {
                    @Override
                    public void onSuccess(Object o) {
                        super.onSuccess(o);
                    }
                });
    }

    private void initView() {
        List<View> popupViews = new ArrayList<>();

//        单列表
        View view1 = getLayoutInflater().inflate(R.layout.base_triple_list, null);
        RecyclerView recyclerView11 = view1.findViewById(R.id.recyclerView1);
        recyclerView11.setLayoutManager(new LinearLayoutManager(Objects.requireNonNull(getContext())));
        adapter11 = new BaseFilterAdapter(R.layout.base_filter_right_item);
        recyclerView11.setAdapter(adapter11);
        adapter11.setOnItemClickListener((adapter, view, position) -> {
            if (position == adapter11.getData().size() - 1) {
                map.remove("deptIds");
                dropDownMenu.setTabText(filterArray[0]);
//                dropDownMenu.closeMenu();
//                getDeviceData(map);
            } else {
                BaseSingleFilter baseSingleFilter1 = adapter11.getData().get(position);
                String name = baseSingleFilter1.getName();
                int deptId = baseSingleFilter1.getId();
                map.put("deptIds", deptId);
                dropDownMenu.setTabText(name);
            }
            dropDownMenu.closeMenu();
            refreshLayout.resetNoMoreData();
            pageNum = 1;
            map.put("pageNum", 1);
            getDeviceData(map);
        });

//        双列表
        View view2 = getLayoutInflater().inflate(R.layout.base_double_list, null);
        RecyclerView recyclerView21 = view2.findViewById(R.id.recyclerView1);
        RecyclerView recyclerView22 = view2.findViewById(R.id.recyclerView2);
        recyclerView21.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView22.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter21 = new BaseFilterAdapter(R.layout.base_filter_right_item);
        adapter22 = new BaseFilterAdapter(R.layout.base_filter_right_item);
        recyclerView21.setAdapter(adapter21);
        recyclerView22.setAdapter(adapter22);

        recyclerView22.setVisibility(View.INVISIBLE);
        adapter21.setOnItemClickListener((adapter, view, position) -> {
            if (position == adapter21.getData().size() - 1) {
                map.remove("categoryIds");
                dropDownMenu.setTabText(filterArray[1]);
                dropDownMenu.closeMenu();
                refreshLayout.resetNoMoreData();
                pageNum = 1;
                map.put("pageNum", 1);
                getDeviceData(map);
            } else {
                BaseSingleFilter baseSingleFilter = adapter21.getData().get(position);
                String name = baseSingleFilter.getName();
                int id = baseSingleFilter.getId();
                boolean existRight = baseSingleFilter.isExistRight();
                if (existRight) {
                    recyclerView22.setVisibility(View.VISIBLE);
                    List<BaseSingleFilter> baseSingleFilters = deviceClassifiysMap.get(baseSingleFilter);
                    adapter22.setNewData(baseSingleFilters);
                } else {
                    dropDownMenu.setTabText(name);
                    dropDownMenu.closeMenu();
                    recyclerView22.setVisibility(View.INVISIBLE);
                    refreshLayout.resetNoMoreData();
                    map.put("categoryIds", id);
                    pageNum = 1;
                    map.put("pageNum", 1);
                    getDeviceData(map);
                }
            }
        });
        adapter22.setOnItemClickListener((adapter, view, position) -> {
            BaseSingleFilter baseSingleFilter = adapter22.getData().get(position);
            String name = baseSingleFilter.getName();
            int id = baseSingleFilter.getId();
            dropDownMenu.setTabText(name);
            dropDownMenu.closeMenu();
            recyclerView22.setVisibility(View.INVISIBLE);
            refreshLayout.resetNoMoreData();
            map.put("categoryIds", id);
            pageNum = 1;
            map.put("pageNum", 1);
            getDeviceData(map);
        });

//      单列表
        RecyclerView recyclerView4 = new RecyclerView(new ContextThemeWrapper(getContext(), R.style.ScrollbarRecyclerView));
        BaseFilterAdapter adapter4 = new BaseFilterAdapter(R.layout.base_filter_right_item);
        recyclerView4.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView4.setAdapter(adapter4);
        recyclerView4.setScrollbarFadingEnabled(true);
        adapter4.setNewData(filters);
        adapter4.setOnItemClickListener((adapter, view, position) -> {
            BaseSingleFilter baseSingleFilter = adapter4.getData().get(position);
            String name = baseSingleFilter.getName();
            if (position < 10) {
                map.put("status", position);
                dropDownMenu.setTabText(name);
            } else {
                dropDownMenu.setTabText(filterArray[2]);
                map.remove("status");
            }
            dropDownMenu.closeMenu();
            refreshLayout.resetNoMoreData();
            pageNum = 1;
            map.put("pageNum", 1);
            getDeviceData(map);
        });

        popupViews.add(view1);
        popupViews.add(view2);
        popupViews.add(recyclerView4);

        recyclerview5 = new RecyclerView(getContext());
        recyclerview5.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerview5.setBackgroundColor(Color.WHITE);
        recyclerview5.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerview5.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshLayout = new SmartRefreshLayout(getContext());
        refreshLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        refreshLayout.addView(recyclerview5);
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout1.finishRefresh();
            pageNum = 1;
            map.put("pageNum", 1);
            map.put("pageSize", 10);
            getDeviceData(map);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();
            pageNum++;
            map.put("pageNum", pageNum);
            map.put("pageSize", 10);
            refreshDeviceData(map);
        });

        adapter = new DeviceAdapter(R.layout.base_device02);
        recyclerview5.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            DeviceData2.RowsBean currentItemData = (DeviceData2.RowsBean) adapter.getData().get(position);
            int deviceId = currentItemData.getId();
            String sn = currentItemData.getSn();
            Intent intent = new Intent();
            intent.putExtra("deviceId", deviceId);
            intent.putExtra("sn", sn);
            intent.setClass(getContext(), DeviceDetailActivity2.class);
            startActivityForResult(intent, DEVICE_DETAIL);
        });
        dropDownMenu.setDropDownMenu(Arrays.asList(filterArray), popupViews, refreshLayout);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == DEVICE_DETAIL) {
                pageNum = 1;
                map.put("pageNum", 1);
                getDeviceData(map);
            }
        }
    }

    private void getDeviceData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().findDeviceData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<DeviceData2>(getContext()) {
                    @Override
                    public void onSuccess(DeviceData2 deviceData2) {
                        if (deviceData2 != null) {
                            rows = deviceData2.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                            } else {
                                adapter.setNewData(null);
                            }
                        }
                    }
                });
    }

    private void refreshDeviceData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().findDeviceData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<DeviceData2>(getContext()) {
                    @Override
                    public void onSuccess(DeviceData2 deviceData2) {
                        if (deviceData2 != null) {
                            rows = deviceData2.getRows();
                            if (rows != null) {
                                adapter.addData(rows);
                                if (rows.size() < 10) {
                                    refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    }
                });
    }

    private void createSection_deptClassify(List<DeptList> deptLists) {
        List<BaseSingleFilter> baseSingleFilterList = new ArrayList<>();
        for (DeptList deptList : deptLists) {
            String deptName = deptList.getDeptName();
            int id = deptList.getId();
            BaseSingleFilter baseSingleFilter1 = new BaseSingleFilter();
            baseSingleFilter1.setName(deptName);
            baseSingleFilter1.setId(id);
            baseSingleFilterList.add(baseSingleFilter1);
        }
        BaseSingleFilter baseSingleFilters2 = new BaseSingleFilter();
        baseSingleFilters2.setName("不限");
        baseSingleFilters2.setId(-1);
        baseSingleFilterList.add(baseSingleFilters2);
        adapter11.setNewData(baseSingleFilterList);
    }

    private Map<BaseSingleFilter, List<BaseSingleFilter>> deviceClassifiysMap = new HashMap<>();

    private void createSection_classify(List<DeviceClassifiy> deviceClassifiys) {
        List<BaseSingleFilter> keyList = new ArrayList<>();
        for (DeviceClassifiy deviceClassifiy : deviceClassifiys) {
            int id = deviceClassifiy.getId();
            String name = deviceClassifiy.getName();
            BaseSingleFilter filterKey = new BaseSingleFilter();
            filterKey.setName(name);
            filterKey.setId(id);
            List<DeviceClassifiy.CategorysBean> deviceClassifiyListBeanList = deviceClassifiy.getCategorys();
            List<BaseSingleFilter> filterValueList = new ArrayList<>();

            if (deviceClassifiyListBeanList != null && deviceClassifiyListBeanList.size() > 0) {
                filterKey.setExistRight(true);
                for (DeviceClassifiy.CategorysBean listBean : deviceClassifiyListBeanList) {
                    BaseSingleFilter filterValue = new BaseSingleFilter();
                    int id1 = listBean.getId();
                    String name1 = listBean.getName();
                    filterValue.setId(id1);
                    filterValue.setName(name1);
                    filterValueList.add(filterValue);
                }
                keyList.add(filterKey);
                deviceClassifiysMap.put(filterKey, filterValueList);
            } else {
                filterKey.setExistRight(false);
                keyList.add(filterKey);
                deviceClassifiysMap.put(filterKey, new ArrayList<>());
            }
        }
        BaseSingleFilter filterKey2 = new BaseSingleFilter();
        filterKey2.setId(-1);
        filterKey2.setName("不限");
        filterKey2.setExistRight(false);
        keyList.add(filterKey2);
        deviceClassifiysMap.put(filterKey2, new ArrayList<>());
        adapter21.setNewData(keyList);
    }


    private void initTopbar() {
        topbar.setTitle("设备");
        topbar.addRightImageButton(R.mipmap.search, R.id.search).setOnClickListener(v -> SearchActivity.start(getContext()));
    }
}
