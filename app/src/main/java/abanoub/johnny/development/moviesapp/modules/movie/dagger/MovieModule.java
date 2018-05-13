package abanoub.johnny.development.moviesapp.modules.movie.dagger;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.MoviePresenter;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.MovieRepository;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.contract.MovieContract;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.interactors.MovieInteractor;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@Module
public class MovieModule {
    private MovieContract.IMovieView view;

    public MovieModule(MovieContract.IMovieView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    MovieContract.IMovieView provideSplashView() {
        return this.view;
    }

    @Provides
    @ActivityScope
    MovieContract.IMovieInteractor provideMovieInteractor(
            DataCall dataCall, LocalDataSource locatDataSource) {
        return new MovieInteractor(dataCall, locatDataSource);
    }

    @Provides
    @ActivityScope
    MoviePresenter provideMoviePresenter(
            MovieContract.IMovieView view) {
        return new MoviePresenter(view);
    }

    @Provides
    @ActivityScope
    MovieContract.IMovieRepository provideMovieRepository(AppDatabase appDatabase) {
        return new MovieRepository(appDatabase);
    }
}