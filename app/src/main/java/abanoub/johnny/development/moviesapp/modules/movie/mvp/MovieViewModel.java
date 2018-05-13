package abanoub.johnny.development.moviesapp.modules.movie.mvp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import abanoub.johnny.development.moviesapp.modules.movie.mvp.contract.MovieContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BaseViewModel;
import abanoub.johnny.development.moviesapp.mvp.models.data.callbacks.InteractorCallback;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions.ProjectThrowable;
import abanoub.johnny.development.moviesapp.utils.NetworkUtil;
import abanoub.johnny.development.moviesapp.utils.events.NetworkSpeedObserver;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

public class MovieViewModel extends BaseViewModel<MovieContract.IMovieInteractor, MovieContract.IMovieRepository> implements MovieContract.IMovieViewModel {

    MovieDetails movieDetails;
    boolean isFavourite = false;
    Movie movie;
    MutableLiveData<MovieDetails> liveMovie = new MutableLiveData<>();

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Override
    public MovieDetails getMovieDetails() {
        return movieDetails;
    }

    @Override
    public void setMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    @Override
    public LiveData<MovieDetails> getMovie(Movie movie) {
        this.movie = movie;
        shwoLoading();
        getInteractor().getMovie(getMovieDetailsCallback,movie.getId());
        return liveMovie;
    }

    private InteractorCallback<MovieDetails> getMovieDetailsCallback = new InteractorCallback<MovieDetails>() {
        @Override
        public void success(MovieDetails movieDetailsResponse) {
            hideLoading();
            if (movieDetailsResponse!=null) {
                movieDetails = movieDetailsResponse;
                liveMovie.postValue(movieDetails);
            } else {
                shwoMessage("no branches");
            }
        }

        @Override
        public void error(List<ProjectThrowable> throwables) {
            hideLoading();
            for (ProjectThrowable projectThrowable : throwables) {
                if (projectThrowable.getErrorCode()== Constants.NO_NETWORK_CONNECTION||projectThrowable.getErrorCode()== Constants.WEAK_NETWORK_CONNECTION) {
                    getMovieNetworkSpeedObserver.addObserver(movieObserver);
                    NetworkUtil.StartAnalizing(getMovieNetworkSpeedObserver);
                }
                shwoMessage(projectThrowable.getErrorMessages());
            }
        }
    };
    private NetworkSpeedObserver getMovieNetworkSpeedObserver = new NetworkSpeedObserver();
    private Observer movieObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            getInteractor().getMovie(getMovieDetailsCallback,movie.getId());
            getMovieNetworkSpeedObserver.deleteObserver(this);
        }
    };

    @Override
    public void addMovieToFavourites(MovieDetails movieDetails)
    {
        getRepository().insert(movieDetails);
        isFavourite = true;
    }
    @Override
    public void removeMovieFromFavourites(MovieDetails movieDetails)
    {
        getRepository().delete(movieDetails);
        isFavourite = false;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}    
