package com.example.coccoctest.module.news;

import com.example.coccoctest.model.RssItem;

import java.util.List;

public interface INewsView {
    void onLoadNewsSuccess(List<RssItem> listItem);
    void onError(String message);
    void onLoading();
}
