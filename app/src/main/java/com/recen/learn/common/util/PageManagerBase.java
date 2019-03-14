package com.recen.learn.common.util;

import android.support.annotation.NonNull;
import android.view.View;


import com.recen.dotui.view.LoadingLayout;
import com.recen.dotutil.ToastUtil;
import com.recen.learn.network.ServiceException;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by kevin on 2016/9/1.
 */

public class PageManagerBase<ItemType> {
    protected WeakReference<SmartRefreshLayout> refreshLayout;
    protected WeakReference<LoadingLayout> loadingLayout;
    protected List<ItemType> items;
    protected final int startPage;
    protected final int numPageItem;
    protected int currentPageNo;
    protected boolean enableRefresh = true;
    protected boolean enableLoadPage = true;
    protected boolean isLoading = false;
    protected PageLoadListener pageLoadListener;
    private boolean showMessage = true;

    public PageManagerBase(int startPage, final int numPageItem, List<ItemType> items) {
        //numPageItem为服务端返回的一页里面item个数
        this.startPage = startPage;
        this.numPageItem = numPageItem;
        this.items = items;
        currentPageNo = startPage;
    }

    public void attach(final SmartRefreshLayout refreshLayout) {
        this.refreshLayout = new WeakReference<>(refreshLayout);

        setRefreshMode(RefreshMode.MODE_BOTH);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (enableRefresh){
                    refresh();
                }

            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (enableLoadPage)
                loadMorePage();
            }
        });
    }

    public void setLoadingLayout(LoadingLayout loadingLayout) {
        this.loadingLayout = new WeakReference<>(loadingLayout);
        loadingLayout.setButton1ClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("重新加載");
                loadFirstPage();
            }
        });
    }

    public void enableRefresh(boolean enable) {
        this.enableRefresh = enable;
        resetPullMode();
    }

    public void enableLoadPage(boolean enable) {
        this.enableLoadPage = enable;
        resetPullMode();
    }

    public void resetPullMode() {
        if (this.refreshLayout == null){
            return;
        }
        SmartRefreshLayout recyclerView = this.refreshLayout.get();
        if (recyclerView != null) {
            RefreshMode refreshMode = RefreshMode.MODE_DISABLE;
            if (enableRefresh && enableLoadPage) {
                refreshMode = RefreshMode.MODE_BOTH;
            } else if (enableRefresh && !enableLoadPage) {
                refreshMode = RefreshMode.MODE_REFRESH;
            } else if (!enableRefresh && enableLoadPage) {
                refreshMode = RefreshMode.MODE_LOAD_MORE;
            }
            setRefreshMode(refreshMode);
        }
    }

    public void setRefreshMode(RefreshMode mode){
        switch (mode){
            case MODE_BOTH:
                refreshLayout.get().setEnableRefresh(true);
                refreshLayout.get().setEnableLoadMore(true);
                break;
            case MODE_LOAD_MORE:
                refreshLayout.get().setEnableRefresh(false);
                refreshLayout.get().setEnableLoadMore(true);
                break;
            case MODE_REFRESH:
                refreshLayout.get().setEnableRefresh(true);
                refreshLayout.get().setEnableLoadMore(false);
                break;
            case MODE_DISABLE:
                refreshLayout.get().setEnableRefresh(false);
                refreshLayout.get().setEnableLoadMore(false);
                break;
                default:
                    refreshLayout.get().setEnableRefresh(true);
                    refreshLayout.get().setEnableLoadMore(true);
                    break;

        }
    }

    public void setPageLoadListener(PageLoadListener pageLoadListener) {
        this.pageLoadListener = pageLoadListener;
    }

    private void loadFirstSuccess(List<ItemType> items) {
        isLoading = false;
        refreshLayout.get().finishRefresh();
        hideLoadingView();

        if (items.isEmpty()) {
            showNoDataView();
        } else {
            PageManagerBase.this.items.clear();
            PageManagerBase.this.items.addAll(items);
        }
    }

    public void loadFirstPage() {
        //防止多次请求翻页导致的重复数据
        if (isLoading) {
            return;
        }
        isLoading = true;

        showLoadingView();
        pageLoadListener.pageLoad(false, currentPageNo = startPage, numPageItem, new PageLoadCallback<ItemType>() {
            @Override
            public void onSuccess(List<ItemType> items) {
                loadFirstSuccess(items);
            }

            @Override
            public void onFailed(ServiceException exception) {
                isLoading = false;
                hideLoadingView();
                onException(exception);
            }
        });
    }

    private void loadMoreSuccess(List<ItemType> items) {
        isLoading = false;
        if (refreshLayout != null && refreshLayout.get() != null) {
            refreshLayout.get().finishLoadMore();
        }
        if (items.isEmpty()) {
            currentPageNo--;
            //TODO add end footer
            if(showMessage) {
                ToastUtil.showToast("已经到底啦");
            }
            showMessage = true;
        } else {
            PageManagerBase.this.items.addAll(items);
        }
    }

    protected void loadMorePage() {
        //防止多次请求翻页导致的重复数据
        if (isLoading) {
            return;
        }
        isLoading = true;

        pageLoadListener.pageLoad(false, ++currentPageNo, numPageItem, new PageLoadCallback<ItemType>() {
            @Override
            public void onSuccess(List<ItemType> items) {
                loadMoreSuccess(items);
            }

            @Override
            public void onFailed(ServiceException exception) {
                isLoading = false;
                onException(exception);
            }
        });
    }

    private void refreshLoadSuccess(List<ItemType> items) {
        isLoading = false;

        refreshLayout.get().finishRefresh();
        if (items.isEmpty()) {
            showNoDataView();
        } else {
            PageManagerBase.this.items.clear();
            PageManagerBase.this.items.addAll(items);
        }
    }

    protected void refresh() {
        //防止多次请求翻页导致的重复数据
        if (isLoading) {
            return;
        }
        isLoading = true;

        pageLoadListener.pageLoad(true, currentPageNo = startPage, numPageItem, new PageLoadCallback<ItemType>() {
            @Override
            public void onSuccess(List<ItemType> items) {
                refreshLoadSuccess(items);
            }

            @Override
            public void onFailed(ServiceException exception) {
                isLoading = false;
                onException(exception);
            }
        });
    }

    protected void onException(ServiceException exception) {
        currentPageNo--;
        showExceptionView(exception);
    }

    protected void showLoadingView() {
        if (loadingLayout != null && loadingLayout.get() != null) {
            loadingLayout.get().showLoadingView();
        }
    }

    protected void hideLoadingView() {
        if (loadingLayout != null && loadingLayout.get() != null) {
            loadingLayout.get().hideLoadingView();
        }
    }

    protected void showNoDataView() {
        if (loadingLayout != null && loadingLayout.get() != null) {
            loadingLayout.get().showNoDataView();
        }
    }

    protected void showExceptionView(ServiceException exception) {
        if (loadingLayout != null && loadingLayout.get() != null) {
//            if (exception.getReturnCode() == ErrorCode.ERROR_NO_NETWORK) {
//                loadingLayout.get().showView(LoadingLayout.ViewType.NoNetwork);
//            } else {
                loadingLayout.get().showView(LoadingLayout.ViewType.NetworkException);
//            }
        }
    }



    public interface PageLoadListener<ItemType> {
        void pageLoad(boolean isRefresh, int pageIndex, int pageNum, PageLoadCallback<ItemType> callback);
    }

    public interface PageLoadCallback<ItemType> {
        void onSuccess(List<ItemType> items);

        void onFailed(ServiceException exception);
    }
}
