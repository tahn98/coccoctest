package com.example.coccoctest.service;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ApiService {
    private static final String BASE_URL = "https://vnexpress.net/rss/";
    private static RssService instance = null;

    public static RssService getInstance() {
        if (instance == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.writeTimeout(60, TimeUnit.SECONDS);
            httpClient.readTimeout(60, TimeUnit.SECONDS);
            httpClient.connectTimeout(60, TimeUnit.SECONDS);
            OkHttpClient client = httpClient.build();

            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(SimpleXmlConverterFactory.create(new Persister(new AnnotationStrategy())))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(client)
                            .build();
            instance = retrofit.create(RssService.class);
        }
        return instance;
    }
}
