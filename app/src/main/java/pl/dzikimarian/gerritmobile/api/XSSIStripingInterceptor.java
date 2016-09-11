package pl.dzikimarian.gerritmobile.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * Created by Michał on 05.07.2016.
 */
public class XSSIStripingInterceptor implements Interceptor {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response response = chain.proceed(chain.request());

        //Log.w("Retrofit@Response", response.body().string());

        String responseBody = response.body()
                .string()
                .substring(4);

        Log.w("Retrofit@Stripped", responseBody);

        MediaType contentType = response.body().contentType();
        ResponseBody body = ResponseBody.create(contentType, responseBody);

        return response.newBuilder().body(body).build();
    }
}
