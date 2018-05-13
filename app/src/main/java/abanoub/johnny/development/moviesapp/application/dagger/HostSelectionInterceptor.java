package abanoub.johnny.development.moviesapp.application.dagger;

import android.support.annotation.NonNull;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;

import java.io.IOException;

import javax.inject.Inject;

import abanoub.johnny.development.moviesapp.application.app.MyApplication;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Abanoub Maher on 5/17/17.
 */

public class HostSelectionInterceptor implements Interceptor {

    private HostSelectionInterceptor sInterceptor;
    private String mScheme;
    private String mHost;
    private String token;
    private HttpUrl httpUrl;

    @Inject
    public HostSelectionInterceptor get() {
        if (sInterceptor == null) {
            sInterceptor = new HostSelectionInterceptor();
        }
        return sInterceptor;
    }

    public HostSelectionInterceptor(String url, String token) {
        if (sInterceptor == null) {
            sInterceptor = new HostSelectionInterceptor();
        }
        sInterceptor.httpUrl = HttpUrl.parse(url);
        if (sInterceptor.httpUrl != null) {
            sInterceptor.mScheme = sInterceptor.httpUrl.scheme();
            sInterceptor.mHost = sInterceptor.httpUrl.host();
        }
        sInterceptor.token = token;
    }

    private HostSelectionInterceptor() {
        // Intentionally blank
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        // If new Base URL is properly formatted than replace with old one

        if (mScheme != null && mHost != null) {
            HttpUrl newUrl = original.url().newBuilder()
                    .scheme(mScheme)
                    .host(mHost)
                    .build();


            Request.Builder originalBuilder = original.newBuilder()
                    .url(newUrl);

            token = MyApplication.sharedPreferencesUtils().getString(Constants.TOKEN, null);
            if (token != null)
                originalBuilder.addHeader("Authorization", token);

            original = originalBuilder.build();
        }
        return chain.proceed(original);
    }

}
