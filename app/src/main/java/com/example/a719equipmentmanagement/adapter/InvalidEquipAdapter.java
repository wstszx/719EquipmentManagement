package com.example.a719equipmentmanagement.adapter;

        import com.chad.library.adapter.base.BaseQuickAdapter;
        import com.chad.library.adapter.base.BaseViewHolder;
        import com.example.a719equipmentmanagement.R;
        import com.example.a719equipmentmanagement.entity.InvalidEquip;

public class InvalidEquipAdapter extends BaseQuickAdapter<InvalidEquip, BaseViewHolder> {
    public InvalidEquipAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvalidEquip item) {
        helper.setText(R.id.deviceName,item.getDeviceName())
                .setText(R.id.invalidDate,item.getReturnDate()+"到期")
                .setText(R.id.number,item.getNumber()+"")
                .setText(R.id.days,"剩余"+item.getDays()+"天");
    }
}
