package abanoub.johnny.development.moviesapp.utils.customeviews.tablayout;

/**
 * Created by Abanoub Maher on 5/30/17.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;

public class CustomTabLayout extends TabLayout {
    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTabsFromPagerAdapter(@NonNull PagerAdapter adapter) {
        int languageFlag = MyApplication.sharedPreferencesUtils().getInt(Constants.LANGUAGEFLAG, 0);
        Typeface typeface;
        if (languageFlag == 0) {
             typeface = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Regular.ttf");
        }
        else {
            typeface = Typeface.createFromAsset(getContext().getAssets(), "DroidKufi-Regular.ttf");
        }

        this.removeAllTabs();

        ViewGroup slidingTabStrip = (ViewGroup) getChildAt(0);

        for (int i = 0, count = adapter.getCount(); i < count; i++) {
            Tab tab = this.newTab();
            this.addTab(tab.setText(adapter.getPageTitle(i)));
            AppCompatTextView view = (AppCompatTextView) ((ViewGroup)slidingTabStrip.getChildAt(i)).getChildAt(1);
            view.setTypeface(typeface);
        }
    }
}