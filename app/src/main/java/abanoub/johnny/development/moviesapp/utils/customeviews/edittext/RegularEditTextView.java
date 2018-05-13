package abanoub.johnny.development.moviesapp.utils.customeviews.edittext;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.utils.FontCache;

import abanoub.johnny.development.moviesapp.utils.FontCache;

/**
 * Created by Abanoub Maher on 5/25/17.
 */

public class RegularEditTextView extends EditText {
        public RegularEditTextView(Context context) {
            super(context);
            applyCustomFont(context);
        }

        public RegularEditTextView(Context context, AttributeSet attrs) {
            super(context, attrs);

            applyCustomFont(context);
        }

        public RegularEditTextView(Context context, AttributeSet attrs, int defStyle) {
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
        }
        setTypeface(customFont);
    }
    }
