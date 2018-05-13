package abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network;


import java.util.ArrayList;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.BuildConfig;
import abanoub.johnny.development.moviesapp.application.app.LocaleManager;
import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.MoviesPage;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.net.ProjectApi;
import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Abanoub Maher on 5/2/17.
 */

public class DataCallsImpl implements DataCall {
    Retrofit retrofit;
    ProjectApi projectApi;

    @Inject
    public DataCallsImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
        projectApi = retrofit.create(ProjectApi.class);
    }


    @Override
    public Single<MovieDetails> getMovieDetails(int id) {
        return projectApi.getMovieDetails(id, BuildConfig.API_KEY, LocaleManager.getLanguage(MyApplication.myApplicationContext));
    }

    @Override
    public Single<MoviesPage<ArrayList<Movie>>> searchMovies(String search, int page) {
        return projectApi.searchMovies(search, BuildConfig.API_KEY, LocaleManager.getLanguage(MyApplication.myApplicationContext),page);
    }

    @Override
    public Single<MoviesPage<ArrayList<Movie>>> getMovies(String state, int page) {
        return projectApi.searchMovies(state, BuildConfig.API_KEY, LocaleManager.getLanguage(MyApplication.myApplicationContext),page);
    }
}