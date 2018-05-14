package abanoub.johnny.development.moviesapp.modules.searchresults.mvp;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.contract.SearchResultsContract;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
public class SearchResultsRepository implements SearchResultsContract.ISearchResultsRepository {
    @Inject
    public SearchResultsRepository(AppDatabase appDatabase){
    }
 @Override
    public void onDestroy() {
        
    }
}
