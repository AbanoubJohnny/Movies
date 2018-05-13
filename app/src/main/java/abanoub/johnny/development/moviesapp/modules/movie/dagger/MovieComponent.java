package abanoub.johnny.development.moviesapp.modules.movie.dagger;

import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.MovieActivity;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.MovieViewModel;
import dagger.Component;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
@Component(modules = {MovieModule.class}, dependencies = ApplicationComponent.class)
public interface MovieComponent {
    void inject(MovieActivity activity);

    void inject(MovieViewModel viewModel);
}
