package abanoub.johnny.development.moviesapp.modules.splash.mvp.interactors;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.contract.SplashContract;
import abanoub.johnny.development.moviesapp.mvp.bases.CommonIneractor;
import abanoub.johnny.development.moviesapp.mvp.bases.InteractorUtilities;
import abanoub.johnny.development.moviesapp.mvp.models.data.callbacks.InteractorCallback;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions.ProjectThrowable;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Abanoub Johnny on 2/16/2018.
 */

@ActivityScope
public class SplashInteractor extends CommonIneractor implements SplashContract.ISplashInteractor {

    ArrayList<ProjectThrowable> errors = new ArrayList<>();
    ProjectThrowable projectThrowable = new ProjectThrowable();
    @Inject
    public SplashInteractor(DataCall dataCall, LocalDataSource localDataSource) {
        super(dataCall, localDataSource);
    }


}
