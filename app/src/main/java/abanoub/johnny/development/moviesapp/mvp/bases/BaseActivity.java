package abanoub.johnny.development.moviesapp.mvp.bases;

import android.app.ActionBar;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.application.app.LocaleManager;
import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.application.dagger.Injector;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.mvp.models.local.SharedPreferencesUtils;
import abanoub.johnny.development.moviesapp.utils.ActivityUtils;
import abanoub.johnny.development.moviesapp.utils.ConfirmationDialogActionsListener;
import abanoub.johnny.development.moviesapp.utils.MessageActionListener;
import abanoub.johnny.development.moviesapp.utils.ProgressDialogNew;
import abanoub.johnny.development.moviesapp.utils.UtiltiesMethods;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Abanoub Maher on 8/18/17.
 */

public abstract class BaseActivity<P extends IPresenter,M extends IViewModel> extends AppCompatActivity implements IView {
    String language;
    int flagLang;
    private Unbinder mUnbinder;
    protected ProgressDialogNew progressDialogNew;
    private ActivityUtils activityUtils;
    private LifecycleRegistry mLifecycleRegistry;


    // This flag should be set to true to enable VectorDrawable support for API < 21
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    protected P mPresenter;

    protected M mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (sharedPreferencesUtils().getString(Constants.LANGUAGE, Constants.ENGLISH).matches(Constants.ARABIC)) {
            UtiltiesMethods.setLocale(this, Constants.ARABIC);
        } else if (sharedPreferencesUtils().getString(Constants.LANGUAGE, Constants.ENGLISH).matches(Constants.ENGLISH)) {
            UtiltiesMethods.setLocale(this, Constants.ENGLISH);
        }
        super.onCreate(savedInstanceState);
        hideNavigationBar();
        setContentView(getContentView());
        mUnbinder = ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        mViewModel= setViewModel();
        resolveDaggerDependency(Injector.INSTANCE.getAppComponent(),mViewModel);
        this.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mPresenter.initiateObserver();
        initiateViewModel();
        onViewReady(savedInstanceState, getIntent());
    }

    public void hideNavigationBar(){
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void hideStatusBar(){
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        actionBar.hide();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    public void startActivityFromClass(Class<?> klass) {
        Intent intent = getIntent(klass);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public BaseActivity getCurrentActivity() {
        return this;
    }


    @Override
    public SharedPreferencesUtils sharedPreferencesUtils() {
        return MyApplication.sharedPreferencesUtils();
    }


    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getPresenter() != null)
            getPresenter().onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getPresenter() != null)
            getPresenter().activityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (getPresenter() != null)
            getPresenter().requestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        if (mUnbinder != null)
            mUnbinder.unbind();
    }
    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }


    @LayoutRes
    protected abstract int getContentView();

    public abstract M setViewModel();

    protected abstract void resolveDaggerDependency(ApplicationComponent appComponent, M viewModel);

    public abstract void onViewReady(Bundle savedInstanceState, Intent intent);

    public void initiateViewModel(){
        mViewModel.getMessages().observe(this, this::showMessage);
        mViewModel.getIsLoading().observe(this, isShowing -> {
            if (isShowing!=null) {
                if (isShowing) {
                    showMYProgressDialog("", "");
                } else {
                    hideMyProgressDialog();
                }
            }
            else {
                Log.e("BaseActivity","is Showing equal null");
            }
        });
    }
    @Override
    public M getmViewModel(){
        return mViewModel;
    }
    @Override
    public void showLoading() {
        activityUtils.showLoading(R.string.label_loading);
    }

    @Override
    public void showLoading(String message) {
        activityUtils.showLoading(message);
    }

    @Override
    public void showLoading(@StringRes int messageId) {
        activityUtils.showLoading(messageId);
    }

    @Override
    public void hideLoading() {
        activityUtils.hideLoading();
    }

    @Override
    public void showMessage(String message) {
        activityUtils.showMessage(message);
    }

    @Override
    public void showMessage(@StringRes int messageStringRes, MessageType type,
                            MessageActionListener listener) {
        activityUtils.showMessage(messageStringRes, type, listener);
    }

    @Override
    public void showMessage(String message, MessageType type,
                            MessageActionListener listener) {
        activityUtils.showMessage(message, type, listener);
    }

    @Override
    public void showMessage(@StringRes int messageStringRes, MessageType type) {
        activityUtils.showMessage(messageStringRes, type, () -> {
        });
    }

    @Override
    public void showMessage(String message, MessageType type) {
        activityUtils.showMessage(message, type, () -> {
        });
    }


    @Override
    public void showConfirmationDialog(@Nullable String positiveActionText,
                                       @Nullable String negativeActionText,
                                       @NonNull String message,
                                       ConfirmationDialogActionsListener listener) {
        activityUtils.showConfirmationDialog(positiveActionText, negativeActionText, message, listener);
    }

    @Override
    public void showConfirmationDialog(@Nullable String positiveActionText,
                                       @Nullable String negativeActionText,
                                       @StringRes int stringRes,
                                       ConfirmationDialogActionsListener listener) {
        activityUtils.showConfirmationDialog(positiveActionText, negativeActionText, stringRes, listener);
    }

    @Override
    public void showConfirmationDialog(@StringRes int positiveActionText,
                                       @StringRes int negativeActionText,
                                       @StringRes int stringRes,
                                       ConfirmationDialogActionsListener listener) {
        activityUtils.showConfirmationDialog(positiveActionText, negativeActionText, stringRes, listener);
    }

    @Override
    public boolean isNetworkAvailable() {
        return ActivityUtils.isNetworkAvailable(this);
    }


    @Override
    public void startActivity(Class<?> klass) {
        Intent intent = getIntent(klass);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void startActivity(Class<?> klass, Bundle args) {
        Intent intent = getIntent(klass);
        intent.putExtras(args);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void startActivityForResult(Class<?> klass, int requestCode) {
        Intent intent = getIntent(klass);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityWithFinish(Class<?> klass) {
        Intent intent = getIntent(klass);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finishActivity();
    }

    @Override
    public void startActivityWithFinish(Class<?> klass, Bundle args) {
        Intent intent = getIntent(klass);
        intent.putExtras(args);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finishActivity();
    }

    @Override
    public Intent getIntent(Class<?> klass) {
        return new Intent(this, klass);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void finishActivity(int result) {
        if (getParent() == null) {
            setResult(result);
        } else {
            getParent().setResult(result);
        }
        finish();
    }

    @Override
    public void finishActivity(int resultOk, Intent createIntent) {
        if (getParent() == null) {
            setResult(resultOk, createIntent);
        } else {
            getParent().setResult(resultOk, createIntent);
        }
        finish();
    }

    @Override
    public void showSnakeBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public void errorGettingRequests() {
        showMessage("error");
    }

    @Override
    public void handleError(int resourceId) {
        showMessage(resourceId);
    }


    @Override
    public void showMYProgressDialog(String title, String body) {

        if (progressDialogNew == null) {
            if (!body.isEmpty())
                progressDialogNew = new ProgressDialogNew(this,
                        getApplicationContext(), body, R.style.NewDialog);
            else
                progressDialogNew = new ProgressDialogNew(this,
                        getApplicationContext(), title, R.style.NewDialog);
            progressDialogNew.setCancelable(false);
            progressDialogNew.show();
        } else {
            progressDialogNew.setCancelable(false);
            progressDialogNew.show();
        }
    }

    @Override
    public void hideMyProgressDialog() {
        if (progressDialogNew != null && progressDialogNew.isShowing())
            progressDialogNew.dismiss();
    }

    @Override
    public void showActivityProgressDialog(String title, String body) {
        this.showMYProgressDialog(title, body);
    }

    @Override
    public void hideActivtyProgressDialog() {
        hideMyProgressDialog();
    }


    @Override
    public BaseActivity getMyActivity() {
        return this;
    }

    @Override
    public void showMessage(final int messageResourceId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), getString(messageResourceId), Toast.LENGTH_LONG).show();
            }
        };
        new Handler(Looper.getMainLooper()).post(runnable);

    }

    @Override
    public void requestPermission(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(this,
                permissions,
                requestCode);
    }

    @Override
    public void openFragment(int frameid, Fragment fragment,int frameLayout,Bundle args) {

        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        endAllOpenFragments(fragmentManager, transaction);
        transaction.add(frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public void openFragment(Fragment fragment,int frameLayout) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        endAllOpenFragments(fragmentManager, transaction);
        transaction.add(frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public void endAllOpenFragments(FragmentManager fragmentManager, FragmentTransaction transaction) {
        List<Fragment> oldFragments = fragmentManager.getFragments();
        for (Fragment oldFragment : oldFragments) {
            if (oldFragment != null)
                transaction.remove(oldFragment);
        }
    }
    @Override
    public void showSnakeBar(@StringRes int textResId, String actionText, View.OnClickListener listener){
        activityUtils.showSnakeBar(textResId,actionText,listener);
    }


    @Override
    public void showSnakeBar(String message, String actionText, View.OnClickListener listener){

        activityUtils.showSnakeBar(message,actionText,listener);
    }
    @Override
    public void hideSnakeBar(){

        activityUtils.hideSnakeBar();
    }
}
