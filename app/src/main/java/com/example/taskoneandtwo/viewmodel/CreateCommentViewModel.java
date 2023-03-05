package com.example.taskoneandtwo.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.taskoneandtwo.model.CreateCommentResponse;
import com.example.taskoneandtwo.repository.CreateCommentsRepository;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateCommentViewModel extends AndroidViewModel {
    private CreateCommentsRepository commentsRepository;
    public MutableLiveData<CreateCommentResponse> mLivedata;
    private Observable<CreateCommentResponse> observable;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public CreateCommentViewModel(@NonNull Application application) {
        super(application);
        commentsRepository = CreateCommentsRepository.commentsRepository(application);
    }


    public LiveData<CreateCommentResponse> getPostDetailsViewModel(int Id, String Token, String Name, String Email, String Body) {

        if (mLivedata == null) {
            mLivedata = new MutableLiveData<>();
        }

        observable = commentsRepository.getComments(Id, Token, Name, Email, Body);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateCommentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(CreateCommentResponse createCommentResponse) {
                        mLivedata.postValue(createCommentResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplication().getApplicationContext(), "onError : " + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return mLivedata;

    }
}
