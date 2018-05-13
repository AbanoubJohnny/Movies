package abanoub.johnny.development.moviesapp.modules.home.dagger;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.home.mvp.HomePresenter;
import abanoub.johnny.development.moviesapp.modules.home.mvp.HomeRepository;
import abanoub.johnny.development.moviesapp.modules.home.mvp.contract.HomeContract;
import abanoub.johnny.development.moviesapp.modules.home.mvp.interactors.HomeInteractor;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@Module
public class HomeModule {
    private HomeContract.IHomeView view;

    public HomeModule(HomeContract.IHomeView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    HomeContract.IHomeView provideSplashView() {
        return this.view;
    }

    @Provides
    @ActivityScope
    HomeContract.IHomeInteractor provideHomeInteractor(
            DataCall dataCall, LocalDataSource locatDataSource) {
        return new HomeInteractor(dataCall, locatDataSource);
    }

    @Provides
    @ActivityScope
    HomePresenter provideHomePresenter(
            HomeContract.IHomeView view) {
        return new HomePresenter(view);
    }

    @Provides
    @ActivityScope
    HomeContract.IHomeRepository provideHomeRepository(AppDatabase appDatabase) {
        return new HomeRepository(appDatabase);
    }
}