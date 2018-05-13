package abanoub.johnny.development.moviesapp.modules.movie.mvp.interactors;

import java.util.ArrayList;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.contract.MovieContract;
import abanoub.johnny.development.moviesapp.mvp.bases.CommonIneractor;
import abanoub.johnny.development.moviesapp.mvp.bases.InteractorUtilities;
import abanoub.johnny.development.moviesapp.mvp.models.data.callbacks.InteractorCallback;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
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
public class MovieInteractor extends CommonIneractor implements MovieContract.IMovieInteractor {


    ArrayList<ProjectThrowable> errors = new ArrayList<>();
    ProjectThrowable projectThrowable = new ProjectThrowable();

    @Inject
    public MovieInteractor(DataCall dataCall, LocalDataSource localDataSource) {
        super(dataCall, localDataSource);
    }
    @Override
    public void getMovie(final InteractorCallback<MovieDetails> callback, int id) {
        getRemoteDataSource().getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieDetails >() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MovieDetails movieDetails) {

                        if (movieDetails.getStatus_code() == Constants.RSPONSE_CODE_SUCCESS) {
                            callback.success(movieDetails);
                        } else {
                            projectThrowable.setErrorMessages(movieDetails.getStatus_message());
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
