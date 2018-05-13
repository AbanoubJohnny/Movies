package abanoub.johnny.development.moviesapp.modules.home.mvp.contract;

import android.arch.lifecycle.LiveData;

import java.util.List;

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

public interface HomeContract {

    interface IHomeView extends IView {
        //put here all methods that view will implement and presenter will use to update the view

    }

    interface IHomeInteractor extends ICommonIneractor
    {
    void getPopular(InteractorCallback<List<Movie>> callback, int page);

        void getToprated(InteractorCallback<List<Movie>> callback, int page);
        //put here all methods that Interactor will implement and presenter will use to get data
    }


    interface IHomePresenter extends IPresenter {
        //put here all methods that presenter will implement and view will use to initiate action
    }

    interface IHomeViewModel extends IViewModel {
        List<Movie> getTopratedMovies();

        List<Movie> getPopularMovies();

        List<MovieDetails> getMyFavouriteMovies();

        //popular movies////////////////////////////////////
        LiveData<List<Movie>> getPopular();

        //popular movies////////////////////////////////////
        LiveData<List<Movie>> getTopRated();

        LiveData<List<MovieDetails>> getMovies();
        //put here all methods to get all livedata to the view   
    }

    interface IHomeRepository extends ICommonRepository {
        LiveData<List<MovieDetails>> getAllMovies();
        //put here all AsyncTasks and RoomDB calls that viewModelask to do so     
    }

}
