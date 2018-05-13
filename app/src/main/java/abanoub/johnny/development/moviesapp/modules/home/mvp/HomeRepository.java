package abanoub.johnny.development.moviesapp.modules.home.mvp;


import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.home.mvp.contract.HomeContract;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.db.deo.MoviesDao;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
public class HomeRepository implements HomeContract.IHomeRepository {

    private MoviesDao moviesDao;
    private LiveData<List<MovieDetails>> mAllMovies;
    @Inject
    public HomeRepository(AppDatabase appDatabase) {
        this.moviesDao = appDatabase.moviesDao();
        this.mAllMovies = moviesDao.getAll();
    }

    @Override
    public LiveData<List<MovieDetails>> getAllMovies() {
        return mAllMovies;
    }

    @Override
    public void onDestroy() {
    }

}
