package abanoub.johnny.development.moviesapp.modules.home.mvp.interactors;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.home.mvp.contract.HomeContract;
import abanoub.johnny.development.moviesapp.mvp.bases.CommonIneractor;
import abanoub.johnny.development.moviesapp.mvp.bases.InteractorUtilities;
import abanoub.johnny.development.moviesapp.mvp.models.data.callbacks.InteractorCallback;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.MoviesPage;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions.ProjectThrowable;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

@ActivityScope
public class HomeInteractor extends CommonIneractor implements HomeContract.IHomeInteractor {

    ArrayList<ProjectThrowable> errors = new ArrayList<>();
    ProjectThrowable projectThrowable = new ProjectThrowable();

    @Inject
    public HomeInteractor(DataCall dataCall, LocalDataSource localDataSource) {
        super(dataCall, localDataSource);
    }

    @Override
    public void getPopular(final InteractorCallback<List<Movie>> callback, int page) {
        getRemoteDataSource().getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MoviesPage<ArrayList<Movie>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MoviesPage<ArrayList<Movie>> moviesPage) {

                        if (moviesPage.getStatus_code() == Constants.RSPONSE_CODE_SUCCESS) {
                            callback.success(moviesPage.getResults());
                        } else {
                            projectThrowable.setErrorMessages(moviesPage.getStatus_message());
                            errors.add(projectThrowable);
                            callback.error(errors);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.error(InteractorUtilities.checkError(e));
                    }
                });
    }
    @Override
    public void getToprated(final InteractorCallback<List<Movie>> callback, int page) {
        getRemoteDataSource().getTopRatedMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MoviesPage<ArrayList<Movie>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MoviesPage<ArrayList<Movie>> moviesPage) {

                        if (moviesPage.getStatus_code() == Constants.RSPONSE_CODE_SUCCESS) {
                            callback.success(moviesPage.getResults());
                        } else {
                            projectThrowable.setErrorMessages(moviesPage.getStatus_message());
                            errors.add(projectThrowable);
                            callback.error(errors);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.error(InteractorUtilities.checkError(e));
                    }
                });
    }
}
