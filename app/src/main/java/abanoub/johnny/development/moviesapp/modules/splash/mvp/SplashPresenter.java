package abanoub.johnny.development.moviesapp.modules.splash.mvp;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.contract.SplashContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BasePresenter;

import javax.inject.Inject;

/**
 * Created by abanoubjohnny on 4/11/2017
 */

@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.ISplashPresenter {

    @Inject
    public SplashPresenter(SplashContract.View rootView) {
        super(rootView);
    }

    @Override
    public void onViewReady() {

    }
}

