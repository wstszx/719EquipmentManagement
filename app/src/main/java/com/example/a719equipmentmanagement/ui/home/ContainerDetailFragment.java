package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Container;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerDetailFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.edittext1)
    EditText edittext1;
    private String id;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id");

        }
    }

    private void initTopbar() {
        topbar.setTitle("货柜详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {

        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_detail;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_complete, R.id.tv_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                inventoryCancel();
                break;
            case R.id.tv_complete:
                inventoryComplete();
                break;
            case R.id.tv_continue:
//                inventoryContinue();
                break;
        }
    }

//    private void inventoryContinue() {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("inventoryId", id);
//            jsonObject.put("containerId", );
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
//        RetrofitClient.getInstance().getService().queryContainer(id)
//                .compose(CommonCompose.io2main(getContext()))
//                .subscribe(new BaseSubscriber<Container>(getContext()) {
//                    @Override
//                    public void onSuccess(Container container) {
//
//                    }
//                });
//        RetrofitClient.getInstance().getService().setInventoryContainer(requestBody)
//                .compose(CommonCompose.io2main(getContext()))
//                .subscribe(new BaseSubscriber<BaseResponse>(getContext()){
//                    @Override
//                    public void onSuccess(BaseResponse response) {
//
//                    }
//                });
//    }

    private void inventoryComplete() {
        
    }

    private void inventoryCancel() {
        
    }
}
