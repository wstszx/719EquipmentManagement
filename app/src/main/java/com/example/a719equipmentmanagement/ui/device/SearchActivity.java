package com.example.a719equipmentmanagement.ui.device;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.tv_dept_sort)
    TextView tvDeptSort;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    private QMUIListPopup mListPopup;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();

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


    @OnClick({R.id.tv_dept_sort, R.id.tv_sort, R.id.tv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dept_sort:
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_BOTTOM);

                mListPopup.show(view);
                break;
            case R.id.tv_sort:
                break;
            case R.id.tv_status:
                break;
        }
    }

    private void initListPopupIfNeed() {
        if (mListPopup == null) {

            String[] listItems = new String[]{
                    "Item 1",
                    "Item 2",
                    "Item 3",
                    "Item 4",
                    "Item 5",
            };
            List<String> data = new ArrayList<>();

            Collections.addAll(data, listItems);

            ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);

            mListPopup = new QMUIListPopup(this, QMUIPopup.DIRECTION_NONE, adapter);
//            mListPopup.create(QMUIDisplayHelper.dp2px(this, 250), QMUIDisplayHelper.dp2px(this, 200), new AdapterView.OnItemClickListener() {
            mListPopup.create(QMUIDisplayHelper.getScreenWidth(this), QMUIDisplayHelper.dp2px(this, 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(SearchActivity.this, "Item " + (i + 1), Toast.LENGTH_SHORT).show();
                    mListPopup.dismiss();
                }
            });
        }
    }
}
