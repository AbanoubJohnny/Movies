package abanoub.johnny.development.moviesapp.mvp.bases;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by Abanoub Maher on 5/2/17.
 */

public interface IPresenter {

    void initiateObserver();

    void onViewReady();

    BasePresenterInjector getInjector();

    void activityResult(int requestCode, int resultCode, Intent data);

    void onSaveInstanceState(Bundle outState);

    void requestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    void call_action();

    void pause();

    void start();

    void stop();

    void resume();

    void destroy();

    void showProgressDialog(String title, String body);

    void hideProgressDialog();


}
