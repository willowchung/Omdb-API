package br.edu.infnet.avaliacao.omdb;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import br.edu.infnet.avaliacao.omdb.entidades.Movie;
import br.edu.infnet.avaliacao.omdb.fragments.MovieFragment;
import br.edu.infnet.avaliacao.omdb.interfaces.ISharedPreferences;
import br.edu.infnet.avaliacao.omdb.listeners.AppBarStateChangeListener;
import br.edu.infnet.avaliacao.omdb.util.Utils;

public class BuscaScrollActivity extends AppCompatActivity implements ISharedPreferences,
                            View.OnClickListener,
                            MovieFragment.OnListFragmentInteractionListener {
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
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = this.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE);
        accountID = sharedPreferences.getString(accountIdKey, null);

        setContentView(R.layout.activity_busca_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setAppBarListener();


        //Instanciando Fragment
        String userId = sharedPreferences.getString(accountIdKey, null);
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);

        MovieFragment movieFragment = new MovieFragment(); //Novo Fragment
        movieFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, movieFragment, "movieList").commit();
        //addFragment(R.id.fragment_container, movieFragment, "movieList");

        mEditText = (EditText) findViewById(R.id.moviesSearchEditText);
        mEditText.setOnClickListener(this);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEditText.setSingleLine(true);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                appBarLayout.setExpanded(false, true);
            }
        });*/
    }

    @Override
    public void onListFragmentInteraction(Movie item) {
        //TODO WW: Abrir detalhes do Filme
        String s = "";
    }

    private void setAppBarListener() {
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                /*if (state.name().equals(State.COLLAPSED)) {
                    findViewById(R.id.moviesSearchEditText).setVisibility(View.GONE);
                }
                if (state.name().equals(State.EXPANDED)) {
                    findViewById(R.id.moviesSearchEditText).setVisibility(View.VISIBLE);
                }*/
            }
        });
    }

    private void addFragment(int containerViewId, Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    private void searchMovies() {
        Utils.hideKeyboard(BuscaScrollActivity.this);
        EditText editText = (EditText) findViewById(R.id.moviesSearchEditText);
        editText.setVisibility(View.GONE);


        appBarLayout.setExpanded(false, true);
        findViewById(R.id.progress_spinner).setVisibility(View.VISIBLE);

        String searchText = mEditText.getText().toString().trim();

//        MovieFragment movieFragment = (MovieFragment)getSupportFragmentManager().findFragmentByTag("favorites");
//        if(movieFragment == null) {
        MovieFragment movieFragment = (MovieFragment)getSupportFragmentManager().findFragmentByTag("movieList");
//        }

        movieFragment.search(searchText);
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
