package  abanoub.johnny.development.moviesapp.modules.searchresults.mvp.contract;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import abanoub.johnny.development.moviesapp.mvp.bases.ICommonIneractor;
import abanoub.johnny.development.moviesapp.mvp.bases.ICommonRepository;
import abanoub.johnny.development.moviesapp.mvp.bases.IPresenter;
import abanoub.johnny.development.moviesapp.mvp.bases.IView;
import abanoub.johnny.development.moviesapp.mvp.bases.IViewModel;
import abanoub.johnny.development.moviesapp.mvp.models.data.callbacks.InteractorCallback;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.MoviesPage;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

public interface SearchResultsContract {

    interface ISearchResultsView extends IView {
        //put here all methods that view will implement and presenter will use to update the view

    }
    interface ISearchResultsInteractor  extends ICommonIneractor {
        void getResults(InteractorCallback<MoviesPage<ArrayList<Movie>>> callback, String searchString, int page);
        //put here all methods that Interactor will implement and presenter will use to get data
    }


    interface ISearchResultsPresenter extends IPresenter {
        //put here all methods that presenter will implement and view will use to initiate action
    }

    interface ISearchResultsViewModel extends IViewModel {
        String getSearchString();

        boolean isMore();

        void setSearchString(String searchString);

        List<Movie> getResultMovies();

        //popular movies////////////////////////////////////
        LiveData<List<Movie>> getResults();
        //put here all methods to get all livedata to the view   
    }

    interface ISearchResultsRepository extends ICommonRepository {
        //put here all AsyncTasks and RoomDB calls that viewModelask to do so     
    }

}
