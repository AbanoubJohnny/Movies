package abanoub.johnny.development.moviesapp.mvp.bases;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.application.dagger.Injector;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.SharedPreferencesUtils;

public abstract class BaseViewModel<I extends ICommonIneractor,C extends ICommonRepository> extends ViewModel implements IViewModel, LifecycleObserver {

    @Inject
    protected I mInteractor;
    @Inject
    protected C mRepository;

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
    protected I getInteractor() {
        return mInteractor;
    }

    protected C getRepository() {
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
