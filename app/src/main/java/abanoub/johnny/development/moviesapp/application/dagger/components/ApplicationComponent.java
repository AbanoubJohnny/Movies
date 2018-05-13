package abanoub.johnny.development.moviesapp.application.dagger.components;

import android.content.Context;
import android.location.Location;

import abanoub.johnny.development.moviesapp.application.dagger.modules.ApplicationModule;
import abanoub.johnny.development.moviesapp.application.dagger.modules.LocalDataSourceModule;
import abanoub.johnny.development.moviesapp.application.dagger.modules.LocationModule;
import abanoub.johnny.development.moviesapp.application.dagger.modules.NetworkModule;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import com.google.android.gms.location.LocationRequest;
import com.google.gson.Gson;
import com.patloew.rxlocation.Geocoding;
import com.patloew.rxlocation.RxLocation;

import abanoub.johnny.development.moviesapp.application.dagger.modules.ApplicationModule;
import abanoub.johnny.development.moviesapp.application.dagger.modules.LocalDataSourceModule;
import abanoub.johnny.development.moviesapp.application.dagger.modules.LocationModule;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import dagger.Component;
import io.reactivex.Observable;
import retrofit2.Retrofit;


@ApplicationScope
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        LocalDataSourceModule.class,
        LocationModule.class
})
public interface ApplicationComponent {

    Context exposeContext();

    Gson exposeGson();

    LocationRequest exposeLocationRequest();

    RxLocation exposeRxLocation();

    Geocoding exposeGeocoding();

    Observable<Location> exposeLocationObservable();

    Retrofit exposeRetrofit();

    LocalDataSource exposeLocalDataSource();

    DataCall exposeDatacall();

    AppDatabase exposeAppDatabase();


}
