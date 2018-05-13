package abanoub.johnny.development.moviesapp.modules.movie.mvp.contract;

import android.arch.lifecycle.LiveData;

import abanoub.johnny.development.moviesapp.mvp.bases.ICommonIneractor;
import abanoub.johnny.development.moviesapp.mvp.bases.ICommonRepository;
import abanoub.johnny.development.moviesapp.mvp.bases.IPresenter;
import abanoub.johnny.development.moviesapp.mvp.bases.IView;
import abanoub.johnny.development.moviesapp.mvp.bases.IViewModel;
import abanoub.johnny.development.moviesapp.mvp.models.data.callbacks.InteractorCallback;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

public interface MovieContract {

    interface IMovieView extends IView {
        //put here all methods that view will implement and presenter will use to update the view

    }

    interface IMovieInteractor extends ICommonIneractor {
        void getMovie(InteractorCallback<MovieDetails> callback, int id);
        //put here all methods that Interactor will implement and presenter will use to get data
    }


    interface IMoviePresenter extends IPresenter {
        //put here all methods that presenter will implement and view will use to initiate action
    }

    interface IMovieViewModel extends IViewModel {
        MovieDetails getMovieDetails();

        void setMovieDetails(MovieDetails movieDetails);

        LiveData<MovieDetails> getMovie(Movie movie);

        void addMovieToFavourites(MovieDetails movieDetails);

        void removeMovieFromFavourites(MovieDetails movieDetails);
        //put here all methods to get all livedata to the view   
    }

    interface IMovieRepository extends ICommonRepository {
        void insert(MovieDetails movieDetails);

        void delete(MovieDetails movieDetails);
        //put here all AsyncTasks and RoomDB calls that viewModelask to do so     
    }

}
