package abanoub.johnny.development.moviesapp.mvp.bases;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.application.dagger.Injector;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.mvp.models.local.SharedPreferencesUtils;
import abanoub.johnny.development.moviesapp.utils.ConfirmationDialogActionsListener;
import abanoub.johnny.development.moviesapp.utils.MessageActionListener;
import abanoub.johnny.development.moviesapp.utils.ProgressDialogNew;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Abanoub Maher on 5/2/17.
 */

public abstract class BaseFragment<P extends IPresenter,M extends IViewModel> extends Fragment implements IView {

    protected Activity myActivity;
    protected ProgressDialogNew progressDialogNew;
    private Unbinder mUnbinder;
    protected View mRootView;
    private LifecycleRegistry myLifecycleRegistry;
    @Inject
    protected P mPresenter;
    protected M mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            myActivity = getActivity();
        } catch (Exception e) {
            myActivity = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(getViewLayoutResId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        mViewModel= setViewModel();
        resolveDaggerDependency(Injector.INSTANCE.getAppComponent(),mViewModel);
        myLifecycleRegistry = new LifecycleRegistry(this);
        mPresenter.initiateObserver();
        mViewModel = setViewModel();
        onViewReady(mRootView, savedInstanceState);
        return mRootView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseActivity<IPresenter,IViewModel> getMyActivity() {
        try {
            return (BaseActivity) getActivity();
        } catch (Exception e) {
            return null;
        }
    }

    @LayoutRes
    protected abstract int getViewLayoutResId();

    protected abstract M setViewModel();

    protected abstract void resolveDaggerDependency(ApplicationComponent appComponent, M viewModel);

    protected abstract void onViewReady(View rootView, Bundle savedInstanceState);


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null)
            mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPresenter != null)
            mPresenter.activityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPresenter!= null)
            mPresenter.requestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return myLifecycleRegistry;
    }

    @Override
    public void showMessage(final int messageResourceId) {
        if (getMyActivity() == null)
            return;
        getMyActivity().showMessage(messageResourceId);
    }

    @Override
    public void showMessage(final String messageString) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showMessage(messageString);

    }


    @Override
    public void startActivity(Class<?> klass) {
        if (getMyActivity() == null)
            return;
        getMyActivity().startActivity(klass);
    }

    @Override
    public void startActivity(Class<?> klass, Bundle args) {
        if (getMyActivity() == null)
            return;
        getMyActivity().startActivity(klass, args);
    }

    @Override
    public void startActivityForResult(Class<?> klass, int requestCode) {

        if (getMyActivity() == null)
            return;
        getMyActivity().startActivityForResult(klass, requestCode);
    }

    @Override
    public void startActivityWithFinish(Class<?> klass) {
        if (getMyActivity() == null)
            return;
        getMyActivity().startActivityWithFinish(klass);
    }

    @Override
    public void startActivityWithFinish(Class<?> klass, Bundle args) {
        if (getMyActivity() == null)
            return;
        getMyActivity().startActivityWithFinish(klass, args);
    }

    @Override
    public Intent getIntent(Class<?> klass) {
        return getMyActivity().getIntent(klass);
    }

    @Override
    public void finishActivity() {
        if (getMyActivity() != null)
            getMyActivity().finish();
    }

    @Override
    public void finishActivity(int result) {
        if (getMyActivity() == null)
            return;
        getMyActivity().finishActivity(result);
    }

    @Override
    public void finishActivity(int resultOk, Intent createIntent) {
        if (getMyActivity() == null)
            return;
        getMyActivity().finishActivity(resultOk, createIntent);
    }


    @Override
    public void handleError(int resourceId) {
        if (getMyActivity() == null)
            return;
        getMyActivity().handleError(resourceId);
    }

    @Override
    public void requestPermission(String[] permissions, int requestCode) {
        requestPermissions(permissions, requestCode);
    }

    @Override
    public void showMYProgressDialog(String title, String body) {

        if (progressDialogNew == null) {
            if (!body.isEmpty())
                progressDialogNew = new ProgressDialogNew(getMyActivity(),
                        getMyActivity().getApplicationContext(), body, R.style.NewDialog);
            else
                progressDialogNew = new ProgressDialogNew(getMyActivity(),
                        getMyActivity().getApplicationContext(), title, R.style.NewDialog);
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

        if (getMyActivity() == null)
            return;
        getMyActivity().showActivityProgressDialog(title, body);
    }

    @Override
    public void hideActivtyProgressDialog() {
        if (getMyActivity() == null)
            return;
        getMyActivity().hideActivtyProgressDialog();
    }

    @Override
    public void errorGettingRequests() {
        getMyActivity().errorGettingRequests();
    }


    @Override
    public SharedPreferencesUtils sharedPreferencesUtils() {
        return getMyActivity().sharedPreferencesUtils();
    }

    @Override
    public void showSnakeBar(String message) {
        if (getMyActivity() == null)
            return;
        getMyActivity().showSnakeBar(message);
    }

    @Override
    public Application getApplication() {
        return getMyActivity().getApplication();
    }


    @Override
    public void showLoading() {

        if (getMyActivity() == null)
            return;
        getMyActivity().showLoading();
    }

    @Override
    public void showLoading(String message) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showLoading(message);
    }

    @Override
    public void showLoading(int messageId) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showLoading(messageId);

    }

    @Override
    public void hideLoading() {

        if (getMyActivity() == null)
            return;
        getMyActivity().hideLoading();
    }

    @Override
    public void showMessage(int messageStringRes, MessageType type, MessageActionListener listener) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showMessage(messageStringRes, type, listener);
    }

    @Override
    public void showMessage(String message, MessageType type, MessageActionListener listener) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showMessage(message, type, listener);
    }

    @Override
    public void showMessage(int messageStringRes, MessageType type) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showMessage(messageStringRes, type);
    }

    @Override
    public void showMessage(String message, MessageType type) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showMessage(message, type);
    }

    @Override
    public void showConfirmationDialog(@Nullable String positiveActionText, @Nullable String negativeActionText, int stringRes, ConfirmationDialogActionsListener listener) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showConfirmationDialog(positiveActionText, negativeActionText, stringRes, listener);
    }

    @Override
    public void showConfirmationDialog(int positiveActionText, int negativeActionText, int stringRes, ConfirmationDialogActionsListener listener) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showConfirmationDialog(positiveActionText, negativeActionText, stringRes, listener);
    }

    @Override
    public void showConfirmationDialog(@Nullable String positiveActionText, @Nullable String negativeActionText, @NonNull String message, ConfirmationDialogActionsListener listener) {

        if (getMyActivity() == null)
            return;
        getMyActivity().showConfirmationDialog(positiveActionText, negativeActionText, message, listener);
    }

    @Override
    public boolean isNetworkAvailable() {
        return getMyActivity().isNetworkAvailable();
    }


    @Override
    public void openFragment(int frameid, Fragment fragment,int frameLayout, Bundle args) {
        if (getMyActivity() == null)
            return;
        getMyActivity().openFragment(frameid, fragment, frameLayout, args);
    }

    @Override
    public void openFragment(Fragment fragment,int frameLayout) {
        if (getMyActivity() == null)
            return;
        getMyActivity().openFragment(fragment, frameLayout);
    }

    @Override
    public void endAllOpenFragments(FragmentManager fragmentManager, FragmentTransaction transaction) {

        if (getMyActivity() == null)
            return;
        getMyActivity().endAllOpenFragments(fragmentManager, transaction);
    }

    @Override
    public void showSnakeBar(@StringRes int textResId, String actionText, View.OnClickListener listener) {
        if (getMyActivity() == null)
            return;
        getMyActivity().showSnakeBar(textResId, actionText, listener);
    }

    @Override
    public void showSnakeBar(String message, String actionText, View.OnClickListener listener){

        if (getMyActivity() == null)
            return;
        getMyActivity().showSnakeBar(message, actionText, listener);
    }

    @Override
    public void hideSnakeBar(){

        if (getMyActivity() == null)
            return;
        getMyActivity().hideSnakeBar();
    }


    public P getPresenter() {
        return mPresenter;
    }
}

