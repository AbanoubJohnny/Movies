package abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.exceptions;

/**
 * Created by Abanoub Maher on 8/24/2017.
 */

public class ProjectThrowable extends Throwable {
    private String errorMessages;
    private int errorCode;

    public String getErrorMessages() {
        return errorMessages;
    }

    public ProjectThrowable setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
        return this;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ProjectThrowable  setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
