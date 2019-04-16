package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.PeopleManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonManageActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.sticky_section_layout)
    QMUIStickySectionLayout stickySectionLayout;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        initTopbar();
        initStickySectionLayout();
    }

    private void initStickySectionLayout() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        stickySectionLayout.setLayoutManager(manager);
        PeopleManageAdapter adapter = new PeopleManageAdapter();
        stickySectionLayout.setAdapter(adapter, true);
        ArrayList<QMUISection<SectionHeader, SectionItem>> list = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            list.add(createSection("三室 " + i + "小组"));
        }
        adapter.setData(list);
        adapter.setCallback(new QMUIStickySectionAdapter.Callback<SectionHeader, SectionItem>() {
            @Override
            public void loadMore(QMUISection<SectionHeader, SectionItem> section, boolean loadMoreBefore) {

            }

            @Override
            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private QMUISection<SectionHeader, SectionItem> createSection(String headerText) {
        SectionHeader header = new SectionHeader(headerText);
        ArrayList<SectionItem> contents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            contents.add(new SectionItem("item " + i));
        }
        QMUISection<SectionHeader, SectionItem> section = new QMUISection<>(header, contents, true);
        // if test load more, you can open the code
//        section.setExistAfterDataToLoad(true);
//        section.setExistBeforeDataToLoad(true);
        return section;
    }

    private void initTopbar() {
        topbar.setTitle("人员管理");
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_manage;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PersonManageActivity.class);
        context.startActivity(starter);
    }
}
