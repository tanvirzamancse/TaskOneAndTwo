package com.example.taskoneandtwo.network;

import com.example.taskoneandtwo.model.CommentsModel;
import com.example.taskoneandtwo.model.CreateCommentResponse;
import com.example.taskoneandtwo.model.PostsModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("posts")
    Observable<List<PostsModel>> posts();

    @GET("posts/{id}")
    Observable<PostsModel> postDetails(
            @Path("id") int Id
    );

    @GET("posts/{id}/comments")
    Observable<List<CommentsModel>> postComments(
            @Path("id") int Id
    );

    @POST("posts/{id}/comments")
    @FormUrlEncoded
    Observable<CreateCommentResponse> createComments(
            @Path("id") int Id,
            @Header("Authorization") String Token,
            @Field("name") String name,
            @Field("email") String email,
            @Field("body") String body
    );
}
