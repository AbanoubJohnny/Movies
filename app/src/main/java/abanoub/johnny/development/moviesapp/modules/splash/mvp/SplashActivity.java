package abanoub.johnny.development.moviesapp.modules.splash.mvp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.modules.home.mvp.HomeActivity;
import abanoub.johnny.development.moviesapp.modules.splash.dagger.DaggerSplashComponent;
import abanoub.johnny.development.moviesapp.modules.splash.dagger.SplashComponent;
import abanoub.johnny.development.moviesapp.modules.splash.dagger.SplashModule;
import abanoub.johnny.development.moviesapp.modules.splash.mvp.contract.SplashContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BaseActivity;


public class SplashActivity extends BaseActivity<SplashPresenter,SplashViewModel>
        implements SplashContract.View {


    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel setViewModel(){
        return ViewModelProviders.of(this).get(SplashViewModel.class);
    }

    @Override
    protected void resolveDaggerDependency(ApplicationComponent appComponent,SplashViewModel viewModel) {
         SplashComponent splashComponent= DaggerSplashComponent
                .builder()
                .applicationComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build();
         splashComponent.inject(this);
        splashComponent.inject(viewModel);


    }


    @Override
    public void onViewReady(Bundle savedInstanceState, Intent intent) {
        Handler handler = new Handler();
        handler.postDelayed(this::goTOHomePage, 3000);

    }

    @Override
    public void goTOHomePage() {

        startActivityWithFinish(HomeActivity.class);
    }
}
