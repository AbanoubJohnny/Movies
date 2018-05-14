
package  abanoub.johnny.development.moviesapp.modules.searchresults.dagger;

import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.SearchResultsActivity;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.SearchResultsViewModel;
import dagger.Component;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
@Component(modules = {SearchResultsModule.class}, dependencies = ApplicationComponent.class)
public interface SearchResultsComponent {
    void inject(SearchResultsActivity activity);
    void inject(SearchResultsViewModel viewModel);
}
