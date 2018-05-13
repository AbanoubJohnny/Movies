package abanoub.johnny.development.moviesapp.modules.movie.mvp;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.contract.MovieContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BasePresenter;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
public class MoviePresenter extends BasePresenter<MovieContract.IMovieView> implements MovieContract.IMoviePresenter {
    private int callsCounter;

    @Inject
    public MoviePresenter(MovieContract.IMovieView rootView) {
        super(rootView);
    }

    @Override
    public void onViewReady() {

    }

}
