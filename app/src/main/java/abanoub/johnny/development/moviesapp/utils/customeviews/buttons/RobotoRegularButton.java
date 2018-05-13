package abanoub.johnny.development.moviesapp.utils.customeviews.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.utils.FontCache;

import abanoub.johnny.development.moviesapp.utils.FontCache;

/**
 * Created by Abanoub Maher on 5/25/17.
 */

public class RobotoRegularButton extends android.support.v7.widget.AppCompatButton {
        public RobotoRegularButton(Context context) {
            super(context);
            applyCustomFont(context);
        }

        public RobotoRegularButton(Context context, AttributeSet attrs) {
            super(context, attrs);

            applyCustomFont(context);
        }

        public RobotoRegularButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            applyCustomFont(context);
        }

        private void applyCustomFont(Context context) {
            int languageFlag = MyApplication.sharedPreferencesUtils().getInt(Constants.LANGUAGEFLAG, 0);
            Typeface customFont;
            if (languageFlag == 0) {
                customFont = FontCache.getTypeface("Roboto-Regular.ttf", context);
            }
            else {
                customFont = FontCache.getTypeface("DroidKufi-Regular.ttf", context);
                setLineSpacing(0, 1);
                setIncludeFontPadding(false);
                setTextSize(TypedValue.COMPLEX_UNIT_PX ,getTextSize() - 6);
            }
            setTypeface(customFont);
        }
    }
