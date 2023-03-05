package com.example.taskoneandtwo.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.taskoneandtwo.model.PostsModel;
import com.example.taskoneandtwo.repository.PostRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends AndroidViewModel {
    private PostRepository postRepository;
    public MutableLiveData<List<PostsModel>> mLivedata;
    private Observable<List<PostsModel>> single;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public PostsViewModel(@NonNull Application application) {
        super(application);
        postRepository = PostRepository.authRepository(application);
    }


    public LiveData<List<PostsModel>> getPostsViewModel() {

        if (mLivedata == null) {
            mLivedata = new MutableLiveData<>();
        }

        single = postRepository.getPost();

        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PostsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(List<PostsModel> postsModels) {
                        mLivedata.postValue(postsModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplication().getApplicationContext(), "onError : "+e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return mLivedata;

    }
}
