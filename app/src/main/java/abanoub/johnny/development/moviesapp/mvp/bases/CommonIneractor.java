package abanoub.johnny.development.moviesapp.mvp.bases;

import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;

/**
 * Created by AbanoubJohnny on 5/5/2018.
 */

public class CommonIneractor implements ICommonIneractor {
    private DataCall remoteDataSource;
    private LocalDataSource localDataSource;

    @Inject
    public CommonIneractor(DataCall remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public DataCall getRemoteDataSource() {
        return remoteDataSource;
    }

    @Override
    public LocalDataSource getLocalDataSource() {
        return localDataSource;
    }

    @Override
    public void onDestroy() {

    }
}
