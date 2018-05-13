package abanoub.johnny.development.moviesapp.modules.splash.mvp;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.contract.SplashContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BaseViewModel;


@ActivityScope
public class SplashViewModel extends BaseViewModel<SplashContract.ISplashInteractor,SplashContract.ISplashRepository> implements SplashContract.ISplashViewModel {

}
