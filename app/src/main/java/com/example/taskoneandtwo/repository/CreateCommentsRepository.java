package com.example.taskoneandtwo.repository;

import android.content.Context;

import com.example.taskoneandtwo.model.CommentsModel;
import com.example.taskoneandtwo.model.CreateCommentResponse;
import com.example.taskoneandtwo.network.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;

public class CreateCommentsRepository {
    private static CreateCommentsRepository authRepository;
    private static Context mContext;

    public static CreateCommentsRepository commentsRepository(Context context) {

        if (authRepository == null) {
            mContext = context;
            authRepository = new CreateCommentsRepository();
        }
        return authRepository;
    }

    public Observable<CreateCommentResponse> getComments(int Id, String Token, String Name, String Email, String Body) {
        return RetrofitClient.getApiService().createComments(Id,Token,Name,Email,Body);

    }
}
