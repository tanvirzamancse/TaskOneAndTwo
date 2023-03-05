package com.example.taskoneandtwo.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.taskoneandtwo.model.CommentsModel;
import com.example.taskoneandtwo.repository.CommentsRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentsViewModel extends AndroidViewModel {
    private CommentsRepository commentsRepository;
    public MutableLiveData<List<CommentsModel>> mLivedata;
    private Observable<List<CommentsModel>> observable;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public CommentsViewModel(@NonNull Application application) {
        super(application);
        commentsRepository = CommentsRepository.commentsRepository(application);
    }


    public LiveData<List<CommentsModel>> getCommentsViewModel(int Id) {

        if (mLivedata == null) {
            mLivedata = new MutableLiveData<>();
        }

        observable = commentsRepository.getComments(Id);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CommentsModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(List<CommentsModel> commentsModels) {
                        mLivedata.postValue(commentsModels);
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
