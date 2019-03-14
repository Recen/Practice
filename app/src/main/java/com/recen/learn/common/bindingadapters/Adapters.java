package com.recen.learn.common.bindingadapters;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.recen.dotui.view.LoadingLayout;
import com.recen.learn.common.util.PageManagerBase;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class Adapters {
    @BindingAdapter({"imageUrl", "placeHolder"})
    public static void loadImage(ImageView imageView, String url, @DrawableRes  int holderDrawable) {
        RequestOptions options = new RequestOptions()
                .placeholder(holderDrawable)
                .error(holderDrawable);
        Glide.with(imageView.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }

    @BindingAdapter("loadingView")
    public static void setLoadingView(LoadingLayout loadingLayout,int loadingView){
        if (loadingLayout != null){
            loadingLayout.setDefaultView(LoadingLayout.ViewType.Loading,loadingView);
        }
    }

    @BindingAdapter("noDataView")
    public static void setLoadingNoDataView(LoadingLayout loadingLayout,int noDataView){
        if (loadingLayout != null){
            loadingLayout.setDefaultView(LoadingLayout.ViewType.NoData,noDataView);
        }
    }

    @BindingAdapter("noNetworkView")
    public static void setLoadingNoNetworkView(LoadingLayout loadingLayout,int noNetworkView){
        if (loadingLayout != null){
            loadingLayout.setDefaultView(LoadingLayout.ViewType.NetworkException,noNetworkView);
        }
    }

    @BindingAdapter("pageManager")
    public static <ItemType> void pageManagerAttachRecyclerView(SmartRefreshLayout view, PageManagerBase<ItemType> pageManager) {
        pageManager.attach(view);
    }

    @BindingAdapter("pageManager")
    public static <ItemType> void pageManagerAttachRecyclerView(LoadingLayout view, PageManagerBase<ItemType> pageManager) {
        pageManager.setLoadingLayout(view);
    }
}
