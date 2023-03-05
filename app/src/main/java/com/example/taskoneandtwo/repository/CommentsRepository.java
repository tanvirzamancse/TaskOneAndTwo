package com.example.taskoneandtwo.repository;

import android.content.Context;

import com.example.taskoneandtwo.model.CommentsModel;
import com.example.taskoneandtwo.model.PostsModel;
import com.example.taskoneandtwo.network.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;

public class CommentsRepository {
    private static CommentsRepository authRepository;
    private static Context mContext;

    public static CommentsRepository commentsRepository(Context context) {

        if (authRepository == null) {
            mContext = context;
            authRepository = new CommentsRepository();
        }
        return authRepository;
    }

    public Observable<List<CommentsModel>> getComments(int Id) {
        return RetrofitClient.getApiService().postComments(Id);

    }
}
