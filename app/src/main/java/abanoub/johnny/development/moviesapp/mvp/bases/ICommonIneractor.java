package abanoub.johnny.development.moviesapp.mvp.bases;

import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;

import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;

/**
 * Created by Abanoub Johnny on 5/5/2018.
 */

public interface ICommonIneractor {
    DataCall getRemoteDataSource();

    LocalDataSource getLocalDataSource();

    void onDestroy();
}
