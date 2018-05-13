package abanoub.johnny.development.moviesapp.modules.home.mvp;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.home.mvp.contract.HomeContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BasePresenter;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.IHomeView> implements HomeContract.IHomePresenter {
    private int callsCounter;

    @Inject
    public HomePresenter(HomeContract.IHomeView rootView) {
        super(rootView);
    }

    @Override
    public void onViewReady() {

    }

}
