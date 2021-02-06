package com.example.coccoctest.base;

public interface IPresenter<V> {
    void attachView(V mvpView);

    void detachView();
}