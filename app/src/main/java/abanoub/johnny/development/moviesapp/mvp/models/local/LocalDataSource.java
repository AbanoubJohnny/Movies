package abanoub.johnny.development.moviesapp.mvp.models.local;

import com.google.gson.Gson;

import io.reactivex.Observable;

/**
 * Created by Abanoub Johnny 3/5/2018
 */

public interface LocalDataSource {
    SharedPreferencesUtils getSharedPreferences();

    Observable<String> loadStringFromAsset(String fileName);

    Gson getGson();

    void onDestroy();
}
