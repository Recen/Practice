package com.recen.learn.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.recen.dotframe.base.CommonApp;
import com.recen.learn.common.widget.CustomRefreshHeaderView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
//import com.tencent.bugly.crashreport.CrashReport;

public class App extends CommonApp {

    @Override
    public void onCreate() {
        super.onCreate();
// 配置下拉刷新默认头部和加载更多
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new CustomRefreshHeaderView(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context);
            }
        });
//        CrashReport.initCrashReport(this, "f018a89fa0", true);
//        PayegisDidSdk.getInstance().init(, 1, 1);
    }
}
