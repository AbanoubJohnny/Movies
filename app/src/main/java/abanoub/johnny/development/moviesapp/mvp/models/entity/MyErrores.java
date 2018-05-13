package abanoub.johnny.development.moviesapp.mvp.models.entity;

/**
 * Created by Abanoub Johnny on 2/23/2018.
 */

public class MyErrores {
    private int status_code;
    private String status_message;


    public int getCode() {
        return status_code;
    }

    public String getMessage() {
        return status_message;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}
