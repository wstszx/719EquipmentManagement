package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.widget.EditText;

import androidx.navigation.Navigation;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        topbar.addRightTextButton(R.string.complete, R.id.complete).setOnClickListener(v -> {
            newInventoryTask();
        });
        topbar.addLeftBackImageButton().setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
    }

    private void newInventoryTask() {
        String name = edittext.getText().toString();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        RetrofitClient.getInstance().getService().newInventoryTask(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(getContext()) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response != null && response.getCode() == 0) {
                            getActivity().finish();
                            ScanActivity.start(getContext());
                        }
                    }
                });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_inventory_task;
    }

}
