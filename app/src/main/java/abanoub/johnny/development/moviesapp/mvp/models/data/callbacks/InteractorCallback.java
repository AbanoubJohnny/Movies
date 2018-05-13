package abanoub.johnny.development.moviesapp.mvp.models.data.callbacks;

import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions.ProjectThrowable;

import java.util.List;

import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions.ProjectThrowable;

/**
 * Created by Abanoub Maher on 5/2/17.
 */

public interface InteractorCallback<T> {
    void success(T t);

    void error(List<ProjectThrowable> throwables);


}
