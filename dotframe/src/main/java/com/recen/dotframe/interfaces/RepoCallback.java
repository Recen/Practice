package com.recen.dotframe.interfaces;

public interface RepoCallback<T> {
    void onSuccess(T data);
    void onFailure();
}
