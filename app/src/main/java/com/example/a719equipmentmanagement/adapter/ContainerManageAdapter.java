package com.example.a719equipmentmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.ContainerOne;
import com.example.a719equipmentmanagement.entity.ContainerTwo;
import com.example.a719equipmentmanagement.entity.PersonOne;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.qmuiteam.qmui.widget.section.QMUIDefaultStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUISection;

import androidx.annotation.NonNull;

import java.util.List;

public class ContainerManageAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public static final int LEVEL_ONE = 0;
    private static final int LEVEL_TWO = 1;
    public ContainerManageAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_ONE, R.layout.base_one_level_item);
        addItemType(LEVEL_TWO, R.layout.base_child_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_ONE:
                ContainerOne containerOne = (ContainerOne) item;
                ContainerData data = containerOne.getData();
                String name = data.getName();
                helper.setText(R.id.tv_one, name);
                if (containerOne.isExpanded()) {
                    helper.setImageResource(R.id.iv_right, R.mipmap.shangla);
                } else {
                    helper.setImageResource(R.id.iv_right, R.mipmap.xiala);
                }
                break;
            case LEVEL_TWO:
                ContainerTwo containerTwo = (ContainerTwo) item;
                ContainerData.ContainersBean containerTwoData = containerTwo.getData();
                String name1 = containerTwoData.getName();
                helper.setText(R.id.tv_1, name1);
                break;
        }
    }

}
