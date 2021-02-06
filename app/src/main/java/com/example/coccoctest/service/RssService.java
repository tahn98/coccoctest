package com.example.coccoctest.service;

import com.example.coccoctest.model.RssRoot;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RssService {
    @GET("tin-moi-nhat.rss")
    Observable<RssRoot> getNews();
}
