package  abanoub.johnny.development.moviesapp.modules.searchresults.dagger;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.SearchResultsPresenter;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.SearchResultsRepository;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.contract.SearchResultsContract;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.interactors.SearchResultsInteractor;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@Module
public class SearchResultsModule {
    private SearchResultsContract.ISearchResultsView view;

    public SearchResultsModule(SearchResultsContract.ISearchResultsView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    SearchResultsContract.ISearchResultsView provideSplashView() {
        return this.view;
    }

    @Provides
    @ActivityScope
    SearchResultsContract.ISearchResultsInteractor provideSearchResultsInteractor(
            DataCall dataCall, LocalDataSource locatDataSource) {
        return new SearchResultsInteractor(dataCall, locatDataSource);
    }
    @Provides
    @ActivityScope
    SearchResultsPresenter provideSearchResultsPresenter(
            SearchResultsContract.ISearchResultsView view) {
        return new SearchResultsPresenter(view);
    }
    @Provides
    @ActivityScope
    SearchResultsContract.ISearchResultsRepository provideSearchResultsRepository(AppDatabase appDatabase) {
        return new SearchResultsRepository(appDatabase);
    }
}