package abanoub.johnny.development.moviesapp.application.dagger.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSourceImpl;
import abanoub.johnny.development.moviesapp.mvp.models.local.SharedPreferencesUtils;
import com.google.gson.Gson;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Abanoub Johnny 3/5/2018
 */
@Module
public class LocalDataSourceModule {
    @Provides
    @ApplicationScope
    LocalDataSource provideLocalStorage(Context context, Gson gson) {
        return new LocalDataSourceImpl(context, gson);
    }

    @Provides
    @ApplicationScope
    SharedPreferencesUtils provideSharedPreferencesUtils(LocalDataSource localDataSource) {
        return localDataSource.getSharedPreferences();
    }

    @Provides
    @ApplicationScope
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, Constants.DB_NAME).build();
    }
}
