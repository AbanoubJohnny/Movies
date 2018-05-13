package abanoub.johnny.development.moviesapp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import abanoub.johnny.development.moviesapp.mvp.bases.MessageType;


/**
 * Created by Abanoub Maher on 5/5/2018.
 */

public class ActivityUtils {
    private static Toast mToast;
    private final Activity mActivity;
    private final FragmentManager fragmentManager;
    private Snackbar snackbar;
    private ProgressDialog mProgressDialog;
    private ProgressDialog pd;
    private View view;

    public ActivityUtils(@NonNull FragmentActivity activity) {
        this(activity, activity.getSupportFragmentManager());
    }

    public ActivityUtils(@NonNull Activity activity, @NonNull FragmentManager fragmentManager) {
        this.mActivity = activity;
        this.fragmentManager = fragmentManager;
        view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean netState = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        netState = true;
                        break;
                    }
                }
            }
        }
        return netState;
    }

    public void showSnakeBar(@StringRes int resId) {
        snackbar = Snackbar.make(view, resId, Snackbar.LENGTH_INDEFINITE);
        View snackBarView = snackbar.getView();
        snackBarView.announceForAccessibility(mActivity.getString(resId));
        snackbar.setAction(null, null);
        snackbar.show();
    }

    public void showSnakeBar(@StringRes int textResId, @StringRes int actionResId, View.OnClickListener listener) {
        snackbar = Snackbar.make(view, textResId, Snackbar.LENGTH_INDEFINITE);
        View snackBarView = snackbar.getView();
        snackBarView.announceForAccessibility(mActivity.getString(textResId));
        snackbar.setAction(actionResId, listener);
        snackbar.show();
    }

    public void showSnakeBar(@StringRes int textResId, String actionText, View.OnClickListener listener) {
        snackbar = Snackbar.make(view, textResId, Snackbar.LENGTH_INDEFINITE);
        View snackBarView = snackbar.getView();
        snackBarView.announceForAccessibility(mActivity.getString(textResId));
        snackbar.setAction(actionText, listener);
        snackbar.show();
    }

    public void showSnakeBar(String textResId, String actionText, View.OnClickListener listener) {
        snackbar = Snackbar.make(view, textResId, Snackbar.LENGTH_INDEFINITE);
        View snackBarView = snackbar.getView();
        snackBarView.announceForAccessibility(textResId);
        snackbar.setAction(actionText, listener);
        snackbar.show();
    }

    public void hideSnakeBar() {
        if (snackbar != null && snackbar.isShown())
            snackbar.dismiss();
    }

    public void showSnakeBar(String message) {
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        View snackBarView = snackbar.getView();
        snackBarView.announceForAccessibility(message);
        snackbar.setAction(null, null);
        snackbar.show();
    }

    public void setSnackBar(Snackbar snackbar) {
        this.snackbar = snackbar;
    }

    public void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mActivity);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void startActivity(Intent intent, boolean finish) {
        mActivity.startActivity(intent);
        if (finish) {
            ((Activity) mActivity).finish();
        }
    }

    public void makeText(String string) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, string, Toast.LENGTH_SHORT);
        }
        mToast.setText(string);
        mToast.show();
    }

    public void showLoading(String message) {
        // FIXME: 3/5/2018
        pd = new ProgressDialog(mActivity);
        pd.setMessage(message);
        pd.show();
    }

    public void showLoading(@StringRes int messageId) {
        showLoading(mActivity.getString(messageId));
    }

    public void hideLoading() {
        // FIXME: 3/5/2018
        if (pd != null)
            pd.dismiss();
    }

    public void showMessage(String message) {
        // FIXME: 3/5/2018
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mActivity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        };
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void showMessage(@StringRes int messageStringRes, MessageType type,
                            MessageActionListener listener) {
        showMessage(mActivity.getString(messageStringRes), type, listener);
    }

    public void showMessage(String message, MessageType type, MessageActionListener listener) {
        // FIXME: 3/5/2018
        showMessage(message);
    }

    public void showMessage(@StringRes int messageStringRes, MessageType type) {
        showMessage(mActivity.getString(messageStringRes), type);
    }

    public void showMessage(String message, MessageType type) {
        // FIXME: 3/5/2018
        showMessage(message);
    }

    public void showConfirmationDialog(@Nullable String positiveActionText,
                                       @Nullable String negativeActionText,
                                       @NonNull String message,
                                       ConfirmationDialogActionsListener listener) {
        // FIXME: 3/5/2018
    }

    public void showConfirmationDialog(@Nullable String positiveActionText,
                                       @Nullable String negativeActionText,
                                       @StringRes int stringRes,
                                       ConfirmationDialogActionsListener listener) {
        showConfirmationDialog(positiveActionText, negativeActionText,
                mActivity.getString(stringRes), listener);
    }

    public void showConfirmationDialog(@StringRes int positiveActionText,
                                       @StringRes int negativeActionText,
                                       @StringRes int stringRes,
                                       ConfirmationDialogActionsListener listener) {
        showConfirmationDialog(
                mActivity.getString(positiveActionText),
                mActivity.getString(negativeActionText),
                mActivity.getString(stringRes), listener);
    }
}
