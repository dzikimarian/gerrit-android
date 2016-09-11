package pl.dzikimarian.gerritmobile.api;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Michał on 09.06.2016.
 */
public interface GerritService {
    @GET("/a/projects/")
    Call<Map<String, Project>> listProjects();
}
