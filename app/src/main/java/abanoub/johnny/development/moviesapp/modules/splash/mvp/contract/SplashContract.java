package abanoub.johnny.development.moviesapp.modules.splash.mvp.contract;

import abanoub.johnny.development.moviesapp.mvp.bases.ICommonIneractor;
import abanoub.johnny.development.moviesapp.mvp.bases.ICommonRepository;
import abanoub.johnny.development.moviesapp.mvp.bases.IPresenter;
import abanoub.johnny.development.moviesapp.mvp.bases.IView;
import abanoub.johnny.development.moviesapp.mvp.bases.IViewModel;

public interface SplashContract {

    interface View extends IView {
        void goTOHomePage();
    }
    interface ISplashInteractor  extends ICommonIneractor {

    }


    interface ISplashPresenter extends IPresenter {
    }

    interface ISplashViewModel extends IViewModel{
    }

    interface ISplashRepository extends ICommonRepository{
    }
}
