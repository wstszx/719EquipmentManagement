package com.example.a719equipmentmanagement.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.base.BaseItemEditActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.User;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditDeptActivity extends BaseActivity {

    private String[] containerAttrs = {"上级部门", "部门名称", "显示排序",
            "负责人", "联系电话", "邮箱", "部门状态"};
//    private String[] containerAttrValue = new String[3];
    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;
    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    private QMUICommonListItemView listItemView;
    private List<String> deptDetails;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
        initGroupListView();
    }

    private void initData() {
        deptDetails = new ArrayList<>();
        Intent intent = getIntent();
        String parentDept = intent.getStringExtra("parentDept");
        User data = (User) intent.getSerializableExtra("data");
        String deptName = data.getDeptName();
        String orderNum = data.getOrderNum();
        String leader = data.getLeader();
        String phone = data.getPhone();
        String email = data.getEmail();
        String status = data.getStatus();
        deptDetails.add(parentDept);
        deptDetails.add(deptName);
        deptDetails.add(orderNum);
        deptDetails.add(leader);
        deptDetails.add(phone);
        deptDetails.add(email);
        deptDetails.add(status);
    }

    private void initGroupListView() {
        View.OnClickListener onClickListener = v -> {
            listItemView = (QMUICommonListItemView) v;
            int tag = (int) listItemView.getTag();
            Intent intent = new Intent();
            intent.putExtra("text", listItemView.getDetailText().toString());
            intent.setClass(this, BaseItemEditActivity.class);
            startActivityForResult(intent, tag);
        };
        QMUIGroupListView.Section section = QMUIGroupListView.newSection(this);
        for (int i = 0; i < containerAttrs.length; i++) {
            QMUICommonListItemView item = groupListView.createItemView(
                    null,
                    containerAttrs[i],
                    deptDetails.get(i),
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
            item.setTag(i);
            section.addItemView(item, onClickListener);
        }
        section.addTo(groupListView);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_dept;
    }

    private void initTopbar() {
        topbar.setTitle("编辑人员");
        topbar.addRightTextButton(R.string.confirm, R.id.confirm).setOnClickListener(v -> {
            RetrofitClient.getInstance().getService().editUser()
                    .compose(CommonCompose.io2main(EditDeptActivity.this))
                    .subscribe(new BaseSubscriber<BaseResponse>(EditDeptActivity.this) {
                        @Override
                        public void onSuccess(BaseResponse baseResponse) {
                        }
                    });
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, EditDeptActivity.class);
        context.startActivity(starter);
    }
}
