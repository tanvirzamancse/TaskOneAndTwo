package com.example.taskoneandtwo.network;

import androidx.constraintlayout.widget.ConstraintSet;

import com.example.taskoneandtwo.utils.ConstrantValue;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class RetrofitClient {
    private static Retrofit retrofit;
   @Singleton
    @Provides
    public static ApiService getApiService() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ConstrantValue.BASE_URL)
                    .client(getOkhttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
    private static OkHttpClient getOkhttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(getHttpLoginInterceptor())
                .build();
    }
    private static HttpLoggingInterceptor getHttpLoginInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
