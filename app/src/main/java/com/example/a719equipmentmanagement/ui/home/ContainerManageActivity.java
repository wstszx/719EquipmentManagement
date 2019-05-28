package com.example.a719equipmentmanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.a719equipmentmanagement.App;
import com.example.a719equipmentmanagement.R;
import com.example.a719equipmentmanagement.adapter.ContainerManageAdapter;
import com.example.a719equipmentmanagement.base.BaseActivity;
import com.example.a719equipmentmanagement.entity.BaseResponse;
import com.example.a719equipmentmanagement.entity.Container;
import com.example.a719equipmentmanagement.entity.ContainerData;
import com.example.a719equipmentmanagement.entity.SectionHeader;
import com.example.a719equipmentmanagement.entity.SectionItem;
import com.example.a719equipmentmanagement.net.BaseSubscriber;
import com.example.a719equipmentmanagement.net.CommonCompose;
import com.example.a719equipmentmanagement.net.NetworkError;
import com.example.a719equipmentmanagement.net.RetrofitClient;
import com.example.a719equipmentmanagement.ui.device.AddDeviceActivity;
import com.example.a719equipmentmanagement.view.CustomInputDialog;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.section.QMUISection;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 货柜管理
 */
public class ContainerManageActivity extends BaseActivity {

    private static final int EDIT_CONTAINER = 1;
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.sticky_section_layout)
    QMUIStickySectionLayout stickySectionLayout;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private ArrayAdapter<String> adapter;
    private QMUIListPopup mListPopup;
    //    String[] headerDeletes = new String[]{
//            "编辑",
//            "删除"
//    };
    String[] itemDeletes = new String[]{
            "编辑",
            "删除"
    };
    private ContainerManageAdapter adapter1;
    private int id;

    @Override
    protected void init(Bundle savedInstanceState) {
        initTopbar();
        initStickySectionLayout();
        initData();
    }

    private void initData() {
        RetrofitClient.getInstance().getService().findContainerData()
                .compose(CommonCompose.io2main(ContainerManageActivity.this))
                .subscribe(new BaseSubscriber<List<ContainerData>>(ContainerManageActivity.this) {
                    @Override
                    public void onSuccess(List<ContainerData> baseResponse) {
                        if (baseResponse != null && baseResponse.size() > 0) {
                            bindUi(baseResponse);
                        }
                    }
                });
    }

    private void bindUi(List<ContainerData> body) {
        ArrayList<QMUISection<SectionHeader<ContainerData>, SectionItem<ContainerData.ListBean>>> list = new ArrayList<>();
        for (ContainerData containerData : body) {
            list.add(createSection(containerData));
        }
        adapter1.setData(list);
    }

    private void initStickySectionLayout() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        stickySectionLayout.setBackgroundColor(getResources().getColor(R.color.qmui_config_color_white));
        stickySectionLayout.setLayoutManager(manager);
        adapter1 = new ContainerManageAdapter();
        stickySectionLayout.setAdapter(adapter1, true);
        adapter1.setCallback(new QMUIStickySectionAdapter.Callback<SectionHeader<ContainerData>, SectionItem<ContainerData.ListBean>>() {
            @Override
            public void loadMore(QMUISection<SectionHeader<ContainerData>, SectionItem<ContainerData.ListBean>> section, boolean loadMoreBefore) {

            }

            @Override
            public void onItemClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                int itemViewType = holder.getItemViewType();
                switch (itemViewType) {
                    case 0:
                        adapter1.toggleFold(position, false);
                        break;
                    case 1:
                        SectionItem<ContainerData.ListBean> sectionItem = adapter1.getSectionItem(position);
                        ContainerData.ListBean listBean = null;
                        if (sectionItem != null) {
                            listBean = sectionItem.getListBean();
                        }
                        if (listBean != null) {
                            ContainerDetailActivity.start(ContainerManageActivity.this, listBean);
                        }
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(QMUIStickySectionAdapter.ViewHolder holder, int position) {
                int itemViewType = holder.getItemViewType();
                if (itemViewType == 0) {
                    QMUISection<SectionHeader<ContainerData>, SectionItem<ContainerData.ListBean>> section = adapter1.getSection(position);
                    SectionHeader<ContainerData> header = null;
                    if (section != null) {
                        header = section.getHeader();
                    }
                    if (header != null) {
                        id = header.getText().getId();
                    }
                    initListPopupIfNeed(itemDeletes);
                    mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                    mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_NONE);
                    mListPopup.show(holder.itemView);
                }
                return true;
            }
        });
    }

    private QMUISection<SectionHeader<ContainerData>, SectionItem<ContainerData.ListBean>> createSection(ContainerData containerData) {
        ArrayList<SectionItem<ContainerData.ListBean>> contents = new ArrayList<>();
        SectionHeader<ContainerData> header = new SectionHeader<>(containerData);
        List<ContainerData.ListBean> listBeans = containerData.getList();
        if (listBeans != null && listBeans.size() > 0) {
            for (ContainerData.ListBean listBean : listBeans) {
                contents.add(new SectionItem<>(listBean));
            }
        }
        return new QMUISection<>(header, contents, true);
    }


    private void initTopbar() {
        topbar.setTitle("货柜管理");
        topbar.addRightImageButton(R.mipmap.add, R.id.add).setOnClickListener(v -> AddContainerActivity.start(this));
        topbar.addLeftImageButton(R.mipmap.back, R.id.back).setOnClickListener(v -> finish());
    }

    private void initListPopupIfNeed(String[] listItems) {

        List<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, data);
        } else {
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }
        if (mListPopup == null) {
            mListPopup = new QMUIListPopup(this, QMUIPopup.DIRECTION_NONE, adapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(this, 250), QMUIDisplayHelper.dp2px(this, 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mListPopup.dismiss();
                    TextView textView = (TextView) view;
                    String s = textView.getText().toString();
                    switch (s) {
                        case "编辑":
                            startActivityForResult(new Intent(ContainerManageActivity.this, EditContainerActivity.class), EDIT_CONTAINER);
                            break;
                        case "删除":
                            delete();
                            break;
                    }
                }
            });
            mListPopup.setOnDismissListener(data::clear);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            if (requestCode == EDIT_CONTAINER) {
                initData();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void delete() {
        RetrofitClient.getInstance().getService().deleteContainer(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse>(ContainerManageActivity.this) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse) {
                        initData();
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container_manage;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ContainerManageActivity.class);
        context.startActivity(starter);
    }

    /**
     * 弹出带输入框的dialog
     */
//    private void showEditTextDialog() {
//        CustomInputDialog customDialogBuilder = new CustomInputDialog(this);
//        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
//        customDialogBuilder.setTitle("添加货柜")
//                .setPlaceholder("请输入货柜名")
//                .setPlaceholder1("请输入层数")
//                .setInputType(InputType.TYPE_CLASS_TEXT)
//                .setInputType1(InputType.TYPE_CLASS_NUMBER)
//                .addAction("取消", (dialog, index) -> dialog.dismiss())
//                .addAction("确定", (dialog, index) -> {
//                    CharSequence text = customDialogBuilder.getEditText().getText();
//                    CharSequence text1 = customDialogBuilder.getEditText1().getText();
//                    dialog.dismiss();
////                    if (text1 != null && text1.length() > 0) {
////                        Toast.makeText(ContainerManageActivity.this, "成功" + "添加货柜" + ":" + text + text1, Toast.LENGTH_SHORT).show();
////                        dialog.dismiss();
////                    } else {
////                        Toast.makeText(ContainerManageActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
////                    }
//                })
//                .create(mCurrentDialogStyle).show();
//    }


}
