package com.example.a719equipmentmanagement.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.a719equipmentmanagement.R;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;

public class BaseItemEditActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.edittext)
    EditText edittext;
    private String text;

    @Override
    protected void init(Bundle savedInstanceState) {
        Intent intent = getIntent();
        text = intent.getStringExtra("text");
        initTopbar();
        initEditText();
    }

    private void initEditText() {
        edittext.setText(text);
        edittext.setSelection(text.length());
    }

    private void initTopbar() {
        topbar.setTitle("编辑");
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("text", edittext.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_edit;
    }

    public static void start(Context context, String text) {
        Intent starter = new Intent(context, BaseItemEditActivity.class);
        starter.putExtra("text", text);
        context.startActivity(starter);
    }

}
