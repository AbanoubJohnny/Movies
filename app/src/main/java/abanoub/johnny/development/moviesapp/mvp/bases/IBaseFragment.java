package abanoub.johnny.development.moviesapp.mvp.bases;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by abanoubjohnny on 5/2/17.
 */

public interface IBaseFragment {
    void showMessage(int messageResourceId);

    void showMessage(String message);

    String getString(int id);

    String getString(int resId, Object... formatArgs);

    void startActivity(Intent intent);

    void startActivity(Class<?> klass);

    void startActivityWithFinish(Class<?> klass);

    void startActivity(Class<?> klass, Bundle args);

    void startActivityForResult(Class<?> klass, int requestCode);

    void startActivityForResult(Intent intent, int requestCode);

    void setUserVisibleHint(boolean isVisibleToUser);

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    void startActivityWithFinish(Class<?> klass, Bundle args);

    Intent getIntent(Class<?> klass);

    void finishActivity();

    void finishActivity(int result);

    void finishActivity(int resultOk, Intent createIntent);

    void handleError(int resourceId);

    Activity getActivity();

    void requestPermission(String[] permissions, int requestCode);

    void errorGettingRequests();

    void showProgressDialog(String title, String body);

    void hideProgressDialog();
}
