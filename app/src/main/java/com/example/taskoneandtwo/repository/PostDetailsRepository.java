package com.example.taskoneandtwo.repository;

import android.content.Context;

import com.example.taskoneandtwo.model.PostsModel;
import com.example.taskoneandtwo.network.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;

public class PostDetailsRepository {
    private static PostDetailsRepository postDetailsRepository;
    private static Context mContext;

    public static PostDetailsRepository authRepository(Context context) {

        if (postDetailsRepository == null) {
            mContext = context;
            postDetailsRepository = new PostDetailsRepository();
        }
        return postDetailsRepository;
    }

    public Observable<PostsModel> getPostDetails(int Id) {
        return RetrofitClient.getApiService().postDetails(Id);

    }
}
