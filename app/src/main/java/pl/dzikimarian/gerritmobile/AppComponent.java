package pl.dzikimarian.gerritmobile;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;
import pl.dzikimarian.gerritmobile.api.ApiModule;

/**
 * Created by Michał on 11.09.2016.
 */
@Singleton
@Component(modules = {ApiModule.class})
public interface AppComponent {
    void inject(ProjectList activity);
}
