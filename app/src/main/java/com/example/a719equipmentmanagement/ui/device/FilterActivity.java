package com.example.a719equipmentmanagement.ui.device;

import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ThreadUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.BaseFilterAdapter;
import com.example.a719equipmentmanagement.adapter.ContentFilterAdapter;
import com.example.a719equipmentmanagement.adapter.DeptManageAdapter;
import com.example.a719equipmentmanagement.adapter.DeviceAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseSingleFilter;
import com.example.a719equipmentmanagement.entity.DeptData;
import com.example.a719equipmentmanagement.entity.DeptOne;
import com.example.a719equipmentmanagement.entity.DeptThree;
import com.example.a719equipmentmanagement.entity.DeptTwo;
import com.example.a719equipmentmanagement.entity.Device;
import com.example.a719equipmentmanagement.entity.DeviceClassifiy;
import com.example.a719equipmentmanagement.entity.DeviceData;
import com.example.a719equipmentmanagement.entity.DeviceData2;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.entity.TreeData;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.home.AddPersonActivity;
import com.example.a719equipmentmanagement.view.DropDownMenu;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.section.QMUISection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
//import static java.lang.System.*;

public class FilterActivity extends BaseActivity {


    @BindView(R.id.constrainlayout)
    ConstraintLayout constrainlayout;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    private String[] filterArray = {"科室分类", "设备分类", "状态"};
    private String[] options = {"可用", "借用", "送检占用", "送检", "报废占用", "报废", "封存", "解封占用", "过期", "外借", "不限"};

    //    private List<Integer> deptOption=new ArrayList();//科室类别
    private List<BaseSingleFilter> deptId = new ArrayList<>();
    private List categoryOption = new ArrayList();//设备类别
    private List statusOption = new ArrayList();//状态类别

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    private List<View> popupViews = new ArrayList<>();
    private List<BaseSingleFilter> filters = new ArrayList<>();

    private DeviceAdapter adapter;
    private int rowCount;
    private DeptManageAdapter adapter1;
    List<MultiItemEntity> list = new ArrayList<>();
    private QMUIListPopup mListPopup;
    private List<BaseSingleFilter> deptOnes;
    private List<BaseSingleFilter> deptTwos;
    private List<BaseSingleFilter> deptThrees;
    private BaseFilterAdapter adapter11;
    private BaseFilterAdapter adapter12;
    private BaseFilterAdapter adapter13;
    private List<BaseSingleFilter> deptOnes_classify;
    private List<BaseSingleFilter> deptTwos_classify;
    private BaseFilterAdapter adapter21;
    private BaseFilterAdapter adapter22;
    private Map<String, Object> map = new HashMap<>();
    private List<DeviceData2.RowsBean> rows;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initView();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
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
        Single<DeviceData2> deviceData2Single = RetrofitClient.getInstance().getService().findDeviceData(new HashMap<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single<List<TreeData>> treeData1 = RetrofitClient.getInstance().getService().getTreeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single<List<DeviceClassifiy>> listSingle = RetrofitClient.getInstance().getService().findDeviceTypeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Single.zip(deviceData2Single, treeData1, listSingle,
                (deviceData2, treeData, deviceClassifiys) -> {
                    boolean mainThread = ThreadUtils.isMainThread();
                    if (mainThread) {
                        if (deviceData2 != null) {
                            rows = deviceData2.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                            }
                        }
                        if (treeData != null && treeData.size() > 0) {
//                            createSeaction(treeData);
                            createSection_deptClassify(treeData);
                        }
                        if (deviceClassifiys != null && deviceClassifiys.size() > 0) {
                            createSection_classify(deviceClassifiys);
                        }
                    }
                    return new Object();
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Object>(this) {
                    @Override
                    public void onSuccess(Object o) {
                        super.onSuccess(o);
                    }
                });
    }

