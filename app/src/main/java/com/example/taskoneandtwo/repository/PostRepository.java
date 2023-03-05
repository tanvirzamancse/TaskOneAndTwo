package com.example.taskoneandtwo.repository;

import android.content.Context;

import com.example.taskoneandtwo.model.PostsModel;
import com.example.taskoneandtwo.network.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class PostRepository {
    private static PostRepository authRepository;
    private static Context mContext;

    public static PostRepository authRepository(Context context) {

        if (authRepository == null) {
            mContext = context;
            authRepository = new PostRepository();
        }
        return authRepository;
    }

    public Observable<List<PostsModel>> getPost() {
        return RetrofitClient.getApiService().posts();

    }
}
