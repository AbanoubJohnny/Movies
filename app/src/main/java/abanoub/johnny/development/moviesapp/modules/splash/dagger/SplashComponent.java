package abanoub.johnny.development.moviesapp.modules.splash.dagger;

import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.SplashActivity;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.SplashViewModel;

import abanoub.johnny.development.moviesapp.modules.splash.mvp.SplashActivity;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.SplashViewModel;
import dagger.Component;

@ActivityScope
@Component(modules = {SplashModule.class}, dependencies = ApplicationComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);

    void inject(SplashViewModel viewModel);
}
