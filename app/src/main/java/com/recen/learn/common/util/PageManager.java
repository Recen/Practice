package com.recen.learn.common.util;

import java.util.List;

/**
 * Created by kevin on 2016/11/9.
 */

public class PageManager<ItemType> extends PageManagerBase<ItemType> {
    public PageManager(int startPage, int numPageItem, List<ItemType> items) {
        super(startPage, numPageItem, items);
    }

    public void loadFirstPage() {
        super.loadFirstPage();
    }

    protected void refresh() {
        super.refresh();
    }

    public interface PageLoadListener<ItemType> extends PageManagerBase.PageLoadListener<ItemType> {
    }

    public interface PageLoadCallback<ItemType> extends PageManagerBase.PageLoadCallback<ItemType> {
    }
}
