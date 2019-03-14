package com.recen.dotui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.recen.dotui.R;

public class LoadingLayout extends FrameLayout {
    public enum ViewType {
        /**
         * 请求中
         */
        Loading             (0),
        /**
         * 加载异常, 在无合适的异常类型使用的情况下使用此异常
         */
        DefaultException    (1),
        /**
         * 网络异常
         */
        NetworkException    (2),
        /**
         * 无网络
         */
        NoNetwork           (3),
        /**
         * 返回数据为空
         */
        NoData              (4);

        ViewType(int ni) {
            nativeInt = ni;
        }
        final int nativeInt;
    }

    private SparseIntArray defaultLayout = new SparseIntArray(5);
    private SparseArray<View> cachedLayout = new SparseArray<View>(5);
    private LayoutInflater mInflater;
    private OnClickListener mBtn1Listener;
    private OnClickListener mBtn2Listener;
    private OnClickListener mBtn3Listener;
    private Context mContext;

    public LoadingLayout(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode())
            return;
        mContext = context;

        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingLayout, R.attr.mcLoadingLayoutStyle, 0);
            if (a != null) {
                setDefaultView(ViewType.Loading, a.getResourceId(
                        R.styleable.LoadingLayout_loadingView, R.layout.dotui_loading));
                setDefaultView(ViewType.DefaultException, a.getResourceId(
                        R.styleable.LoadingLayout_defaultExceptionView, R.layout.dotui_loading_default_exception));
                setDefaultView(ViewType.NetworkException, a.getResourceId(
                        R.styleable.LoadingLayout_networkExceptionView, R.layout.dotui_loading_default_network_exception));
                setDefaultView(ViewType.NoNetwork, a.getResourceId(
                        R.styleable.LoadingLayout_noNetworkView, R.layout.dotui_loading_no_network));
                setDefaultView(ViewType.NoData, a.getResourceId(
                        R.styleable.LoadingLayout_noDataView, R.layout.dotui_loading_no_data));
                a.recycle();
            }
        }
    }

    public void setDefaultView(ViewType viewType, int resLayout) {
        defaultLayout.put(viewType.nativeInt, resLayout);
    }

    public void showView(ViewType viewType) {
        int count = defaultLayout.size();
        for(int i = 0; i < count; i++) {
            int key = defaultLayout.keyAt(i);
            if(key==viewType.nativeInt){
                doShowView(viewType);
            }else{
                hideViewByKey(key);
            }
        }
    }

    private void hideViewByKey(int key) {
        View view = cachedLayout.get(key);

        if (view==null)
            return;

        view.setVisibility(GONE);
    }

    private void doShowView(ViewType viewType) {
        int resLayoutId = defaultLayout.get(viewType.nativeInt);
        if (resLayoutId <= 0)
            throw new IllegalStateException("layout is not set for " + viewType);

        View view = cachedLayout.get(viewType.nativeInt);

        if (view == null) {
            view = mInflater.inflate(resLayoutId, null);
            cachedLayout.put(viewType.nativeInt, view);
            addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));
            initListener(view);
        }

        view.setVisibility(VISIBLE);
        view.bringToFront();
    }

    private void initListener(View view) {
        View btn1 = view.findViewById(android.R.id.button1);
        if (btn1 != null && mBtn1Listener != null) {
            btn1.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    mBtn1Listener.onClick(v);
                }
            });
        }

        View btn2 = view.findViewById(android.R.id.button2);
        if (btn2 != null && mBtn2Listener != null) {
            btn2.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    mBtn2Listener.onClick(v);
                }
            });
        }

        View btn3 = view.findViewById(android.R.id.button3);
        if (btn3 != null && mBtn3Listener != null) {
            btn3.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    mBtn3Listener.onClick(v);
                }
            });
        }
    }

    public void showLoadingView() {
        showView(ViewType.Loading);
    }

    public void hideLoadingView() {
        hideViewByKey(ViewType.Loading.nativeInt);
    }

    public void showNoDataView() {
        showView(ViewType.NoData);
    }

    public void hideNoDataView() {
        hideViewByKey(ViewType.NoData.nativeInt);
    }

    public void setButton1ClickListener(OnClickListener listener){
        mBtn1Listener = listener;
    }
}
