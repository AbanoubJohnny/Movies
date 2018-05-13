package abanoub.johnny.development.moviesapp.utils;

import android.util.Log;

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
}
