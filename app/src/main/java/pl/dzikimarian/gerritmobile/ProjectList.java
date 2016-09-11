package pl.dzikimarian.gerritmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import pl.dzikimarian.gerritmobile.api.GerritService;
import pl.dzikimarian.gerritmobile.api.Project;
import pl.dzikimarian.gerritmobile.api.callbacks.ProjectListCallback;
import retrofit2.Call;
import retrofit2.Retrofit;

public class ProjectList extends AppCompatActivity {

    AppComponent component;

    @Inject
    Retrofit retrofit;
    //@Inject
    //ProjectListCallback projectListCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        ((MyApplication) getApplication()).getComponent().inject(this);

        GerritService gerritService = retrofit.create(GerritService.class);

        Call<Map<String, Project>> projects = gerritService.listProjects();
        projects.enqueue(new ProjectListCallback(this));
    }
}
