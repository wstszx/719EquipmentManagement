package com.example.a719equipmentmanagement.view;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.a719equipmentmanagement.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUILangHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogBuilder;

public class CustomInputDialog extends QMUIDialogBuilder<CustomInputDialog> {
    protected String mPlaceholder;
    protected String mPlaceholder1;
    protected TransformationMethod mTransformationMethod;
    protected RelativeLayout mMainLayout;
    protected EditText mEditText;
    protected ImageView mRightImageView;
    private int mInputType = InputType.TYPE_CLASS_TEXT;
    private int mInputType1 = InputType.TYPE_TEXT_VARIATION_PASSWORD;
    private CharSequence mDefaultText = null;
    private EditText mEditText1;
    private ImageView mRightImageView1;
    private RelativeLayout mMainLayout1;
    private CharSequence mDefaultText1;

    public CustomInputDialog(Context context) {
        super(context);
    }

    /**
     * 设置第一个输入框的 placeholder
     */
    public CustomInputDialog setPlaceholder(String placeholder) {
        this.mPlaceholder = placeholder;
        return this;
    }
    /**
     * 设置第一个输入框的 placeholder
     */
    public CustomInputDialog setPlaceholder1(String placeholder) {
        this.mPlaceholder1 = placeholder;
        return this;
    }

    /**
     * 设置输入框的 placeholder
     */
    public CustomInputDialog setPlaceholder(int resId) {
        return setPlaceholder(getBaseContext().getResources().getString(resId));
    }

    public CustomInputDialog setDefaultText(CharSequence defaultText) {
        mDefaultText = defaultText;
        return this;
    }
    public CustomInputDialog setDefaultText1(CharSequence defaultText) {
        mDefaultText1 = defaultText;
        return this;
    }

    /**
     * 设置 EditText 的 transformationMethod
     */
    public CustomInputDialog setTransformationMethod(TransformationMethod transformationMethod) {
        mTransformationMethod = transformationMethod;
        return this;
    }

    /**
     * 设置 EditText 的 inputType
     */
    public CustomInputDialog setInputType(int inputType) {
        mInputType = inputType;
        return this;
    }
    public CustomInputDialog setInputType1(int inputType) {
        mInputType1 = inputType;
        return this;
    }

    @Override
    protected void onCreateContent(QMUIDialog dialog, ViewGroup parent, Context context) {
        mEditText = new EditText(context);
        QMUIDialog.MessageDialogBuilder.assignMessageTvWithAttr(mEditText, hasTitle(), R.attr.qmui_dialog_edit_content_style);
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEditText.setId(R.id.qmui_dialog_edit_input);

        if (!QMUILangHelper.isNullOrEmpty(mDefaultText)) {
            mEditText.setText(mDefaultText);
        }

        mRightImageView = new ImageView(context);
        mRightImageView.setId(R.id.qmui_dialog_edit_right_icon);
        mRightImageView.setVisibility(View.GONE);

        mMainLayout = new RelativeLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = mEditText.getPaddingTop();
        lp.leftMargin = mEditText.getPaddingLeft();
        lp.rightMargin = mEditText.getPaddingRight();
        lp.bottomMargin = mEditText.getPaddingBottom();
        mMainLayout.setBackgroundResource(R.drawable.qmui_edittext_bg_border_bottom);
        mMainLayout.setLayoutParams(lp);

        if (mTransformationMethod != null) {
            mEditText.setTransformationMethod(mTransformationMethod);
        } else {
            mEditText.setInputType(mInputType);
        }

        mEditText.setBackgroundResource(0);
        mEditText.setPadding(0, 0, 0, QMUIDisplayHelper.dpToPx(5));
        RelativeLayout.LayoutParams editLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editLp.addRule(RelativeLayout.LEFT_OF, mRightImageView.getId());
        editLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        if (mPlaceholder != null) {
            mEditText.setHint(mPlaceholder);
        }
//第二个
        mEditText1 = new EditText(context);
        QMUIDialog.MessageDialogBuilder.assignMessageTvWithAttr(mEditText1, hasTitle(), R.attr.qmui_dialog_edit_content_style);
        mEditText1.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEditText1.setId(R.id.qmui_dialog_edit_input);

        if (!QMUILangHelper.isNullOrEmpty(mDefaultText)) {
            mEditText1.setText(mDefaultText1);
        }

        mRightImageView1 = new ImageView(context);
        mRightImageView1.setId(R.id.qmui_dialog_edit_right_icon);
        mRightImageView1.setVisibility(View.GONE);

        mMainLayout1 = new RelativeLayout(context);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.topMargin = mEditText1.getPaddingTop();
        lp1.leftMargin = mEditText1.getPaddingLeft();
        lp1.rightMargin = mEditText1.getPaddingRight();
        lp1.bottomMargin = mEditText1.getPaddingBottom();
        mMainLayout1.setBackgroundResource(R.drawable.qmui_edittext_bg_border_bottom);
        mMainLayout1.setLayoutParams(lp1);

        if (mTransformationMethod != null) {
            mEditText1.setTransformationMethod(mTransformationMethod);
        } else {
            mEditText1.setInputType(mInputType1);
        }

        mEditText1.setBackgroundResource(0);
        mEditText1.setPadding(0, 0, 0, QMUIDisplayHelper.dpToPx(5));
        RelativeLayout.LayoutParams editLp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editLp1.addRule(RelativeLayout.LEFT_OF, mRightImageView1.getId());
        editLp1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        if (mPlaceholder != null) {
            mEditText1.setHint(mPlaceholder1);
        }
        mMainLayout.addView(mEditText, createEditTextLayoutParams());
        mMainLayout1.addView(mEditText1, createEditTextLayoutParams());
        mMainLayout.addView(mRightImageView, createRightIconLayoutParams());
        mMainLayout1.addView(mRightImageView1, createRightIconLayoutParams());

        parent.addView(mMainLayout);
        parent.addView(mMainLayout1);
    }

    protected RelativeLayout.LayoutParams createEditTextLayoutParams() {
        RelativeLayout.LayoutParams editLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editLp.addRule(RelativeLayout.LEFT_OF, mRightImageView.getId());
        editLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        return editLp;
    }

    protected RelativeLayout.LayoutParams createRightIconLayoutParams() {
        RelativeLayout.LayoutParams rightIconLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightIconLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        rightIconLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        rightIconLp.leftMargin = QMUIDisplayHelper.dpToPx(5);
        return rightIconLp;
    }

    @Override
    protected void onAfter(QMUIDialog dialog, LinearLayout parent, Context context) {
        super.onAfter(dialog, parent, context);
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            }
        });
        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEditText.requestFocus();
                inputMethodManager.showSoftInput(mEditText, 0);
            }
        }, 300);
    }

    /**
     * 注意该方法只在调用 {@link #create()} 或 {@link #create(int)} 或 {@link #show()} 生成 Dialog 之后
     * 才能返回对应的 EditText，在此之前将返回 null
     */

    public EditText getEditText() {
        return mEditText;
    }

    public EditText getEditText1() {
        return mEditText1;
    }

    public ImageView getRightImageView() {
        return mRightImageView;
    }
}
