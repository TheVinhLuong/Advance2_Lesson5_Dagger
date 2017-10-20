package com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.middleware;

import java.io.IOException;
import java.net.HttpURLConnection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class InterceptorImplementation implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = initializeHeader(chain);
        Request request = builder.build();
        Response response = chain.proceed(request);
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            request = builder.build();
            response = chain.proceed(request);
        }
        return response;
    }

    private Request.Builder initializeHeader(Chain chain) {
        Request originRequest = chain.request();
        return originRequest.newBuilder()
                .header("Accept", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store")
                .method(originRequest.method(), originRequest.body());
    }
}