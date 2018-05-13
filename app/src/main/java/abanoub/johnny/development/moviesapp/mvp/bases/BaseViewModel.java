package abanoub.johnny.development.moviesapp.mvp.bases;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.application.dagger.Injector;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.SharedPreferencesUtils;

import javax.inject.Inject;

public abstract class BaseViewModel<I extends ICommonIneractor,CR extends ICommonRepository> extends ViewModel implements IViewModel, LifecycleObserver {

    @Inject
    protected I mInteractor;
    @Inject
    protected CR mRepository;

    private final MutableLiveData<String> messages = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isShowingLoading = new MutableLiveData<>();

    public BaseViewModel() {
        super();
    }

    @Override
    public void shwoMessage(String message) {
        messages.postValue(message);
    }

    @Override
    public LiveData<String> getMessages() {
        return messages;
    }

    @Override
    public void shwoLoading() {
        isShowingLoading.postValue(true);
    }

    @Override
    public void hideLoading() {
        isShowingLoading.postValue(false);
    }

    @Override
    public LiveData<Boolean> getIsLoading() {
        return isShowingLoading;
    }

    public SharedPreferencesUtils sharedPreferencesUtils() {
        return Injector.INSTANCE.getAppComponent().exposeLocalDataSource().getSharedPreferences();
    }

    @Override
    public void setInteractor(Object interactor){
        mInteractor = (I) interactor;
    }

    @Override
    public void setRepository(Object repository){
        mRepository = (CR) repository ;
    }

    protected I getInteractor() {
        return mInteractor;
    }

    protected CR getRepository() {
        return mRepository;
    }

    protected AppDatabase getAppDB(){
        return MyApplication.getAppDatabase();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        shwoMessage("onCleared");
    }
}