    private void initView() {
//        三列表
        View view1 = getLayoutInflater().inflate(R.layout.base_triple_list, null);
        RecyclerView recyclerView11 = view1.findViewById(R.id.recyclerView1);
        RecyclerView recyclerView12 = view1.findViewById(R.id.recyclerView2);
        RecyclerView recyclerView13 = view1.findViewById(R.id.recyclerView3);
        recyclerView11.setLayoutManager(new LinearLayoutManager(this));
        recyclerView12.setLayoutManager(new LinearLayoutManager(this));
        recyclerView13.setLayoutManager(new LinearLayoutManager(this));
        adapter11 = new BaseFilterAdapter(R.layout.base_filter_right_item);
        adapter12 = new BaseFilterAdapter(R.layout.base_filter_right_item);
        adapter13 = new BaseFilterAdapter(R.layout.base_filter_item);
        recyclerView11.setAdapter(adapter11);
        recyclerView12.setAdapter(adapter12);
        recyclerView13.setAdapter(adapter13);
        adapter11.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == adapter11.getData().size() - 1) {
                    map.remove("deptIds");
                    dropDownMenu.setTabText(filterArray[0]);
                    dropDownMenu.closeMenu();
                    getDeviceData(map);
                } else {
                    recyclerView12.setVisibility(View.VISIBLE);
                    BaseSingleFilter baseSingleFilter1 = adapter11.getData().get(position);
                    List<BaseSingleFilter> baseSingleFilters1 = deptMap1_2.get(baseSingleFilter1);
                    adapter12.setNewData(baseSingleFilters1);
                    Set<BaseSingleFilter> baseSingleFiltersSet2 = deptMap2_3.keySet();
                    ArrayList<BaseSingleFilter> baseSingleFilters2 = new ArrayList<>(baseSingleFiltersSet2);
                    adapter12.setNewData(baseSingleFilters2);
                }
            }
        });
        adapter12.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                adapter13.setNewData(deptThrees);
                recyclerView13.setVisibility(View.VISIBLE);
                BaseSingleFilter baseSingleFilter2 = adapter12.getData().get(position);
                List<BaseSingleFilter> baseSingleFilters2 = deptMap2_3.get(baseSingleFilter2);
                adapter13.setNewData(baseSingleFilters2);
            }
        });
        adapter13.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseSingleFilter baseSingleFilter3 = adapter13.getData().get(position);
                String name = baseSingleFilter3.getName();
                int id = baseSingleFilter3.getId();
                dropDownMenu.setTabText(name);
                dropDownMenu.closeMenu();
                recyclerView12.setVisibility(View.INVISIBLE);
                recyclerView13.setVisibility(View.INVISIBLE);
                map.put("deptIds", id);
                getDeviceData(map);
            }
        });

//        双列表
        View view2 = getLayoutInflater().inflate(R.layout.base_double_list, null);
        RecyclerView recyclerView21 = view2.findViewById(R.id.recyclerView1);
        RecyclerView recyclerView22 = view2.findViewById(R.id.recyclerView2);
        recyclerView21.setLayoutManager(new LinearLayoutManager(this));
        recyclerView22.setLayoutManager(new LinearLayoutManager(this));
        adapter21 = new BaseFilterAdapter(R.layout.base_filter_right_item);
        adapter22 = new BaseFilterAdapter(R.layout.base_filter_item);
        recyclerView21.setAdapter(adapter21);
        recyclerView22.setAdapter(adapter22);

        recyclerView22.setVisibility(View.INVISIBLE);
        adapter21.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == adapter21.getData().size() - 1) {
                    map.remove("categoryIds");
                    dropDownMenu.setTabText(filterArray[1]);
                    dropDownMenu.closeMenu();
                    getDeviceData(map);
                } else {
                    recyclerView22.setVisibility(View.VISIBLE);
                    BaseSingleFilter baseSingleFilter = adapter21.getData().get(position);
                    List<BaseSingleFilter> baseSingleFilters = deviceClassifiysMap.get(baseSingleFilter);
                    adapter22.setNewData(baseSingleFilters);
                }
            }
        });
        adapter22.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                adapter22.setNewData(filters);
                BaseSingleFilter baseSingleFilter = adapter22.getData().get(position);
                String name = baseSingleFilter.getName();
                int id = baseSingleFilter.getId();
                dropDownMenu.setTabText(name);
                dropDownMenu.closeMenu();
                recyclerView22.setVisibility(View.INVISIBLE);
                map.put("categoryIds", id);
                getDeviceData(map);
            }
        });

