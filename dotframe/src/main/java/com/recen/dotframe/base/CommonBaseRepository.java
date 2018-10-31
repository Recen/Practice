package com.recen.dotframe.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class CommonBaseRepository {

    private CompositeDisposable compositeDisposable;

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void unSubscribe() {
        if (compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
