package abanoub.johnny.development.moviesapp.application.dagger.modules;

import abanoub.johnny.development.moviesapp.BuildConfig;
import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.application.dagger.HostSelectionInterceptor;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import abanoub.johnny.development.moviesapp.mvp.models.local.LocalDataSource;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCallsImpl;

import java.util.concurrent.TimeUnit;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.application.dagger.HostSelectionInterceptor;
import abanoub.johnny.development.moviesapp.application.dagger.scope.ApplicationScope;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCall;
import abanoub.johnny.development.moviesapp.mvp.models.remoteDataCalls.network.DataCallsImpl;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abanoub Maher on 5/2/17.
 */
@Module
public class NetworkModule {
    private final MyApplication application;
    private final int TIMEOUT = 120;
    private final int cacheSize = 10 * 1024 * 1024; // 10 MiB

    public NetworkModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    DataCall provideNetworkCalls(Retrofit retrofit) {
        return new DataCallsImpl(retrofit);
    }

    @Provides
    @ApplicationScope
    HostSelectionInterceptor provideInterceptor(LocalDataSource localDataSource) { // This is where the Interceptor object is constructed

        String token = localDataSource.getSharedPreferences().getString(Constants.TOKEN, null);
        HostSelectionInterceptor hostSelectionInterceptor = new HostSelectionInterceptor(BuildConfig.BASE_URL,token);
        return hostSelectionInterceptor.get();
    }

    @Provides
    @ApplicationScope
    Cache provideOkHttpCache() {
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(HostSelectionInterceptor interceptor,Cache cache) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .cache(cache)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofitBuilder(OkHttpClient okHttpClient) { // The Client is then added to Retrofit
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build(); // Base URL to create instance
    }


}
