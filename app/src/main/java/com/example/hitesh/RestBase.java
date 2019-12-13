package com.example.hitesh;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RestBase {

    private static AsyncHttpClient client = new AsyncHttpClient();

    private static final String TAG = "RestBase";




    public static void get(String url, RequestParams params, String auth_key, AsyncHttpResponseHandler responseHandler) {
//        Log.e(TAG, "get() called with: url = [" + url + "], params = [" + params + "], responseHandler = [" + responseHandler + "]");
        client.addHeader("X-Auth-Token",auth_key);
        client.get(url, params, responseHandler);
        client.setConnectTimeout(10);
    }

    public static void post(String url, RequestParams params, String auth_key, AsyncHttpResponseHandler responseHandler) {
        Log.d(TAG, "post() called with: url = [" + url + "], params = [" + params + "], auth_key = [" + auth_key + "]");
        client.addHeader("X-Auth-Token",auth_key);
        client.post(url, params, responseHandler);

    }



    public static void get(String url,RequestParams params,JsonHttpResponseHandler responseHandler) {
//        Log.e(TAG, "get() called with: url = [" + url + "], params = [" + params + "], responseHandler = [" + responseHandler + "]");

    }

    public static void post(String url, RequestParams params, String auth_key, JsonHttpResponseHandler responseHandler) {
        Log.d(TAG, "post() called with: url = [" + url + "], params = [" + params + "], auth_key = [" + auth_key + "]");
        client.addHeader("Authorization",auth_key);
        client.post(url, params, responseHandler);

    }

    public static void postx(String url, RequestParams params, String auth_key, JsonHttpResponseHandler responseHandler) {
        Log.d(TAG, "post() called with: url = [" + url + "], params = [" + params + "], auth_key = [" + auth_key + "]");
        client.addHeader("X-Auth-Token",auth_key);
        client.post(url, params, responseHandler);

    }


}
