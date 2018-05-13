package abanoub.johnny.development.moviesapp.application.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import abanoub.johnny.development.moviesapp.application.dagger.Injector;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.mvp.models.local.SharedPreferencesUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Abanoub Maher on 5/2/17.
 */

public class MyApplication extends Application {
    private final static String TAG = "Application";
    ApplicationComponent applicationComponent;
    public static Context myApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
        if (sharedPreferencesUtils().getString(Constants.LANGUAGE, Constants.ENGLISH).matches(Constants.ARABIC)) {
            overrideFont(getApplicationContext(), "SERIF", "DroidKufi-Regular.ttf");
        } else {
            overrideFont(getApplicationContext(), "SERIF", "Roboto-Regular.ttf");
        }
        myApplicationContext = getApplicationContext();
    }

    public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {

        final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap<String, Typeface>();
            newMap.put("serif", customFontTypeface);
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
                defaultFontTypefaceField.setAccessible(true);
                defaultFontTypefaceField.set(null, customFontTypeface);
            } catch (Exception e) {
                Log.e(MyApplication.class.getSimpleName(), "Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride);
            }
        }
    }

    public void initializeApplicationComponent() {
        applicationComponent = Injector.INSTANCE.initializeAppComponent(this);
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
        Log.d(TAG, "attachBaseContext");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: " + newConfig.locale.getLanguage());
    }
    public static SharedPreferencesUtils sharedPreferencesUtils(){
        return Injector.INSTANCE.getAppComponent().exposeLocalDataSource().getSharedPreferences();
    }

    public static AppDatabase getAppDatabase(){
        return Injector.INSTANCE.getAppComponent().exposeAppDatabase();
    }

}
