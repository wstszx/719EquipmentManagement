package com.example.a719equipmentmanagement.ui.device;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.BaseFilterAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseSingleFilter;
import com.example.a719equipmentmanagement.view.DropDownMenu;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.constrainlayout)
    ConstraintLayout constrainlayout;
    private String[] filterArray = {"科室分类", "设备分类", "状态"};
    private String[] options = {"选项1", "选项2", "选项3"};
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    //    @BindView(R.id.tv_dept_sort)
//    TextView tvDeptSort;
//    @BindView(R.id.tv_sort)
//    TextView tvSort;
//    @BindView(R.id.tv_status)
//    TextView tvStatus;
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    private List<View> popupViews = new ArrayList<>();
    private List<BaseSingleFilter> filters = new ArrayList<>();
//    private QMUIListPopup mListPopup;
//    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog_FullWidth;

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
            }
        });

        RecyclerView recyclerView2 = new RecyclerView(this);
        BaseFilterAdapter adapter2 = new BaseFilterAdapter(R.layout.base_filter_item);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(adapter2);
        adapter2.setNewData(filters);


        RecyclerView recyclerView3 = new RecyclerView(this);
        BaseFilterAdapter adapter3 = new BaseFilterAdapter(R.layout.base_filter_item);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3.setAdapter(adapter3);
        adapter3.setNewData(filters);

        popupViews.add(recyclerView1);
        popupViews.add(recyclerView2);
        popupViews.add(recyclerView3);


        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        dropDownMenu.setDropDownMenu(Arrays.asList(filterArray), popupViews, contentView);

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
