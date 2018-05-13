package abanoub.johnny.development.moviesapp.mvp.bases;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import abanoub.johnny.development.moviesapp.mvp.models.entity.MyErrores;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions.ExceptionHandler;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions.ProjectThrowable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by Abanoub Johnny on 15-Apr-18.
 */

public class InteractorUtilities {
    public static List<ProjectThrowable> checkError(Throwable e){
        ArrayList<ProjectThrowable> errors = new ArrayList<>();
        if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();
            String message = null;
            try {
                if (body != null) {
                    message = body.string();
                    Gson gson = new Gson();
                    try {
                        MyErrores response = gson.fromJson(message, MyErrores.class);
                        errors.add(ExceptionHandler.handleException(response));
                    }catch (Exception e1){
                        MyErrores error = new MyErrores();
                        error.setStatus_message(message);
                        errors.add(ExceptionHandler.handleException(error));
                    }
                }else {
                    errors.add(ExceptionHandler.handleException(e));
                }
            } catch (IOException e1) {
                Log.e("InteractorUtilities",e1.getMessage());
            }
        }else {
            errors.add(ExceptionHandler.handleException(e));
        }
        return errors;
    }
}
