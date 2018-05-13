package abanoub.johnny.development.moviesapp.modules.splash.mvp;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.contract.SplashContract;
import abanoub.johnny.development.moviesapp.mvp.models.db.AppDatabase;

import javax.inject.Inject;

@ActivityScope
public class SplashRepository implements SplashContract.ISplashRepository {
    @Inject
    public SplashRepository(AppDatabase appDatabase){
    }


    @Override
    public void onDestroy() {

    }
}
