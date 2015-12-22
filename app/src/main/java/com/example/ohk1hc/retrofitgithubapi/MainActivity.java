package com.example.ohk1hc.retrofitgithubapi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    GitHubService gitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Repo>> listRepo = gitHubService.listRepositories("hckhanh");
                try {
                    List<Repo> repo = listRepo.execute().body();
                    Toast.makeText(MainActivity.this, repo.get(0).getName(), Toast.LENGTH_SHORT)
                            .show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .build();

        gitHubService = retrofit.create(GitHubService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
