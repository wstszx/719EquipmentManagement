package com.example.a719equipmentmanagement.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.DeptList;
import com.example.a719equipmentmanagement.entity.DeptOne;
import com.example.a719equipmentmanagement.entity.DeptThree;
import com.example.a719equipmentmanagement.entity.DeptTwo;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.entity.PersonThree;
import com.example.a719equipmentmanagement.entity.PersonTwo;

import java.util.List;

public class DeptManageAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private Context mContext;
    public static final int LEVEL_ONE = 0;
    public static final int LEVEL_TWO = 1;
    public static final int LEVEL_THREE = 2;
    public static final int LEVEL_FOUR = 3;

    public DeptManageAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        this.mContext = context;
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
        addItemType(LEVEL_TWO, R.layout.base_two_level_item);
        addItemType(LEVEL_THREE, R.layout.base_three_level_item);
        addItemType(LEVEL_FOUR, R.layout.base_two_level_item);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_ONE:
                DeptOne deptOne = (DeptOne) item;
                DeptList deptList = deptOne.getDept();
                List<DeptList.UsersBean> users = deptList.getUsers();
                setLevelData(deptList, helper);
                if (deptOne.isExpanded()) {
                    helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
                } else {
                    helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
                }
//                helper.getView(R.id.constraint).setOnClickListener(v -> {
//                    int pos = helper.getAdapterPosition();
//                    if (deptOne.isExpanded()) {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
//                        collapse(pos, true);
//                    } else {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
//                        expand(pos, true);
//                    }
//                });
                break;
            case LEVEL_TWO:
                DeptTwo personTwo = (DeptTwo) item;
                DeptList dept = personTwo.getDept();
                setLevel1Data(dept, helper);
                if (personTwo.isExpanded()) {
                    helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
                } else {
                    helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
                }
//                helper.getView(R.id.constraint).setOnClickListener(v -> {
//                    int pos = helper.getAdapterPosition();
//                    if (personTwo.isExpanded()) {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
//                        collapse(pos, true);
//                    } else {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
//                        expand(pos, true);
//                    }
//                });
                break;
            case LEVEL_THREE:
                DeptThree deptThree = (DeptThree) item;
                DeptList.UsersBean usersBean = deptThree.getusersBean();
                setLevel2Data(usersBean, helper);
                break;
            case LEVEL_FOUR:
                DeptTwo deptTwo = (DeptTwo) item;
                DeptList.UsersBean usersBean1 = deptTwo.getUsersBean();
                setLevel3Data(usersBean1, helper);
                break;
        }
    }

    private void setLevelData(DeptList deptList, BaseViewHolder helper) {
        String deptName = deptList.getDeptName();
        helper.setText(R.id.tv_parent, deptName);
    }

    private void setLevel1Data(DeptList deptList, BaseViewHolder helper) {
        String deptName = deptList.getDeptName();
//        List<DeptList.UsersBean> users = deptList.getUsers();
//        if (users != null && users.size() > 0) {
//            for (DeptList.UsersBean user : users) {
//                String userName = user.getUserName();
//                helper.setText(R.id.tv_parent, userName);
//            }
//        }
        helper.setText(R.id.tv_parent, deptName);
    }

    private void setLevel2Data(DeptList.UsersBean usersBean, BaseViewHolder helper) {
        String userName = usersBean.getUserName();
        helper.setText(R.id.tv_parent, userName);
    }

    private void setLevel3Data(DeptList.UsersBean usersBean, BaseViewHolder helper) {
        String userName = usersBean.getUserName();
        helper.setText(R.id.tv_parent, userName);
    }
}
