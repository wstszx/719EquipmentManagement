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

public class ReturnInspectionDialog extends QMUIDialogBuilder<ReturnInspectionDialog> {
    protected String mPlaceholder;
    protected String mPlaceholder1;
    protected String mPlaceholder2;
    protected TransformationMethod mTransformationMethod;
    protected RelativeLayout mMainLayout;
    protected EditText mEditText;
    protected ImageView mRightImageView;
    private int mInputType = InputType.TYPE_CLASS_TEXT;
    private int mInputType1 = InputType.TYPE_TEXT_VARIATION_PASSWORD;
    private int mInputType2 = InputType.TYPE_CLASS_TEXT;
    private CharSequence mDefaultText = null;
    private EditText mEditText1;
    private ImageView mRightImageView1;
    private RelativeLayout mMainLayout1;
    private CharSequence mDefaultText1;
    private CharSequence mDefaultText2;
    private EditText mEditText2;
    private ImageView mRightImageView2;
    private RelativeLayout mMainLayout2;

    public ReturnInspectionDialog(Context context) {
        super(context);
    }

    /**
     * 设置第一个输入框的 placeholder
     */
    public ReturnInspectionDialog setPlaceholder(String placeholder) {
        this.mPlaceholder = placeholder;
        return this;
    }

    /**
     * 设置第二个输入框的 placeholder
     */
    public ReturnInspectionDialog setPlaceholder1(String placeholder) {
        this.mPlaceholder1 = placeholder;
        return this;
    }

    /**
     * 设置第三个输入框的 placeholder
     */
    public ReturnInspectionDialog setPlaceholder2(String placeholder) {
        this.mPlaceholder2 = placeholder;
        return this;
    }

    /**
     * 设置输入框的 placeholder
     */
    public ReturnInspectionDialog setPlaceholder(int resId) {
        return setPlaceholder(getBaseContext().getResources().getString(resId));
    }

    public ReturnInspectionDialog setDefaultText(CharSequence defaultText) {
        mDefaultText = defaultText;
        return this;
    }

    public ReturnInspectionDialog setDefaultText1(CharSequence defaultText) {
        mDefaultText1 = defaultText;
        return this;
    }

    public ReturnInspectionDialog setDefaultText2(CharSequence defaultText) {
        mDefaultText2 = defaultText;
        return this;
    }

    /**
     * 设置 EditText 的 transformationMethod
     */
    public ReturnInspectionDialog setTransformationMethod(TransformationMethod transformationMethod) {
        mTransformationMethod = transformationMethod;
        return this;
    }

    /**
     * 设置 EditText 的 inputType
     */
    public ReturnInspectionDialog setInputType(int inputType) {
        mInputType = inputType;
        return this;
    }

    public ReturnInspectionDialog setInputType1(int inputType) {
        mInputType1 = inputType;
        return this;
    }

    public ReturnInspectionDialog setInputType2(int inputType) {
        mInputType2 = inputType;
        return this;
    }

    /**
     * @param dialog
     * @param parent
     * @param context
     */
    @Override
    protected void onCreateContent(QMUIDialog dialog, ViewGroup parent, Context context) {
        mEditText = new EditText(context);

        QMUIDialog.MessageDialogBuilder.assignMessageTvWithAttr(mEditText, hasTitle(), R.attr.qmui_dialog_edit_content_style);
        mEditText.setFocusable(false);
        mEditText.setEnabled(false);
        mEditText.setFocusableInTouchMode(true);
//        mEditText.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEditText.setId(R.id.qmui_dialog_edit_input);

        if (!QMUILangHelper.isNullOrEmpty(mDefaultText)) {
            mEditText.setText(mDefaultText);
        }

        mRightImageView = new ImageView(context);
        mRightImageView.setImageResource(R.mipmap.date);
        mRightImageView.setId(R.id.qmui_dialog_edit_right_icon);
        mRightImageView.setVisibility(View.VISIBLE);

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
        mEditText1.setFocusable(false);
        mEditText1.setEnabled(false);
        mEditText1.setFocusableInTouchMode(true);
//        mEditText1.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEditText1.setId(R.id.qmui_dialog_edit_input);

        if (!QMUILangHelper.isNullOrEmpty(mDefaultText1)) {
            mEditText1.setText(mDefaultText1);
        }

        mRightImageView1 = new ImageView(context);
        mRightImageView1.setImageResource(R.mipmap.date);
        mRightImageView1.setId(R.id.qmui_dialog_edit_right_icon);
        mRightImageView1.setVisibility(View.VISIBLE);

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
        if (mPlaceholder1 != null) {
            mEditText1.setHint(mPlaceholder1);
        }
//        第三个
        mEditText2 = new EditText(context);

        QMUIDialog.MessageDialogBuilder.assignMessageTvWithAttr(mEditText2, hasTitle(), R.attr.qmui_dialog_edit_content_style);
        mEditText2.setFocusable(true);
        mEditText2.setEnabled(true);
        mEditText2.setFocusableInTouchMode(true);
//        mEditText.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEditText2.setId(R.id.qmui_dialog_edit_input);

        if (!QMUILangHelper.isNullOrEmpty(mDefaultText2)) {
            mEditText2.setText(mDefaultText2);
        }

        mRightImageView2 = new ImageView(context);
        mRightImageView2.setImageResource(R.mipmap.date);
        mRightImageView2.setId(R.id.qmui_dialog_edit_right_icon);
        mRightImageView2.setVisibility(View.GONE);

        mMainLayout2 = new RelativeLayout(context);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.topMargin = mEditText2.getPaddingTop();
        lp2.leftMargin = mEditText2.getPaddingLeft();
        lp2.rightMargin = mEditText2.getPaddingRight();
        lp2.bottomMargin = mEditText2.getPaddingBottom();
        mMainLayout2.setBackgroundResource(R.drawable.qmui_edittext_bg_border_bottom);
        mMainLayout2.setLayoutParams(lp2);

        if (mTransformationMethod != null) {
            mEditText2.setTransformationMethod(mTransformationMethod);
        } else {
            mEditText2.setInputType(mInputType2);
        }

        mEditText2.setBackgroundResource(0);
        mEditText2.setPadding(0, 0, 0, QMUIDisplayHelper.dpToPx(5));
        RelativeLayout.LayoutParams editLp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editLp2.addRule(RelativeLayout.LEFT_OF, mRightImageView2.getId());
        editLp2.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        if (mPlaceholder2 != null) {
            mEditText2.setHint(mPlaceholder2);
        }
        mMainLayout.addView(mEditText, createEditTextLayoutParams());
        mMainLayout1.addView(mEditText1, createEditTextLayoutParams());
        mMainLayout2.addView(mEditText2, createEditTextLayoutParams());
        mMainLayout.addView(mRightImageView, createRightIconLayoutParams());
        mMainLayout1.addView(mRightImageView1, createRightIconLayoutParams());
        mMainLayout2.addView(mRightImageView2, createRightIconLayoutParams());

        parent.addView(mMainLayout);
        parent.addView(mMainLayout1);
        parent.addView(mMainLayout2);
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
//        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
//            }
//        });
//        mEditText.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mEditText.requestFocus();
//                inputMethodManager.showSoftInput(mEditText, 0);
//            }
//        }, 300);
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

    public EditText getEditText2() {
        return mEditText2;
    }

    public ImageView getRightImageView() {
        return mRightImageView;
    }

    public ImageView getRightImageView1() {
        return mRightImageView1;
    }

    public ImageView getRightImageView2() {
        return mRightImageView2;
    }
}
