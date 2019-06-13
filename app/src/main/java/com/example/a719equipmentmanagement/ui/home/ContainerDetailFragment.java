package com.example.a719equipmentmanagement.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.base.BaseFragment;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Container;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerDetailFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView1)
    TextView textView1;
    private int containerId;
    private int inventoryId;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            containerId = bundle.getInt("containerId");
            inventoryId = bundle.getInt("inventoryId");
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonArray.put(jsonObject);
                jsonObject.put("inventoryId", inventoryId);
                jsonObject.put("containerId", containerId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonArray.toString());
            Single<BaseResponse> baseResponseSingle = RetrofitClient.getInstance().getService().setInventoryContainer(requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

            Single<Container> containerSingle = RetrofitClient.getInstance().getService().queryContainer(containerId + "")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            Single.zip(baseResponseSingle, containerSingle, (response, container) -> {
                if (container != null) {
                    Container.DataBean data = container.getData();
                    if (data != null) {
                        setData(data);
                    }
                }
                if (response != null) {
                    ToastUtils.showShort("保存盘点货柜");
                }
                return new Object();
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSubscriber<Object>(getContext()) {

                    });
        }
    }

    private void setData(Container.DataBean data) {
        String name = data.getName();
        String createTime = data.getCreateTime();
        textView.setText(name);
        textView1.setText(createTime);
    }

    private void initTopbar() {
        topbar.setTitle("货柜详情");
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            getActivity().finish();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_detail;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                inventoryCancel(view);
                break;
            case R.id.tv_continue:
                Bundle bundle = new Bundle();
                bundle.putInt("inventoryId", inventoryId);
                NavHostFragment.findNavController(this).navigate(R.id.scanFragment, bundle);
                break;
        }
    }

    private void inventoryCancel(View view) {
        RetrofitClient.getInstance().getService().cancelInventoryTask(inventoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(getContext()) {
                    @Override
                    public void onSuccess(BaseResponse response) {
                        if (response.getCode() == 0) {
                            Bundle bundle = new Bundle();
                            bundle.putString("title", "盘点取消成功");
                            Navigation.findNavController(view).navigate(R.id.resultFragment, bundle);
                        }
                    }
                });
    }
}
