package com.example.a719equipmentmanagement.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.entity.PersonThree;
import com.example.a719equipmentmanagement.entity.PersonTwo;
import com.example.a719equipmentmanagement.entity.User;

import java.util.List;

public class PersonManageAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
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

    public PersonManageAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        this.mContext = context;
        addItemType(LEVEL_ONE, R.layout.base_parent_item);
        addItemType(LEVEL_TWO, R.layout.base_parent_item);
        addItemType(LEVEL_THREE, R.layout.base_parent_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_ONE:
                PersonOne personOne = (PersonOne) item;
                User user = personOne.getUser();
                setLevelData(user, helper);
                helper.getView(R.id.constraint).setOnClickListener(v -> {
                    int pos = helper.getAdapterPosition();
                    if (personOne.isExpanded()) {
                        collapse(pos, true);
                    } else {
                        expand(pos, true);
                    }
                });
                break;
            case LEVEL_TWO:
                PersonTwo personTwo = (PersonTwo) item;
                User user1 = personTwo.getUser();
                setLevelData(user1, helper);
                helper.getView(R.id.constraint).setOnClickListener(v -> {
                    int pos = helper.getAdapterPosition();
                    if (personTwo.isExpanded()) {
                        collapse(pos, true);
                    } else {
                        expand(pos, true);
                    }
                });
                break;
            case LEVEL_THREE:
                PersonThree personThree = (PersonThree) item;
                User user2 = personThree.getUser();
                setLevelData(user2, helper);
                break;
        }
    }

    private void setLevelData(User user, BaseViewHolder helper) {
        String deptName = user.getDeptName();
        String leader = user.getLeader();
        String status = user.getStatus();
        switch (status) {
            case "0":
                helper.setText(R.id.tv_status, "正常");
                break;
            case "1":
                helper.setText(R.id.tv_status, "停用");
                break;
        }
        helper.setText(R.id.tv_parent, deptName)
                .setText(R.id.tv_leader, leader);
    }
}