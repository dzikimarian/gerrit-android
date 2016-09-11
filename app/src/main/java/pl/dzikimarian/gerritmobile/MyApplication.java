package pl.dzikimarian.gerritmobile;

import android.app.Application;
import android.util.Log;

/**
 * Created by Michał on 11.09.2016.
 */
public class MyApplication extends Application {
    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
