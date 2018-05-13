package abanoub.johnny.development.moviesapp.modules.movie.mvp;

import android.os.AsyncTask;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.contract.MovieContract;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.db.deo.MoviesDao;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
public class MovieRepository implements MovieContract.IMovieRepository {


    private MoviesDao moviesDao;
    private InsertAsyncTask insertAsyncTask;
    private DeleteAsyncTask deleteAsyncTask;

    @Inject
    public MovieRepository(AppDatabase appDatabase) {
        this.moviesDao = appDatabase.moviesDao();
    }

    @Override
    public void insert(MovieDetails movieDetails) {
        insertAsyncTask = new InsertAsyncTask(moviesDao);
        insertAsyncTask.execute(movieDetails);
    }

    @Override
    public void delete(MovieDetails movieDetails) {
        deleteAsyncTask = new DeleteAsyncTask(moviesDao);
        deleteAsyncTask.execute(movieDetails);
    }

    @Override
    public void onDestroy() {
        if (insertAsyncTask != null)
            insertAsyncTask.cancel(true);
        if (deleteAsyncTask != null)
            deleteAsyncTask.cancel(true);
    }

    private static class InsertAsyncTask extends AsyncTask<MovieDetails, Void, Void> {

        private MoviesDao mAsyncTaskDao;

        InsertAsyncTask(MoviesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MovieDetails... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<MovieDetails, Void, Void> {

        private MoviesDao mAsyncTaskDao;

        DeleteAsyncTask(MoviesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MovieDetails... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

}
