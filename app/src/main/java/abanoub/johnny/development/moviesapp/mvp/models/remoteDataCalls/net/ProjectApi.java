package abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.net;

import java.util.ArrayList;

import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.MoviesPage;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProjectApi {

    @GET("movie/{movie_id}")
    Single<MovieDetails> getMovieDetails(@Path("movie_id") int id,
                                                       @Query("api_key") String api_key,
                                                       @Query("language") String language);

    @GET("search/movie")
    Single<MoviesPage<ArrayList<Movie>>> searchMovies(@Query("query") String search,
                                                        @Query("api_key") String api_key,
                                                        @Query("language") String language,
                                                        @Query("page") int page);

    @GET("movie/popular")
    Single<MoviesPage<ArrayList<Movie>>> getPopularMovies(@Query("api_key") String api_key,
                                                   @Query("language") String language,
                                                   @Query("page") int page);
    @GET("movie/top_rated")
    Single<MoviesPage<ArrayList<Movie>>> getTopRatedMovies(@Query("api_key") String api_key,
                                                          @Query("language") String language,
                                                          @Query("page") int page);

}

