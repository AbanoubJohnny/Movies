package abanoub.johnny.development.moviesapp.application.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;


/**
 * Created by Abanoub Johnny on 3/8/2018.
 */

public class LocaleManager {
    public static Context setLocale(Context c) {
        return setNewLocale(c, getLanguage(c));
    }

    public static Context setNewLocale(Context c, String language) {
        persistLanguage(c, language);
        return updateResources(c, language);
    }

    public static String getLanguage(Context c) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        return sharedPreferences.getString(Constants.LANGUAGE, Constants.ENGLISH);
    }

    public static Context persistLanguage(Context c, String language) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        sharedPreferences.edit().putString(Constants.LANGUAGE, language).apply();
        return updateResources(c, language);
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language,"EG");
        Locale.setDefault(locale);
        System.setProperty("user.language",language);


        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT < 17) {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        } else {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }


}