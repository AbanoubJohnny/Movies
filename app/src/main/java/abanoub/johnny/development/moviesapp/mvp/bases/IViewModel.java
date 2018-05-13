package abanoub.johnny.development.moviesapp.mvp.bases;

import android.arch.lifecycle.LiveData;

/**
 * Created by Abanoub Maher on 5/2/17.
 */

public interface IViewModel{

    void shwoMessage(String message);

    void shwoLoading();

    void hideLoading();

    LiveData<Boolean> getIsLoading();

    LiveData<String> getMessages();

}
