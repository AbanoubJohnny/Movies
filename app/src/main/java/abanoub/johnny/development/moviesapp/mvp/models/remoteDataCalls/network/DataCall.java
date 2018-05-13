package abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network;


import java.util.ArrayList;

import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.MoviesPage;
import io.reactivex.Single;

public interface DataCall {

    Single<MovieDetails> getMovieDetails(int id);

    Single<MoviesPage<ArrayList<Movie>>> searchMovies(String search,int page);

    Single<MoviesPage<ArrayList<Movie>>> getPopularMovies(int page);

    Single<MoviesPage<ArrayList<Movie>>> getTopRatedMovies(int page);
}
