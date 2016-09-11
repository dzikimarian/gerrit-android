package pl.dzikimarian.gerritmobile.api;

import com.burgstaller.okhttp.AuthenticationCacheInterceptor;
import com.burgstaller.okhttp.CachingAuthenticatorDecorator;
import com.burgstaller.okhttp.digest.CachingAuthenticator;
import com.burgstaller.okhttp.digest.Credentials;
import com.burgstaller.okhttp.digest.DigestAuthenticator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.dzikimarian.gerritmobile.ProjectList;
import pl.dzikimarian.gerritmobile.api.callbacks.ProjectListCallback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michał on 11.09.2016.
 */

@Module
public class ApiModule {

    String username = "mmaciag";
    String password = "Wt9xz7Qy75JlaZ8Z/S5ErUCeVeoI9thfpg5EzZ3ezA";
    String baseUrl = "https://review.upaid.pl:8443/";

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient getHttpClient(XSSIStripingInterceptor xssiStripingInterceptor) {

        final DigestAuthenticator authenticator = new DigestAuthenticator(new Credentials(this.username, this.password));
        final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();

        return new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationCacheInterceptor(authCache))
                .addInterceptor(xssiStripingInterceptor)
                .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
                .build();

    }

    @Provides
    @Singleton
    XSSIStripingInterceptor providesXSSIStrippingInterceptor(){
        return new XSSIStripingInterceptor();
    }

    @Provides
    ProjectListCallback projectListCallback(ProjectList activity){
        return new ProjectListCallback(activity);
    }
}
