package com.rsui.rs_network.network;

import android.content.Context;

import com.rsui.rs_network.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://192.168.1.3:3000/"; // Thay bằng IP của Server chạy API
    private static Retrofit retrofit = null;
    private static Context appContext;
    private static TokenRepository tokenRepository;

    // Khởi tạo với Context
    public static void initialize(Context context) {
        appContext = context.getApplicationContext();
    }
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Tạo OkHttp client
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);

            // 1. Thêm auth interceptor trước
            tokenRepository = new TokenRepository(appContext);
            httpClient.addInterceptor(new AuthInterceptor(tokenRepository));

            // 2. Thêm logging interceptor sau cùng để log cả retry và tránh lỗi closed response
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
    public static ApiService getApi() {
        return getClient().create(ApiService.class);
    }
}
