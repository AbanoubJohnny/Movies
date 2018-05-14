package abanoub.johnny.development.moviesapp.modules.searchresults.mvp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.contract.SearchResultsContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BaseViewModel;
import abanoub.johnny.development.moviesapp.mvp.models.data.callbacks.InteractorCallback;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.MoviesPage;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions.ProjectThrowable;
import abanoub.johnny.development.moviesapp.utils.NetworkUtil;
import abanoub.johnny.development.moviesapp.utils.events.NetworkSpeedObserver;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */
 
public class SearchResultsViewModel extends BaseViewModel<SearchResultsContract.ISearchResultsInteractor,SearchResultsContract.ISearchResultsRepository> implements SearchResultsContract.ISearchResultsViewModel {

    List<Movie> resultMovies;
    String searchString;
    int page = 1,totalpages = 1;

    @Override
    public String getSearchString() {
        return searchString;
    }

    @Override
    public boolean isMore(){
        return page<=totalpages;
    }
    @Override
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public List<Movie> getResultMovies() {
        return resultMovies;
    }

    private MutableLiveData<List<Movie>> liveResultMovies = new MutableLiveData<>();

    //popular movies////////////////////////////////////
    @Override
    public LiveData<List<Movie>> getResults() {
        if (page<=totalpages) {
            if (resultMovies == null || resultMovies.isEmpty()) {
                resultMovies = new ArrayList<>();
            }
            shwoLoading();
            getInteractor().getResults( getPopularCallback,searchString, page);
        }
        else {
            shwoMessage("no more");
        }
        return liveResultMovies;
    }

    private InteractorCallback<MoviesPage<ArrayList<Movie>>> getPopularCallback = new InteractorCallback<MoviesPage<ArrayList<Movie>>>() {
        @Override
        public void success(MoviesPage<ArrayList<Movie>>  moviesPage) {
            hideLoading();
            if (moviesPage!=null) {
                totalpages = moviesPage.totalPages;
                    page++;
                if (moviesPage.getResults()!=null&&!moviesPage.getResults().isEmpty()){
                    resultMovies.addAll(moviesPage.getResults());
                    liveResultMovies.postValue(resultMovies);
                }
            } else {
                shwoMessage("no movies");
            }
        }

        @Override
        public void error(List<ProjectThrowable> throwables) {
            hideLoading();
            for (ProjectThrowable projectThrowable : throwables) {
                if (projectThrowable.getErrorCode()== Constants.NO_NETWORK_CONNECTION||projectThrowable.getErrorCode()== Constants.WEAK_NETWORK_CONNECTION) {
                    resultsNetworkSpeedObserver.addObserver(resultsObserver);
                    NetworkUtil.StartAnalizing(resultsNetworkSpeedObserver);
                }
                shwoMessage(projectThrowable.getErrorMessages());
            }
        }
    };
    private NetworkSpeedObserver resultsNetworkSpeedObserver = new NetworkSpeedObserver();
    private Observer resultsObserver = new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            getInteractor().getResults(getPopularCallback,searchString,page);
            resultsNetworkSpeedObserver.deleteObserver(this);
        }
    };

    //popular movies////////////////////////////////////




}
