package abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.entity.MyErrores;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import retrofit2.HttpException;

/**
 * Created by Abanoub Maher on 8/23/2017.
 */

public class ExceptionHandler {
    public static ProjectThrowable handleException(Throwable throwable){
        throwable.printStackTrace();
        if (throwable instanceof HttpException) {
            return new ProjectThrowable().setErrorMessages(MyApplication.myApplicationContext.getString(R.string.error_in_service));
        }
        else if (throwable instanceof IOException) {
            return new ProjectThrowable().setErrorMessages(MyApplication.myApplicationContext.getString(R.string.error_in_connection)).setErrorCode(Constants.NO_NETWORK_CONNECTION);
        }
        else if(throwable instanceof TimeoutException){
            return new ProjectThrowable().setErrorMessages(MyApplication.myApplicationContext.getString(R.string.weak_network_connection)).setErrorCode(Constants.WEAK_NETWORK_CONNECTION);
        } else {
            return new ProjectThrowable().setErrorMessages(MyApplication.myApplicationContext.getString(R.string.try_again_later));
        }
    }

    public static ProjectThrowable handleException(MyErrores emptyResponse){
        if (emptyResponse.getCode() <500&&emptyResponse.getCode() >=400) {
            return new ProjectThrowable().setErrorMessages(emptyResponse.getMessage());
        }  else {
            return new ProjectThrowable().setErrorMessages(MyApplication.myApplicationContext.getString(R.string.try_again_later));
        }
    }
}
