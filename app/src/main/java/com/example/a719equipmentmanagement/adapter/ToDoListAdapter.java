package com.example.a719equipmentmanagement.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.entity.ToReturn;
import com.example.a719equipmentmanagement.entity.UserToDo;

public class ToDoListAdapter extends BaseQuickAdapter<UserToDo,BaseViewHolder> {
    public ToDoListAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, UserToDo item) {
//        helper.setText();
    }


}
