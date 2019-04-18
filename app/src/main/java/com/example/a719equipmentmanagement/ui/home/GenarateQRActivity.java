package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class GenarateQRActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.topbar)
    QMUITopBar topbar;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        createChineseQRCode();
    }

    private void initTopbar() {
        topbar.setTitle("二维码");
        topbar.addRightTextButton(R.string.print, R.id.print).setOnClickListener(v -> {

        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_genarate_qr;
    }

    private void createChineseQRCode() {
        new MyTask(GenarateQRActivity.this, imageView).execute();

    }

    static class MyTask extends AsyncTask<Void, Void, Bitmap> {
        // 弱引用允许Activity被垃圾收集器清理
        private final WeakReference<GenarateQRActivity> weakActivity;
        private final WeakReference<ImageView> weakimageview;

        MyTask(GenarateQRActivity myActivity, ImageView imageView) {
            this.weakActivity = new WeakReference<>(myActivity);
            this.weakimageview = new WeakReference<>(imageView);
        }

        @Override
        public Bitmap doInBackground(Void... params) {
            return QRCodeEncoder.syncEncodeQRCode("生成的", BGAQRCodeUtil.dp2px(weakActivity.get().getApplicationContext(), 150));
        }

        @Override
        public void onPostExecute(Bitmap bitmap) {
            // 重新获取Actiity的强引用，并且判断是否存活
            GenarateQRActivity activity = weakActivity.get();
            if (activity == null
                    || activity.isFinishing()
                    || activity.isDestroyed()) {
                // activity死亡了，不再做任何的事情
                return;
            }
            if (bitmap != null) {
                weakimageview.get().setImageBitmap(bitmap);
            } else {
                Toast.makeText(weakActivity.get().getApplicationContext(), "生成二维码失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, GenarateQRActivity.class);
        context.startActivity(starter);
    }

}
