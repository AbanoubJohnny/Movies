package  abanoub.johnny.development.moviesapp.modules.searchresults.mvp.interactors;

import java.util.ArrayList;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.dagger.scope.ActivityScope;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.contract.SearchResultsContract;
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
public class SearchResultsInteractor extends CommonIneractor implements SearchResultsContract.ISearchResultsInteractor {

    ArrayList<ProjectThrowable> errors = new ArrayList<>();
    ProjectThrowable projectThrowable = new ProjectThrowable();
    @Inject
    public SearchResultsInteractor(DataCall dataCall, LocalDataSource localDataSource) {
        super(dataCall, localDataSource);
    }


    @Override
    public void getResults(final InteractorCallback<MoviesPage<ArrayList<Movie>> > callback,String searchString, int page) {
        getRemoteDataSource().searchMovies(searchString,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MoviesPage<ArrayList<Movie>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MoviesPage<ArrayList<Movie>> moviesPage) {

                        if (moviesPage.getStatus_code() == Constants.RSPONSE_CODE_SUCCESS) {
                            callback.success(moviesPage);
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
