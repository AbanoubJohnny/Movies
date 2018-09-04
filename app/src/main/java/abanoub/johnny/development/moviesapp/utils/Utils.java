package abanoub.johnny.development.moviesapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by Abanoub Maher on 5/10/2017.
 */

public final class Utils {
    public static int parseInt(String value) {
        try {
            double aDouble = Double.parseDouble(value);
            return (int) aDouble;
        } catch (NumberFormatException e) {
            Log.e(e.getMessage(), "parseInt: ");
            return 0;
        } catch (Exception e) {
            Log.e(e.getMessage(), "parseInt: ");
            return 0;
        }
    }

    public static int dpToPx(float dp, Context context) {
        return dpToPx(dp, context.getResources());
    }

    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }
    public static float PxToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
