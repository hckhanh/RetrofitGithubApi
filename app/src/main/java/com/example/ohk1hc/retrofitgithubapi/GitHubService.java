package com.example.ohk1hc.retrofitgithubapi;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubService {

    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepositories(@Path("user") String user);

}
