package abanoub.johnny.development.moviesapp.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Abanoub Johnny on 11/3/2017.
 */

public class ServiceTools {
    private static String LOG_TAG = ServiceTools.class.getName();

    public static boolean isServiceRunning(Context context, Class<?> serviceClass){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
