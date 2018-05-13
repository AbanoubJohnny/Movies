package abanoub.johnny.development.moviesapp.modules.home.mvp;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import abanoub.johnny.development.moviesapp.modules.home.mvp.contract.HomeContract;
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

public class HomeViewModel extends BaseViewModel<HomeContract.IHomeInteractor, HomeContract.IHomeRepository> implements HomeContract.IHomeViewModel {

    private List<Movie> topratedMovies,popularMovies ;
    private List<MovieDetails> myFavouriteMovies ;

    @Override
    public List<Movie> getTopratedMovies() {
        return topratedMovies;
    }

    @Override
    public List<Movie> getPopularMovies() {
        return popularMovies;
    }
    @Override
    public List<MovieDetails> getMyFavouriteMovies() {
        return myFavouriteMovies;
    }

    private MutableLiveData<List<Movie>> livePopularMovies = new MutableLiveData<>(),livetopratedMovies = new MutableLiveData<>();

    //popular movies////////////////////////////////////
    @Override
    public LiveData<List<Movie>> getPopular() {
        if (popularMovies==null||popularMovies.isEmpty()){
            popularMovies = new ArrayList<>();
            shwoLoading();
        }
        getInteractor().getPopular(getPopularCallback,1);
        return livePopularMovies;
    }

    private InteractorCallback<List<Movie>> getPopularCallback = new InteractorCallback<List<Movie>>() {
        @Override
        public void success(List<Movie> movies) {
            hideLoading();
            if (!movies.isEmpty()) {
                popularMovies.addAll(movies);
                livePopularMovies.postValue(popularMovies);
            } else {
                shwoMessage("no branches");
            }
        }

        @Override
        public void error(List<ProjectThrowable> throwables) {
            hideLoading();
            for (ProjectThrowable projectThrowable : throwables) {
                if (projectThrowable.getErrorCode()== Constants.NO_NETWORK_CONNECTION||projectThrowable.getErrorCode()== Constants.WEAK_NETWORK_CONNECTION) {
                    popularnNetworkSpeedObserver.addObserver(popularObserver);
                    NetworkUtil.StartAnalizing(popularnNetworkSpeedObserver);
                }
                shwoMessage(projectThrowable.getErrorMessages());
            }
        }
    };
    private NetworkSpeedObserver popularnNetworkSpeedObserver = new NetworkSpeedObserver();
    private Observer popularObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            getInteractor().getPopular(getPopularCallback,1);
            popularnNetworkSpeedObserver.deleteObserver(this);
        }
    };

    //popular movies////////////////////////////////////



    //popular movies////////////////////////////////////
    @Override
    public LiveData<List<Movie>> getTopRated() {
        if (topratedMovies==null||topratedMovies.isEmpty()){
            topratedMovies = new ArrayList<>();
            shwoLoading();
        }
        getInteractor().getToprated(getTopRatedCallback,1);
        return livetopratedMovies;
    }

    private InteractorCallback<List<Movie>> getTopRatedCallback = new InteractorCallback<List<Movie>>() {
        @Override
        public void success(List<Movie> movies) {
            hideLoading();
            if (!movies.isEmpty()) {
                topratedMovies.addAll(movies);
                livetopratedMovies.postValue(popularMovies);
            } else {
                shwoMessage("no branches");
            }
        }

        @Override
        public void error(List<ProjectThrowable> throwables) {
            hideLoading();
            for (ProjectThrowable projectThrowable : throwables) {
                if (projectThrowable.getErrorCode()== Constants.NO_NETWORK_CONNECTION||projectThrowable.getErrorCode()== Constants.WEAK_NETWORK_CONNECTION) {
                    topratedNetworkSpeedObserver.addObserver(topratedObserver);
                    NetworkUtil.StartAnalizing(topratedNetworkSpeedObserver);
                }
                shwoMessage(projectThrowable.getErrorMessages());
            }
        }
    };
    private NetworkSpeedObserver topratedNetworkSpeedObserver = new NetworkSpeedObserver();
    private Observer topratedObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            getInteractor().getToprated(getTopRatedCallback,1);
            topratedNetworkSpeedObserver.deleteObserver(this);
        }
    };

    //popular movies////////////////////////////////////

    @Override
    public LiveData<List<MovieDetails>> getMovies(){
        return getRepository().getAllMovies();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
