package abanoub.johnny.development.moviesapp.application.dagger;


import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.application.dagger.components.DaggerApplicationComponent;
import abanoub.johnny.development.moviesapp.application.dagger.modules.ApplicationModule;
import abanoub.johnny.development.moviesapp.application.dagger.modules.LocalDataSourceModule;
import abanoub.johnny.development.moviesapp.application.dagger.modules.LocationModule;
import abanoub.johnny.development.moviesapp.application.dagger.modules.NetworkModule;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;

/**
 * Created by Abanoub Johnny 3/5/2018
 */

public enum Injector {
    INSTANCE;

    private ApplicationComponent applicationComponent;

    public ApplicationComponent initializeAppComponent(MyApplication application) {
        //DaggerApplicationComponent
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .networkModule(new NetworkModule(application))
                .localDataSourceModule(new LocalDataSourceModule())
                .locationModule(new LocationModule())
                .build();
        return applicationComponent;
    }

    public ApplicationComponent getAppComponent() {
        return applicationComponent;
    }
}
