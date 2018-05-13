package abanoub.johnny.development.moviesapp.modules.home.dagger;

import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.home.mvp.HomeActivity;
import abanoub.johnny.development.moviesapp.modules.home.mvp.HomeViewModel;
import dagger.Component;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
@Component(modules = {HomeModule.class}, dependencies = ApplicationComponent.class)
public interface HomeComponent {
    void inject(HomeActivity activity);

    void inject(HomeViewModel viewModel);
}
