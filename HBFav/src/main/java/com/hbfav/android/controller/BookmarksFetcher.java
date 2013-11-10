package com.hbfav.android.controller;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BookmarksFetcher {
    public static final String[] ALLOWED_IMAGE_CONTENT_TYPE = new String[]{"image/gif", "image/png", "image/jpeg"};
    private static final String BASE_URL = "http://feed.hbfav.com/";
    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getImage(String url, BinaryHttpResponseHandler binaryHandler) {
        client.get(url, binaryHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}