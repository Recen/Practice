package com.recen.learn.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.recen.learn.R;


public class TitleBar extends RelativeLayout {
    private TextView mTitleView;
    private TextView mBtnLeftView;
    private TextView mBtnRightView;
    private PopupWindow mRightPopupWindow;

    public TitleBar(Context context) {
        super(context);
        init(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_title_bar, this);
        mTitleView = (TextView) findViewById(R.id.title);
        mBtnLeftView = (TextView) findViewById(R.id.btnLeft);
        mBtnRightView = (TextView) findViewById(R.id.btnRight);

        // Load attributes
        if (attrs == null) {
            return;
        }
        final TypedArray typeArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.titleBar, 0, 0);

        mTitleView.setText(typeArray.getString(R.styleable.titleBar_android_title));
        {
            Drawable drawable = typeArray.getDrawable(R.styleable.titleBar_titleDrawableRight);
            if(drawable != null){
                drawable.setBounds(0 ,0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mTitleView.setCompoundDrawables(null, null, drawable, null);
            }
        }

        Drawable leftButtonImage = typeArray.getDrawable(R.styleable.titleBar_leftButtonImage);
        Drawable rightButtonImage = typeArray.getDrawable(R.styleable.titleBar_rightButtonImage);
        String leftButtonText = typeArray.getString(R.styleable.titleBar_leftButtonText);
        String rightButtonText = typeArray.getString(R.styleable.titleBar_rightButtonText);
        typeArray.recycle();

        if (leftButtonImage != null) {
            leftButtonImage.setBounds(0, 0, leftButtonImage.getMinimumWidth(), leftButtonImage.getMinimumHeight());
            mBtnLeftView.setCompoundDrawables(leftButtonImage, null, null, null);
        } else {
            mBtnLeftView.setText(leftButtonText);
        }

        if (rightButtonImage != null) {
            rightButtonImage.setBounds(0, 0, rightButtonImage.getMinimumWidth(), rightButtonImage.getMinimumHeight());
            mBtnRightView.setCompoundDrawables(null, null, rightButtonImage, null);
        } else {
            mBtnRightView.setText(rightButtonText);
        }
    }

    public CharSequence getTitle() {
        return mTitleView.getText();
    }

    public void setTitle(CharSequence title) {
        mTitleView.setText(title);
    }

    public void setLeftButtonVisibility(int visibility){
        mBtnLeftView.setVisibility(visibility);
    }

    public void setRightButtonVisibility(int visibility){
        mBtnRightView.setVisibility(visibility);
    }

    public void setLeftButtonClickListener(OnClickListener listener){
        mBtnLeftView.setOnClickListener(listener);
    }

    public void setRightButtonClickListener(final OnClickListener listener){
        mBtnRightView.setOnClickListener(listener);
    }

    public void setLeftButtonText(CharSequence text){
        mBtnLeftView.setCompoundDrawables(null, null, null, null);
        mBtnLeftView.setText(text);
    }

    public void setRightButtonText(CharSequence text){
        mBtnRightView.setCompoundDrawables(null, null, null, null);
        mBtnRightView.setText(text);
    }

    public void setLeftButtonImage(Drawable drawable){
        if(drawable !=null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mBtnLeftView.setCompoundDrawables(drawable, null, null, null);
        }
        mBtnLeftView.setText("");
    }

    public void setRightButtonImage(Drawable drawable){
        if(drawable !=null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mBtnRightView.setCompoundDrawables(null, null, drawable, null);
        }
        mBtnRightView.setText("");
    }

    public void setRightButtonClickable(boolean clickable){
        if(clickable){
            //mBtnRightView.setTextColor(getResources().getColor(R.color.font_blue));
            mBtnRightView.setClickable(true);
        } else {
            //mBtnRightView.setTextColor(getResources().getColor(R.color.font_grey));
            mBtnRightView.setClickable(false);
        }
    }

    public void setTitleDrawableRight(Drawable drawableRight){
        drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
        mTitleView.setCompoundDrawables(null, null, drawableRight, null);
    }

    public void setTitleClickListener(OnClickListener listener){
        mTitleView.setOnClickListener(listener);
    }
}
