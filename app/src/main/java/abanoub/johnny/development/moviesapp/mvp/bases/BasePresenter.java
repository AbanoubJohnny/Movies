package abanoub.johnny.development.moviesapp.mvp.bases;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.application.dagger.Injector;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.mvp.models.local.SharedPreferencesUtils;
import abanoub.johnny.development.moviesapp.utils.ProgressDialogNew;


/**
 * Created by abanoubjohnny on 5/2/17.
 */

public abstract class BasePresenter<V extends IView> implements IPresenter,LifecycleObserver {

    private Context context;

    private V mRootView;

    private ProgressDialog mProgressDialog;

    private Gson gson;

    ProgressDialogNew progressDialogNew;

    BasePresenterInjector basePresenterInjector;

    public BasePresenter(V rootView) {
        super();
        gson = new Gson();
        this.mRootView = rootView;
    }

    public BasePresenter() {
        this(null);
    }
    @Override
    public BasePresenterInjector getInjector() {
        return basePresenterInjector;
    }

    protected Intent getIntent(Class<?> classType) {
        return getView().getIntent(classType);
    }

    protected void finishActivity(int result) {
        if (getView() != null)
            getView().finishActivity(result);
    }

    protected void finish() {
        if (getView() != null)
            getView().finishActivity();
    }

    protected void showMessage(int messageResourceId) {
        if (getView() != null)
            getView().showMessage(messageResourceId);
    }

    protected void showMessage(String message) {
        if (getView() != null)
            getView().showMessage(message);
    }

    protected String getString(int resourceId) {
        if (getView() != null)
            if (getView().getMyActivity() != null)
                return getView().getMyActivity().getString(resourceId);
        return "";
    }

    protected String getString(int resourceId, Object... formatArgs) {
        if (getView() != null)
            if (getView().getMyActivity() != null)
                return getView().getMyActivity().getString(resourceId, formatArgs);
        return "";
    }

    protected void startActivity(Class<?> classType) {
        if (getView() != null)
            getView().startActivity(classType);
    }

    protected void startActivity(Intent intent) {
        if (getView() != null)
            getView().startActivity(intent);
    }

    protected void startActivityForResult(Class<?> classType, int requestCode) {
        Intent intent = getIntent(classType);
        if (getView() != null)
            getView().startActivityForResult(intent, requestCode);
    }

    protected void startActivityForResult(Intent intent, int requestCode) {
        if (getView() != null)
            getView().startActivityForResult(intent, requestCode);
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override
    public void requestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    call_action();
                    Log.d("TAG", "Call Permission Granted");
                } else {
                    Log.d("TAG", "Call Permission Not Granted");
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void call_action() {

        String phoneNum = sharedPreferencesUtils().getString(Constants.PHONE_NUMBER, "1");
        if (ContextCompat.checkSelfPermission(getView().getMyActivity(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            getView().requestPermission(new String[]{Manifest.permission.CALL_PHONE}, 123);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNum));
            getView().getMyActivity().startActivity(intent);
            sharedPreferencesUtils().remove(Constants.PHONE_NUMBER);
        }
    }


    public SharedPreferencesUtils sharedPreferencesUtils(){
        return Injector.INSTANCE.getAppComponent().exposeLocalDataSource().getSharedPreferences();
    }



    @Override
    public void initiateObserver(){
        mRootView.getLifecycle().addObserver(this);
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public abstract void onViewReady();


    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {

    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        mRootView.getLifecycle().removeObserver(this);
    }

    protected V getView() {
        return mRootView;
    }

    public void showProgressDialog(String title, String body) {

       getView().showMYProgressDialog(title,body);
    }

    public void hideProgressDialog() {

        getView().hideActivtyProgressDialog();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getView().getApplication()).getApplicationComponent();
    }

}
