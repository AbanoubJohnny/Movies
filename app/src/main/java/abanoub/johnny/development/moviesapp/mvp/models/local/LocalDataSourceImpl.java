package abanoub.johnny.development.moviesapp.mvp.models.local;

import android.content.Context;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Abanoub Johnny 3/5/2018
 */

@ApplicationScope
public class LocalDataSourceImpl implements LocalDataSource {

    private final SharedPreferencesUtils mSharedPreferencesUtils;
    private Context mContext;
    private Gson mGson;

    @Inject
    public LocalDataSourceImpl(Context context, Gson gson) {
        this.mContext = context;
        mGson = gson;
        mSharedPreferencesUtils = new SharedPreferencesUtils(context, gson);
    }

    @Override
    public SharedPreferencesUtils getSharedPreferences() {
        return mSharedPreferencesUtils;
    }

    @Override
    public Observable<String> loadStringFromAsset(String fileName) {
        return Observable.create(subscriber -> {
            try {
                InputStream is = mContext.getAssets().open(fileName);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                subscriber.onNext(new String(buffer, "UTF-8"));
                subscriber.onComplete();
            } catch (IOException ex) {
                Timber.e(ex, "loadStringFromAsset: %s", "Error Load File");
                subscriber.onError(ex);
                subscriber.onComplete();
            }
        });
    }

    @Override
    public Gson getGson() {
        return mGson;
    }

    @Override
    public void onDestroy() {
        // FIXME: 4/25/2017
    }
}
