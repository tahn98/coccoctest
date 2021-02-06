package com.example.coccoctest.module.news;

import android.app.Activity;
import android.util.Log;

import com.example.coccoctest.base.BasePresenter;
import com.example.coccoctest.model.RssRoot;
import com.example.coccoctest.service.ApiService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter extends BasePresenter<INewsView> implements INewsPresenter {

    @Override
    public void loadNews() {
        if (isViewAttached()) {
            Observable<RssRoot> call = ApiService.getInstance().getNews();
            Disposable disposable = call.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        Log.d("tahn", response.toString());
                        getView().onLoadNewsSuccess(response.getChannel().getItem());
                    }, throwable -> {
                        Log.d("tahn", throwable.getMessage());
                        getView().onError(throwable.getMessage());
                    });
        }
    }
}
