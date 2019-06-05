package com.example.a719equipmentmanagement.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.entity.ContainerItem;

import java.util.List;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class GenerateContainerCodeAdapter extends BaseQuickAdapter<ContainerItem, BaseViewHolder> {
    private Context context;

    public GenerateContainerCodeAdapter(int layoutResId, @Nullable List<ContainerItem> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ContainerItem item) {
        String name = item.getName();
        String id = item.getId();
        helper.setText(R.id.tv_name, name);
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode(id, BGAQRCodeUtil.dp2px(context, 150));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    ((ImageView) helper.getView(R.id.iv_qrcode)).setImageBitmap(bitmap);
                } else {
                    ToastUtils.showShort("生成中文二维码失败");
                }
            }
        }.execute();
    }
}
