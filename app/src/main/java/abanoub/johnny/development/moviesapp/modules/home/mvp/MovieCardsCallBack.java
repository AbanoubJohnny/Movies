package abanoub.johnny.development.moviesapp.modules.home.mvp;

import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;

public interface MovieCardsCallBack {
    public void openDetails(Movie movie);
    public void openDetails(MovieDetails movieDetails);
}
