package abanoub.johnny.development.moviesapp.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;

import java.util.Locale;

/**
 * Created by Abanoub Maher on 8/18/17.
 */

public class LanguageUtils {

  public static Context changeLoc(Context context, int flagLang, String language) {
    if(flagLang == -1)
      return context;
    Resources res = context.getResources();
    DisplayMetrics dm = res.getDisplayMetrics();
    Configuration config = res.getConfiguration();

    if (language.equals("")) {

      String currentLanguage = Locale.getDefault().getLanguage();
      if (currentLanguage.equals(Constants.ENGLISH)) {
        language = Constants.ENGLISH;
        flagLang = 0;
      } else {
        language = Constants.ARABIC;
        flagLang = 1;
      }
    }

    Locale locale = null;
    if (flagLang == 0) {
      language = Constants.ENGLISH;
      locale = new Locale(language);
    } else {
      language = Constants.ARABIC;
      locale = new Locale(language);
    }

    Locale.setDefault(locale);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      setSystemLocale(config, locale);
    } else {
      setSystemLocaleLegacy(config, locale);
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      Context resc = context.createConfigurationContext(config);
      res = resc.getResources();
      return resc;
    } else {
      res.updateConfiguration(config, dm);
    }
    return context;
  }

  @SuppressWarnings("deprecation")
  public static Locale getSystemLocaleLegacy(Configuration config) {
    return config.locale;
  }

  @TargetApi(Build.VERSION_CODES.N) public static Locale getSystemLocale(Configuration config) {
    return config.getLocales().get(0);
  }

  @SuppressWarnings("deprecation")
  public static void setSystemLocaleLegacy(Configuration config, Locale locale) {
    config.locale = locale;
  }

  @TargetApi(Build.VERSION_CODES.N)
  public static void setSystemLocale(Configuration config, Locale locale) {
    config.setLocale(locale);
  }
}
