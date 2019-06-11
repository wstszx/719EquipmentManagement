package com.example.a719equipmentmanagement.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.entity.PersonThree;
import com.example.a719equipmentmanagement.entity.PersonTwo;
import com.example.a719equipmentmanagement.entity.User;

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
//    public static final int LEVEL_THREE = 2;

    public DeptManageAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        this.mContext = context;
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
        addItemType(LEVEL_TWO, R.layout.base_two_level_item);
//        addItemType(LEVEL_THREE, R.layout.base_three_level_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_ONE:
                PersonOne personOne = (PersonOne) item;
                User user = personOne.getUser();
                setLevelData(user, helper);
                if (personOne.isExpanded()) {
                    helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
                } else {
                    helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
                }
//                helper.getView(R.id.constraint).setOnClickListener(v -> {
//                    int pos = helper.getAdapterPosition();
//                    if (personOne.isExpanded()) {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
//                        collapse(pos, true);
//                    } else {
//                        helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
//                        expand(pos, true);
//                    }
//                });
                break;
            case LEVEL_TWO:
                PersonTwo personTwo = (PersonTwo) item;
                User.UsersBean user1 = personTwo.getUser();
                setLevel1Data(user1, helper);
//                if (personTwo.isExpanded()) {
//                    helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
//                } else {
//                    helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
//                }
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
//            case LEVEL_THREE:
//                PersonThree personThree = (PersonThree) item;
//                User user2 = personThree.getUser();
//                setLevelData(user2, helper);
//                break;
        }
    }

    private void setLevelData(User user, BaseViewHolder helper) {
        String deptName = user.getDeptName();
        String leader = user.getLeader();
//        String status = user.getStatus();
//        switch (status) {
//            case "0":
//                helper.setText(R.id.tv_status, "正常");
//                break;
//            case "1":
//                helper.setText(R.id.tv_status, "停用");
//                break;
//        }
        helper.setText(R.id.tv_parent, deptName);
    }

    private void setLevel1Data(User.UsersBean user, BaseViewHolder helper) {
        String userName = user.getUserName();
//        String status = user.getStatus();
//        switch (status) {
//            case "0":
//                helper.setText(R.id.tv_status, "正常");
//                break;
//            case "1":
//                helper.setText(R.id.tv_status, "停用");
//                break;
//        }
        helper.setText(R.id.tv_parent, userName);

    }
}
