package com.recen.dotframe.impl;

import com.recen.dotframe.base.CommonBaseRepository;
import com.recen.dotframe.base.CommonBaseViewModel;
import com.recen.dotframe.interfaces.IPage;
import com.recen.dotutil.DUtil;

public abstract class PageWrapper<T extends CommonBaseRepository> extends CommonBaseViewModel{
    protected IPage page;
    protected T mRepository;
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
        mRepository = DUtil.getNewInstance(this,0);
        initData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mRepository.unSubscribe();
    }
}
