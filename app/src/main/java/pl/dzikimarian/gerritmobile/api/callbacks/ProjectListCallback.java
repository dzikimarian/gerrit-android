package pl.dzikimarian.gerritmobile.api.callbacks;

import android.renderscript.Sampler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.dzikimarian.gerritmobile.ProjectList;
import pl.dzikimarian.gerritmobile.R;
import pl.dzikimarian.gerritmobile.api.Project;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pl.dzikimarian.gerritmobile.R.layout.project_list_item;

/**
 * Created by Michał on 05.07.2016.
 */
public class ProjectListCallback implements Callback<Map<String,Project>> {
    ProjectList activity;

    @BindView(R.id.hello_world)
    TextView t;

    @BindView(R.id.projectList)
    ListView list;

    @Inject
    public ProjectListCallback(ProjectList activity){
        super();
        this.activity = activity;
        ButterKnife.bind(this,activity);
    }

    @Override
    public void onResponse(Call<Map<String,Project>> call, Response<Map<String,Project>> response) {
        int projectCount = response.body().size();

        List<String> projects = new ArrayList<String>(response.body().keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, project_list_item, projects);
        list.setAdapter(adapter);

        t.setText(String.valueOf(response.body().size()));

    }

    @Override
    public void onFailure(Call<Map<String,Project>> call, Throwable t) {
        Log.i("api-result", "fail " + t.getMessage());
    }
}
