package com.example.a719equipmentmanagement.ui.device;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.BaseFilterAdapter;
import com.example.a719equipmentmanagement.adapter.ContentFilterAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseSingleFilter;
import com.example.a719equipmentmanagement.entity.Device;
import com.example.a719equipmentmanagement.view.DropDownMenu;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.constrainlayout)
    ConstraintLayout constrainlayout;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    private String[] filterArray = {"科室分类", "设备分类", "状态"};
    private String[] options = {"选项1", "选项2", "选项3"};
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    private List<View> popupViews = new ArrayList<>();
    private List<BaseSingleFilter> filters = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initView();
    }

    private void initData() {
        for (String option : options) {
            BaseSingleFilter filter = new BaseSingleFilter();
            filter.setName(option);
            filters.add(filter);
        }
    }

    private void initView() {
        RecyclerView recyclerView1 = new RecyclerView(this);
        BaseFilterAdapter adapter1 = new BaseFilterAdapter(R.layout.base_filter_item);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);
        adapter1.setNewData(filters);

        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dropDownMenu.setTabText(position == 0 ? filterArray[0] : options[position]);
                dropDownMenu.closeMenu();
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

        popupViews.add(recyclerView1);
        popupViews.add(view);
        popupViews.add(recyclerView4);

        List<Device> devices = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Device device = new Device();
            device.setDepartment("第三实验室");
            device.setName("压力计");
            device.setLocation("3货柜第" + i + "层");
            device.setStatus("可借");
            device.setUserName("张三");
            devices.add(device);
        }
        RecyclerView recyclerview5 = new RecyclerView(this);
        recyclerview5.setBackgroundColor(Color.WHITE);
        recyclerview5.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerview5.setLayoutManager(new LinearLayoutManager(this));
        ContentFilterAdapter adapter5 = new ContentFilterAdapter(R.layout.base_device02);
        recyclerview5.setAdapter(adapter5);
        adapter5.setNewData(devices);

        dropDownMenu.setDropDownMenu(Arrays.asList(filterArray), popupViews, recyclerview5);
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }


//    @OnClick({R.id.tv_dept_sort, R.id.tv_sort, R.id.tv_status})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_dept_sort:
////                initListPopupIfNeed();
////                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
////                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_BOTTOM);
////                mListPopup.show(view);
//                break;
//            case R.id.tv_sort:
//                dropDownMenu.setDropDownMenu(Arrays.asList(filterArray), popupViews, );
//                break;
//            case R.id.tv_status:
//                break;
//        }
//    }


//    private void initListPopupIfNeed() {
//        if (mListPopup == null) {
//
//            String[] listItems = new String[]{
//                    "Item 1",
//                    "Item 2",
//                    "Item 3",
//                    "Item 4",
//                    "Item 5",
//                    "Item 6"
//            };
//            List<String> data = new ArrayList<>();
//
//            Collections.addAll(data, listItems);
//
//            ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);
//
//            mListPopup = new QMUIListPopup(this, QMUIPopup.DIRECTION_BOTTOM, adapter);
////            mListPopup.create(QMUIDisplayHelper.dp2px(this, 250), QMUIDisplayHelper.dp2px(this, 200), new AdapterView.OnItemClickListener() {
//            int screenWidth = QMUIDisplayHelper.getScreenWidth(this);
//            mListPopup.create(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT, new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Toast.makeText(SearchActivity.this, "Item " + (i + 1), Toast.LENGTH_SHORT).show();
//                    mListPopup.dismiss();
//                }
//            });
//        }
//    }
}
