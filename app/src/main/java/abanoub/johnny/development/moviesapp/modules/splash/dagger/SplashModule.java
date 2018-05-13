package abanoub.johnny.development.moviesapp.modules.splash.dagger;


import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.SplashPresenter;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.SplashRepository;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.contract.SplashContract;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.interactors.SplashInteractor;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;

import abanoub.johnny.development.moviesapp.modules.splash.mvp.SplashPresenter;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.SplashRepository;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.contract.SplashContract;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.interactors.SplashInteractor;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {
    private SplashContract.View view;

    public SplashModule(SplashContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    SplashContract.ISplashInteractor provideSplashInteractor(
            DataCall dataCall, LocalDataSource locatDataSource) {
        return new SplashInteractor(dataCall, locatDataSource);
    }
    @Provides
    @ActivityScope
    SplashContract.View provideSplashView() {
        return this.view;
    }

    @Provides
    @ActivityScope
    SplashPresenter provideSplashPresenter(
            SplashContract.View view) {
        return new SplashPresenter(view);
    }
    @Provides
    @ActivityScope
    SplashContract.ISplashRepository provideSplashRepository(AppDatabase appDatabase) {
        return new SplashRepository(appDatabase);
    }

}