package  abanoub.johnny.development.moviesapp.modules.searchresults.mvp;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.contract.SearchResultsContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BasePresenter;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */
 
@ActivityScope
public class SearchResultsPresenter extends BasePresenter<SearchResultsContract.ISearchResultsView> implements SearchResultsContract.ISearchResultsPresenter {
    private int callsCounter;

    @Inject
    public SearchResultsPresenter(SearchResultsContract.ISearchResultsView rootView) {
        super(rootView);
    }

    @Override
    public void onViewReady() {

    }

}
