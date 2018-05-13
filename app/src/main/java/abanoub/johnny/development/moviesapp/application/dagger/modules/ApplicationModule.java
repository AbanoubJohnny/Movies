package abanoub.johnny.development.moviesapp.application.dagger.modules;


import android.app.Application;
import android.content.Context;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Abanoub Johnny 3/5/2018
 */

@Module
public class ApplicationModule {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final Application mApplication;

    //this class mainly deffined to make all the instances of the app like Gson builder to be singletone instance ??
    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationScope
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(DATE_FORMAT);
        return gsonBuilder.create();
    }

}
