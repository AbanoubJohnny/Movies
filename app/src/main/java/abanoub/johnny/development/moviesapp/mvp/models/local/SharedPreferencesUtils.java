package abanoub.johnny.development.moviesapp.mvp.models.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import abanoub.johnny.development.moviesapp.utils.Preconditions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import abanoub.johnny.development.moviesapp.utils.Preconditions;

/**
 * Created by Abanoub Maher on 2/5/2018.
 */

@ApplicationScope
public class SharedPreferencesUtils {

    private SharedPreferences pref;
    private Gson gson;

    @Inject
    public SharedPreferencesUtils(Context context, Gson gson) {
        this.pref = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = gson;
    }

    public SharedPreferences getPref() {
        return pref;
    }

    // Primitive

    public void putInt(String key, int value) {
        pref.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int def) {
        return pref.getInt(key, def);
    }

    public void putLong(String key, long value) {
        pref.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long def) {
        return pref.getLong(key, def);
    }

    public void putFloat(String key, float value) {
        pref.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key, float def) {
        return pref.getFloat(key, def);
    }

    public void putBoolean(String key, boolean value) {
        pref.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean def) {
        return pref.getBoolean(key, def);
    }

    public void putString(String key, String value) {
        pref.edit().putString(key, value).apply();
    }

    public String getString(String key, String def) {
        return pref.getString(key, def);
    }

    public Set<String> getStringSet(String key, HashSet<String> def) {
        return pref.getStringSet(key, def);
    }

    private void putStringSet(String key, HashSet<String> value) {
        pref.edit().putStringSet(key, value).apply();
    }

    public void remove(String key){
        pref.edit().remove(key).apply();
    }
    // Date

    public void putDate(String key, Date date) {
        pref.edit().putLong(key, date.getTime()).apply();
    }

    public Date getDate(String key) {
        return new Date(pref.getLong(key, 0));
    }

    // Gson

    public <T> void putObject(String key, T t) {
        pref.edit().putString(key, gson.toJson(t)).apply();
    }

    public <T> T getObject(String key, TypeToken<T> typeToken) {
        String objectString = pref.getString(key, null);
        if (objectString != null) {
            return gson.fromJson(objectString, typeToken.getType());
        }
        return null;
    }

    public void clearData() {
        pref.edit().clear().apply();
    }

    public boolean isLoggedUser() {
        return Preconditions.checkisNotNullOrEmpty(getAccessToken());
    }

    public String getAccessToken() {
        // FIXME: 2/5/2018 return from shared preference
        return "";
    }

    public boolean isAppIntroShown() {
        // FIXME: 2/5/2018
        return true;
    }

    public static final class PreferencesContract {
    }
}
