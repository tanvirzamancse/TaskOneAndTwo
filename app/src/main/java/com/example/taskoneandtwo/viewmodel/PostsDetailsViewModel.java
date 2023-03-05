package com.example.taskoneandtwo.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.taskoneandtwo.model.PostsModel;
import com.example.taskoneandtwo.repository.PostDetailsRepository;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostsDetailsViewModel extends AndroidViewModel {
    private PostDetailsRepository postDetailsRepository;
    public MutableLiveData<PostsModel> mLivedata;
    private Observable<PostsModel> observable;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public PostsDetailsViewModel(@NonNull Application application) {
        super(application);
        postDetailsRepository = PostDetailsRepository.authRepository(application);
    }


    public LiveData<PostsModel> getPostDetailsViewModel(int Id) {

        if (mLivedata == null) {
            mLivedata = new MutableLiveData<>();
        }

        observable = postDetailsRepository.getPostDetails(Id);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(PostsModel postsModel) {
                        mLivedata.postValue(postsModel);
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
