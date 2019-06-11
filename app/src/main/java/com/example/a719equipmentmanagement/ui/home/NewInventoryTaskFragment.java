package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.navigation.Navigation;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 新建盘点任务
 */
public class NewInventoryTaskFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.edittext)
    EditText edittext;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
    }

    private void initTopbar() {
        topbar.setTitle("新建盘点任务");
        topbar.addRightTextButton(R.string.continues, R.id.continues).setOnClickListener(this::newInventoryTask);
        topbar.addLeftBackImageButton().setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
    }

    private void newInventoryTask(View v) {
        String name = edittext.getText().toString();
        if (StringUtils.isEmpty(name)) {
            ToastUtils.showShort("盘点任务名称不能为空");
            return;
        }
//        HashMap<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        map.put("name", name);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitClient.getInstance().getService().newInventoryTask(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(getContext()) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response != null && response.getCode() == 0) {
                            getActivity().finish();
                            Navigation.findNavController(v).navigate(R.id.scanFragment);
                        }
                    }
                });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_inventory_task;
    }

}
