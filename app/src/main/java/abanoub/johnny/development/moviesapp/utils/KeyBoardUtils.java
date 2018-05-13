package abanoub.johnny.development.moviesapp.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Abanoub Maher on 5/10/17.
 */

public class KeyBoardUtils {
    public static void hideKeyboard(Activity activity) {
        if (isKeyboardVisible(activity)) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }

    public static boolean isKeyboardVisible(Activity activity) {
        ///This method is based on the one described at http://stackoverflow.com/questions/4745988/how-do-i-detect-if-software-keyboard-is-visible-on-android-device
        Rect r = new Rect();
        View contentView = activity.findViewById(android.R.id.content);
        contentView.getWindowVisibleDisplayFrame(r);
        int screenHeight = contentView.getRootView().getHeight();

        int keypadHeight = screenHeight - r.bottom;

        return
                (keypadHeight > screenHeight * 0.15);
    }
}
