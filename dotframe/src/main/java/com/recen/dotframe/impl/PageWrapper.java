package com.recen.dotframe.impl;

import com.recen.dotframe.base.CommonBaseViewModel;
import com.recen.dotframe.interfaces.IPage;

public abstract class PageWrapper extends CommonBaseViewModel{
    protected IPage page;

    public PageWrapper(){
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    public void finish(){
        page.finish();
    }

    public void init(IPage page) {
        this.page = page;
        initData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
