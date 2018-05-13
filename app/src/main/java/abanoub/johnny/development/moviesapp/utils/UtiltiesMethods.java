package abanoub.johnny.development.moviesapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;

import java.util.Locale;


/**
 * Created by Abanoub Maher on 7/17/17.
 */

public class UtiltiesMethods {
    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = null;
        ConnectivityManager connectivityManager;
        if (context != null) {
            connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            }
        }
        return networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    public static String convertToArabic(String value) {
        String newValue = (((((((((((((value)
                .replaceAll("1", "١")).replaceAll("2", "٢"))
                .replaceAll("3", "٣")).replaceAll("4", "٤"))
                .replaceAll("5", "٥")).replaceAll("6", "٦"))
                .replaceAll("7", "٧")).replaceAll("8", "٨"))
                .replaceAll("9", "٩")).replaceAll("0", "٠"))
                .replaceAll("PM", "م")).replaceAll("AM", "ص"));
        return newValue;
    }

    public static String convertToEnglish(String value) {
        String newValue = (((((((((((value)
                .replaceAll("١", "1")).replaceAll("٢", "2"))
                .replaceAll("٣", "3")).replaceAll("٤", "4"))
                .replaceAll("٥", "5")).replaceAll("٦", "6"))
                .replaceAll("٧", "7")).replaceAll("٨", "8"))
                .replaceAll("٩", "9")).replaceAll("٠", "0"));
        return newValue;
    }

    public static Boolean isArabicLanguage(Context context) {
        int languageFlag = MyApplication.sharedPreferencesUtils().getInt(Constants.LANGUAGEFLAG, 0);
        return languageFlag == 1;
    }

    public static boolean isWeakConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        NetworkInfo.DetailedState detailedState = info.getDetailedState();
        boolean check = false;
        if (detailedState == NetworkInfo.DetailedState.VERIFYING_POOR_LINK) {
            check = true;
        }
        return check;
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static boolean isArabic(String text) {
        String textWithoutSpace = text.trim().replaceAll(" ", ""); //to ignore whitepace
        for (int i = 0; i < textWithoutSpace.length(); ) {
            int c = textWithoutSpace.codePointAt(i);
            //range of arabic chars/symbols is from 0x0600 to 0x06ff
            //the arabic letter 'لا' is special case having the range from 0xFE70 to 0xFEFF
            if (c >= 0x0600 && c <= 0x06FF || (c >= 0xFE70 && c <= 0xFEFF))
                i += Character.charCount(c);
            else
                return false;

        }
        return true;
    }

    public static void setLocale(Activity activity, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getApplicationContext().getResources().updateConfiguration(config, null);
    }

}
