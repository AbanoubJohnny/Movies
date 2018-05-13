package abanoub.johnny.development.moviesapp.mvp.models.local;

/**
 * Created by Abanoub Johnny on 7/23/2017.
 */


public interface Constants {

    String PHONE_NUMBER = "PHONE_NUMBER";
    String ARABIC = "ar-AE";
    String ENGLISH = "en-US";
    String LANGUAGE = "LANGUAGE";
    String LANGUAGEFLAG = "LANGUAGEFLAG";
    String TOKEN = "TOKEN";
    int LOCATION_INTERVAL_MILLISECONDS = 1000;
    String DRIVER_ID = "DRIVER_ID";
    String DB_NAME = "my_project_db";

    //Network State///////////////////////
    int TYPE_WIFI = 1;
    int TYPE_MOBILE = 2;
    int TYPE_NOT_CONNECTED = 0;
    int NETWORK_STATUS_NOT_CONNECTED = 0;
    int NETWORK_STAUS_WIFI = 1;
    int NETWORK_STATUS_MOBILE = 2;
    //Network State///////////////////////


    //responses codes ///////////////////////////////////////
    int RSPONSE_CODE_SUCCESS = 0;
    int RSPONSE_CODE_SUCCESS_WITH_DATA = 202;
    int RSPONSE_CODE_NOT_FOUND = 404;
    int RSPONSE_CODE_NOT_AUTHORIZED = 401;
    int NO_NETWORK_CONNECTION = 8000;
    int WEAK_NETWORK_CONNECTION = 8001;
    //responses codes ///////////////////////////////////////

    //Movies states//////////////
    String MovieDetails = "MovieDetails";
    String MovieToShow = "MovieToShow";
}
