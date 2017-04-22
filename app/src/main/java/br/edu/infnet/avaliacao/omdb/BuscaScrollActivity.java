package br.edu.infnet.avaliacao.omdb;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.avaliacao.omdb.adapters.FilmesAdapter;
import br.edu.infnet.avaliacao.omdb.entidades.Movie;
import br.edu.infnet.avaliacao.omdb.entidades.OmdbAPI;
import br.edu.infnet.avaliacao.omdb.interfaces.ISharedPreferences;
import br.edu.infnet.avaliacao.omdb.listeners.AppBarStateChangeListener;
import cz.msebera.android.httpclient.Header;

import static java.security.AccessController.getContext;

public class BuscaScrollActivity extends AppCompatActivity implements ISharedPreferences, View.OnClickListener {
    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }
    private AppBarStateChangeListener.State mCurrentState = AppBarStateChangeListener.State.IDLE;
    private SharedPreferences sharedPreferences;
    private String accountID;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private final String omdbAPI = "http://www.omdbapi.com/?type=Movie&s=";
    private EditText mEditText;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = this.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE);
        accountID = sharedPreferences.getString(accountIdKey, null);

        setContentView(R.layout.activity_busca_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setAppBarListener();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mEditText = (EditText) findViewById(R.id.moviesSearchEditText);
        mEditText.setOnClickListener(this);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEditText.setSingleLine(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                appBarLayout.setExpanded(false, true);
            }
        });
    }

    private void setAppBarListener() {
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
            }
        });
    }

    private void searchMovies() {
        appBarLayout.setExpanded(false, true);
        findViewById(R.id.progress_spinner).setVisibility(View.VISIBLE);
        String searchText = mEditText.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(omdbAPI + searchText, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                findViewById(R.id.progress_spinner).setVisibility(View.GONE);
                //Toast.makeText(getContext(), "Connection Error" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                findViewById(R.id.progress_spinner).setVisibility(View.GONE);
                try {
                    Gson gson = new Gson();
                    OmdbAPI omdbAPI = gson.fromJson(responseString, OmdbAPI.class);
                    List<Movie> movies = omdbAPI.getMovieList();

                    if(movies == null) {
                        movies = new ArrayList<Movie>();
                        //Toast.makeText(getContext(), "No movies found" , Toast.LENGTH_SHORT).show();
                    }

                    mAdapter = new FilmesAdapter(movies);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (Exception e) {
                    //Toast.makeText(getContext(), "Erro parsing" , Toast.LENGTH_SHORT).show();
                }
            }
        });


        /*// specify an adapter (see also next example)
        mAdapter = new FilmesAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_busca_scroll, menu);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.moviesSearchEditText:
                searchMovies();
                break;
        }
    }
}
