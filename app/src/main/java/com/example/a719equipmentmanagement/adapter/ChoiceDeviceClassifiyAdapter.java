package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.DeptOne;
import com.example.a719equipmentmanagement.entity.DeviceTypeOne;
import com.example.a719equipmentmanagement.entity.DeviceTypeTwo;

import java.util.List;

public class ChoiceDeviceClassifiyAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final int LEVEL_ONE = 0;
    private static final int LEVEL_TWO = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChoiceDeviceClassifiyAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
        addItemType(LEVEL_TWO, R.layout.base_two_level_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_ONE:
                DeviceTypeOne typeOne = (DeviceTypeOne) item;
                String name = typeOne.getDept().getName();
                helper.setText(R.id.tv_parent, name);
                break;
            case LEVEL_TWO:
                DeviceTypeTwo typeTwo = (DeviceTypeTwo) item;
                String name1 = typeTwo.getDept().getName();
                helper.setText(R.id.tv_parent, name1);
                break;
        }
    }
}
