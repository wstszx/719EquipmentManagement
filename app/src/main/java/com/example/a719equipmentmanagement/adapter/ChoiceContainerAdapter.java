package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ContainerOne;
import com.example.a719equipmentmanagement.entity.ContainerTwo;
import com.example.a719equipmentmanagement.entity.DeviceTypeOne;
import com.example.a719equipmentmanagement.entity.DeviceTypeTwo;

import java.util.List;

public class ChoiceContainerAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int LEVEL_ONE = 0;
    public static final int LEVEL_TWO = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ChoiceContainerAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
        addItemType(LEVEL_TWO, R.layout.base_two_level_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_ONE:
                ContainerOne containerOne = (ContainerOne) item;
                String name = containerOne.getDept().getName();
                helper.setText(R.id.tv_parent, name);
                break;
            case LEVEL_TWO:
                ContainerTwo containerTwo = (ContainerTwo) item;
                String name1 = containerTwo.getDept().getName();
                helper.setText(R.id.tv_parent, name1);
                break;
        }
    }
}
