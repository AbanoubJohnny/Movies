package abanoub.johnny.development.moviesapp.mvp.bases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.utils.LanguageUtils;
import abanoub.johnny.development.moviesapp.utils.UtiltiesMethods;

import abanoub.johnny.development.moviesapp.utils.LanguageUtils;
import abanoub.johnny.development.moviesapp.utils.UtiltiesMethods;


/**
 * Created by Abanoub Maher on 8/18/17.
 */

public class BaseFragmentActivity extends FragmentActivity {
  String language;
  int flagLang;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
      if(MyApplication.sharedPreferencesUtils().getString(Constants.LANGUAGE, Constants.ENGLISH).matches(Constants.ARABIC)) {
          UtiltiesMethods.setLocale(this, Constants.ARABIC);
      }
      else if(MyApplication.sharedPreferencesUtils().getString(Constants.LANGUAGE, Constants.ENGLISH).matches(Constants.ENGLISH)) {
          UtiltiesMethods.setLocale(this, Constants.ARABIC);
      }

      super.onCreate(savedInstanceState);
      this.overridePendingTransition(R.anim.fade_in,
              R.anim.fade_out);

  }

    @Override
    protected void attachBaseContext(Context base) {
        checkForNewUser();
         language = MyApplication.sharedPreferencesUtils().getString(Constants.LANGUAGE, "");
         flagLang = MyApplication.sharedPreferencesUtils().getInt(Constants.LANGUAGEFLAG, -1);

        super.attachBaseContext(LanguageUtils.changeLoc(base, flagLang, language));
    }

    private void checkForNewUser(){
    }

    public void startActivityFromClass(Class<?> klass) {
        Intent intent = getIntent(klass);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public Intent getIntent(Class<?> klass) {
        return new Intent(this, klass);
    }

    public BaseFragmentActivity getCurrentActivity() {
        return this;
    }


}
