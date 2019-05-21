package com.example.a719equipmentmanagement.ui.device;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.a719equipmentmanagement.entity.DeviceData;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

import static java.lang.System.*;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.constrainlayout)
    ConstraintLayout constrainlayout;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    private String[] filterArray = {"科室分类", "设备分类", "状态"};
    private String[] options = {"选项1", "选项2", "选项3"};

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
        Single.zip(RetrofitClient.getInstance().getService().findDeviceData(),
                RetrofitClient.getInstance().getService().getTreeData(),
                new BiFunction<DeviceData, List<TreeData>, Object>() {
                    @Override
                    public Object apply(DeviceData deviceData, List<TreeData> treeData) throws Exception {
                        if (deviceData != null) {
                            List<DeviceData.RowsBean> rows = deviceData.getRows();
                            if (rows != null && rows.size() > 0) {
                                adapter.setNewData(rows);
                            }
                            rowCount = rows.size();
                        }
                        if (treeData != null && treeData.size() > 0) {
                            createSeaction(treeData);
                        }
                        return new Object();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Object>(SearchActivity.this) {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
    }

    private void initView() {
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
                adapter12.setNewData(deptTwos);
                recyclerView12.setVisibility(View.VISIBLE);
            }
        });
        adapter12.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapter13.setNewData(deptThrees);
                recyclerView13.setVisibility(View.VISIBLE);
            }
        });
        adapter13.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                dropDownMenu.setTabText(position == 0 ? filterArray[1] : options[position]);
                dropDownMenu.setTabText((position == -1) ? filterArray[1] : deptThrees.get(position).getName());
                dropDownMenu.closeMenu();
                recyclerView12.setVisibility(View.INVISIBLE);
                recyclerView13.setVisibility(View.INVISIBLE);
            }
        });


        View view = getLayoutInflater().inflate(R.layout.base_double_list, null);
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView1);
        RecyclerView recyclerView3 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        BaseFilterAdapter adapter2 = new BaseFilterAdapter(R.layout.base_filter_right_item);
        BaseFilterAdapter adapter3 = new BaseFilterAdapter(R.layout.base_filter_item);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);
        adapter2.setNewData(filters);
        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapter3.setNewData(filters);
                recyclerView3.setVisibility(View.VISIBLE);
            }
        });
        adapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapter3.setNewData(filters);
                dropDownMenu.setTabText(position == 0 ? filterArray[1] : options[position]);
                dropDownMenu.closeMenu();
                recyclerView3.setVisibility(View.INVISIBLE);
            }
        });


        RecyclerView recyclerView4 = new RecyclerView(this);
        BaseFilterAdapter adapter4 = new BaseFilterAdapter(R.layout.base_filter_item);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));
        recyclerView4.setAdapter(adapter4);
        adapter4.setNewData(filters);
        adapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dropDownMenu.setTabText(position == 0 ? filterArray[2] : options[position]);
                dropDownMenu.closeMenu();
            }
        });

        popupViews.add(view1);
        popupViews.add(view);
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

        dropDownMenu.setDropDownMenu(Arrays.asList(filterArray), popupViews, recyclerview5);
    }

    private void createSeaction(List<TreeData> treeData) {
        deptOnes = new ArrayList<>();
        deptTwos = new ArrayList<>();
        deptThrees = new ArrayList<>();
        for (TreeData treeData1 : treeData) {
            int id1 = treeData1.getId();
            String name1 = treeData1.getName();
            if (id1 == 100) {
                BaseSingleFilter filter1 = new BaseSingleFilter();
                filter1.setId(id1);
                filter1.setName(name1);
                deptOnes.add(filter1);
                for (TreeData treeData2 : treeData) {
                    int pId2 = treeData2.getPId();
                    if (id1 == pId2) {
                        String name2 = treeData2.getName();
                        int id2 = treeData2.getId();
                        BaseSingleFilter filter2 = new BaseSingleFilter();
                        filter2.setId(id2);
                        filter2.setName(name2);
                        deptTwos.add(filter2);
                        for (TreeData treeData3 : treeData) {
                            int pId3 = treeData3.getPId();
                            if (id2 == pId3) {
                                String name3 = treeData3.getName();
                                int id3 = treeData3.getId();
                                BaseSingleFilter filter3 = new BaseSingleFilter();
                                filter3.setId(id3);
                                filter3.setName(name3);
                                deptThrees.add(filter3);
                            }
                        }
                    }
                }
            }

        }
        adapter11.setNewData(deptOnes);

    }

    private void initTopbar() {
        topbar.setTitle("设备搜索");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
