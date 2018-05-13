/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package abanoub.johnny.development.moviesapp.mvp.bases;

/**
 * Created by janisharali on 27/01/17.
 */

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import abanoub.johnny.development.moviesapp.mvp.models.local.SharedPreferencesUtils;
import abanoub.johnny.development.moviesapp.utils.ConfirmationDialogActionsListener;
import abanoub.johnny.development.moviesapp.utils.MessageActionListener;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface IView extends LifecycleOwner {


    IViewModel getmViewModel();

    void showLoading();

    void showLoading(String message);

    void showLoading(@StringRes int messageId);

    void hideLoading();

    void showMessage(int messageResourceId);

    void showMessage(String message);

    void showMessage(@StringRes int messageStringRes, MessageType type,
                     MessageActionListener listener);

    void showMessage(String message, MessageType type,
                     MessageActionListener listener);

    void showMessage(@StringRes int messageStringRes, MessageType type);

    void showMessage(String message, MessageType type);

    void showConfirmationDialog(@Nullable String positiveActionText,
                                @Nullable String negativeActionText,
                                @StringRes int stringRes,
                                ConfirmationDialogActionsListener listener);

    void showConfirmationDialog(@StringRes int positiveActionText,
                                @StringRes int negativeActionText,
                                @StringRes int stringRes,
                                ConfirmationDialogActionsListener listener);

    void showConfirmationDialog(@Nullable String positiveActionText,
                                @Nullable String negativeActionText,
                                @NonNull String message,
                                ConfirmationDialogActionsListener listener);

    boolean isNetworkAvailable();


    void startActivityWithFinish(Class<?> klass, Bundle args);

    Intent getIntent(Class<?> klass);

    void finishActivity();

    void finishActivity(int result);

    void finishActivity(int resultOk, Intent createIntent);

    void startActivity(Intent intent);

    void startActivity(Class<?> klass);

    void startActivityWithFinish(Class<?> klass);

    void startActivity(Class<?> klass, Bundle args);

    void startActivityForResult(Class<?> klass, int requestCode);

    void startActivityForResult(Intent intent, int requestCode);

    void handleError(int resourceId);

    void requestPermission(String[] permissions, int requestCode);

    void showMYProgressDialog(String title, String body);

    void hideMyProgressDialog();

    void showActivityProgressDialog(String title, String body);

    void hideActivtyProgressDialog();

    void errorGettingRequests();

    SharedPreferencesUtils sharedPreferencesUtils();

    void showSnakeBar(String message);

    Application getApplication();

    Activity getMyActivity();

    void openFragment(int frameid, Fragment fragment, int frameLayout, Bundle args);

    void openFragment(Fragment fragment, int frameLayout);

    void endAllOpenFragments(FragmentManager fragmentManager, FragmentTransaction transaction);

    void showSnakeBar(@StringRes int textResId, String actionText, View.OnClickListener listener);
    void showSnakeBar(String message, String actionText, View.OnClickListener listener);

    void hideSnakeBar();
}