//      单列表
        RecyclerView recyclerView4 = new RecyclerView(new ContextThemeWrapper(FilterActivity.this, R.style.ScrollbarRecyclerView));
        BaseFilterAdapter adapter4 = new BaseFilterAdapter(R.layout.base_filter_item);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));
        recyclerView4.setAdapter(adapter4);
        recyclerView4.setScrollbarFadingEnabled(true);
        adapter4.setNewData(filters);
        adapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
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
                getDeviceData(map);
            }
        });

        popupViews.add(view1);
        popupViews.add(view2);
        popupViews.add(recyclerView4);

        RecyclerView recyclerview5 = new RecyclerView(this);
        recyclerview5.setBackgroundColor(Color.WHITE);
        recyclerview5.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerview5.setLayoutManager(new LinearLayoutManager(this));

//        ContentFilterAdapter adapter5 = new ContentFilterAdapter(R.layout.base_device02);
//        recyclerview5.setAdapter(adapter5);
//        adapter5.setNewData(devices);
        adapter = new DeviceAdapter(R.layout.base_device02);
        recyclerview5.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DeviceData2.RowsBean currentItemData=rows.get(position);
                DeviceDetailActivity.start(FilterActivity.this, currentItemData);
            }
        });


        dropDownMenu.setDropDownMenu(Arrays.asList(filterArray), popupViews, recyclerview5);
    }

    private void getDeviceData(Map<String, Object> map) {
        RetrofitClient.getInstance().getService().findDeviceData(map)
                .compose(CommonCompose.io2main(FilterActivity.this))
                .subscribe(new BaseSubscriber<DeviceData2>(FilterActivity.this) {
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

//    private void createSeaction(List<TreeData> treeData) {
//        deptOnes = new ArrayList<>();
//        deptTwos = new ArrayList<>();
//        deptThrees = new ArrayList<>();
//        for (TreeData treeData1 : treeData) {
//            int id1 = treeData1.getId();
//            String name1 = treeData1.getName();
//            if (id1 == 100) {
//                BaseSingleFilter filter1 = new BaseSingleFilter();
//                filter1.setId(id1);
//                filter1.setName(name1);
//                deptOnes.add(filter1);
//                for (TreeData treeData2 : treeData) {
//                    int pId2 = treeData2.getPId();
//                    if (id1 == pId2) {
//                        String name2 = treeData2.getName();
//                        int id2 = treeData2.getId();
//                        BaseSingleFilter filter2 = new BaseSingleFilter();
//                        filter2.setId(id2);
//                        filter2.setName(name2);
//                        deptTwos.add(filter2);
//                        for (TreeData treeData3 : treeData) {
//                            int pId3 = treeData3.getPId();
//                            if (id2 == pId3) {
//                                String name3 = treeData3.getName();
//                                int id3 = treeData3.getId();
//                                BaseSingleFilter filter3 = new BaseSingleFilter();
//                                filter3.setId(id3);
//                                filter3.setName(name3);
//                                deptThrees.add(filter3);
//                            }
//                        }
//                    }
//                }
//            }
//            adapter11.setNewData(deptOnes);
//        }
//    }

    private Map<BaseSingleFilter, List<BaseSingleFilter>> deptMap1_2 = new HashMap<>();
    private Map<BaseSingleFilter, List<BaseSingleFilter>> deptMap2_3 = new HashMap<>();

    private void createSection_deptClassify(List<TreeData> treeData) {
        for (TreeData treeData1 : treeData) {
            int id1 = treeData1.getId();
            String name1 = treeData1.getName();

            if (id1 == 100) {
                BaseSingleFilter filterKey1 = new BaseSingleFilter();
                filterKey1.setId(id1);
                filterKey1.setName(name1);
                List<BaseSingleFilter> filterValueList1 = new ArrayList<>();
                for (TreeData treeData2 : treeData) {
                    int pId2 = treeData2.getPId();
                    if (id1 == pId2) {
                        BaseSingleFilter filterValue1 = new BaseSingleFilter();
                        int id2 = treeData2.getId();
                        String name2 = treeData2.getName();
                        filterValue1.setId(id2);
                        filterValue1.setName(name2);
                        filterValueList1.add(filterValue1);
                        BaseSingleFilter filterKey2 = new BaseSingleFilter();
                        filterKey2.setId(id2);
                        filterKey2.setName(name2);
                        List<BaseSingleFilter> filterValueList2 = new ArrayList<>();
                        for (TreeData treeData3 : treeData) {
                            int pId3 = treeData3.getPId();
                            if (pId3 == id2) {
                                BaseSingleFilter filterValue2 = new BaseSingleFilter();
                                int id3 = treeData3.getId();
                                String name3 = treeData3.getName();
                                filterValue2.setId(id3);
                                filterValue2.setName(name3);
                                filterValueList2.add(filterValue2);
                            }
                        }
                        deptMap2_3.put(filterKey2, filterValueList2);
                    }
                }

                deptMap1_2.put(filterKey1, filterValueList1);
            }
        }
        BaseSingleFilter filterKey2 = new BaseSingleFilter();
        filterKey2.setName("不限");
        filterKey2.setId(-1);
        deptMap1_2.put(filterKey2, new ArrayList<>());
        Set<BaseSingleFilter> baseSingleFiltersSet1 = deptMap1_2.keySet();
        ArrayList<BaseSingleFilter> baseSingleFilters1 = new ArrayList<>(baseSingleFiltersSet1);
        Collections.reverse(baseSingleFilters1);
        adapter11.setNewData(baseSingleFilters1);
    }

    private Map<BaseSingleFilter, List<BaseSingleFilter>> deviceClassifiysMap = new HashMap<>();

    private void createSection_classify(List<DeviceClassifiy> deviceClassifiys) {

        for (DeviceClassifiy deviceClassifiy : deviceClassifiys) {
            int id = deviceClassifiy.getId();
            String name = deviceClassifiy.getName();
            BaseSingleFilter filterKey = new BaseSingleFilter();
            filterKey.setName(name);
            filterKey.setId(id);
            List<DeviceClassifiy.ListBean> deviceClassifiyListBeanList = deviceClassifiy.getList();
            List<BaseSingleFilter> filterValueList = new ArrayList<>();
            for (DeviceClassifiy.ListBean listBean : deviceClassifiyListBeanList) {
                BaseSingleFilter filterValue = new BaseSingleFilter();
                int id1 = listBean.getId();
                String name1 = listBean.getName();
                filterValue.setId(id1);
                filterValue.setName(name1);
                filterValueList.add(filterValue);
            }
            deviceClassifiysMap.put(filterKey, filterValueList);
        }
        BaseSingleFilter filterKey2 = new BaseSingleFilter();
        filterKey2.setId(-1);
        filterKey2.setName("不限");
        deviceClassifiysMap.put(filterKey2, new ArrayList<>());
        Set<BaseSingleFilter> baseSingleFiltersSet = deviceClassifiysMap.keySet();
        ArrayList<BaseSingleFilter> baseSingleFilters1 = new ArrayList<>(baseSingleFiltersSet);
        Collections.reverse(baseSingleFilters1);
        adapter21.setNewData(baseSingleFilters1);
    }


    private void initTopbar() {
        topbar.setTitle("设备筛选");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
