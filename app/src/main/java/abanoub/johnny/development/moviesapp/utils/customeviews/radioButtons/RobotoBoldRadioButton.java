package abanoub.johnny.development.moviesapp.utils.customeviews.radioButtons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.utils.FontCache;

import abanoub.johnny.development.moviesapp.utils.FontCache;

/**
 * Created by Abanoub Maher on 5/25/17.
 */

public class RobotoBoldRadioButton extends android.support.v7.widget.AppCompatRadioButton {
        public RobotoBoldRadioButton(Context context) {
            super(context);
            applyCustomFont(context);
        }

        public RobotoBoldRadioButton(Context context, AttributeSet attrs) {
            super(context, attrs);

            applyCustomFont(context);
        }

        public RobotoBoldRadioButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            applyCustomFont(context);
        }

        private void applyCustomFont(Context context) {
            int languageFlag = MyApplication.sharedPreferencesUtils().getInt(Constants.LANGUAGEFLAG, 0);
            Typeface customFont;
            if (languageFlag == 0) {
                customFont = FontCache.getTypeface("Roboto-Bold.ttf", context);
            }
            else {
                customFont = FontCache.getTypeface("DroidKufi-Bold.ttf", context);
                setLineSpacing(-10, 1);
            }
            setTypeface(customFont);
        }
    }
