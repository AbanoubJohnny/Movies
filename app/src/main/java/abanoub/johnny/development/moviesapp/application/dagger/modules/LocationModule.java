package abanoub.johnny.development.moviesapp.application.dagger.modules;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.Geocoding;
import com.patloew.rxlocation.RxLocation;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import timber.log.Timber;

@Module
public class LocationModule {

    @Provides
    @ApplicationScope
    LocationRequest provideLocationRequest() {
        return LocationRequest.create() //standard GMS LocationRequest
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000);
    }

    @Provides
    @ApplicationScope
    RxLocation provideRxLocation(Context context) {
        return new RxLocation(context);
    }

    @Provides
    @ApplicationScope
    Geocoding provideGeocoding(RxLocation rxLocation) {
        return rxLocation.geocoding();
    }

    @Provides
    @ApplicationScope
    Observable<Location> provideLocationObservable(RxLocation rxLocation,
                                                   LocationRequest locationRequest,Context context) {
        //noinspection MissingPermission
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: ActivityCompat#requestPermissions
            return null;
        }
        else {
            return rxLocation.location()
                    .updates(locationRequest)
                    .filter(location -> {
                        Timber.d("provideLocationObservable() called with: location = " + location.toString());
                        return location.getAccuracy() < 70;
                    });
        }
    }
}
