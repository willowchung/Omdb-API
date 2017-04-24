package br.edu.infnet.avaliacao.omdb;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.KeyEvent;
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
                                        MovieFragment.OnListFragmentInteractionListener  {

    /*public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }
    private AppBarStateChangeListener.State mCurrentState = AppBarStateChangeListener.State.IDLE;*/
    private Menu mMenu;
    private SharedPreferences sharedPreferences;
    private String accountID;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private EditText mEditText;
    private MovieFragment mMovieFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = this.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE);
        accountID = sharedPreferences.getString(accountIdKey, null);

        setContentView(R.layout.activity_busca_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setEditTextListener();
        setAppBarListener();
        setFragment();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mAppBarLayout.setExpanded(false, true);
            }
        });*/
    }

    private void setFragment() {
        //Instanciando Fragment
        String userId = sharedPreferences.getString(accountIdKey, null);
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);

        mMovieFragment = new MovieFragment(); //Novo Fragment
        mMovieFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mMovieFragment, "movieList").commit();
    }

    private void setEditTextListener() {
        mEditText = (EditText) findViewById(R.id.moviesSearchEditText);
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEditText.setSingleLine(true);
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == event.KEYCODE_ENTER) {
                    searchMovies();
                }
                return false;
            }
        });
    }

    private void setAppBarListener() {
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        /*mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == -mCollapsingToolbarLayout.getHeight() + mToolbar.getHeight()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                else if (verticalOffset == 0) {
                    mEditText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });*/
    }

    @Override
    public void onListFragmentInteraction(Movie item) {
        //TODO WW: Abrir detalhes do Filme
    }

    private void searchMovies() {
        Utils.hideKeyboard(BuscaScrollActivity.this);

        mAppBarLayout.setExpanded(false, true);
        findViewById(R.id.progress_spinner).setVisibility(View.VISIBLE);

        String searchText = mEditText.getText().toString().trim();
        mMovieFragment.search(searchText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_busca_scroll, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_favorites:
                Drawable favoriteSelected = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp);
                Drawable favoriteUnselected = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp);

                if (item.getIcon().getCurrent().getConstantState().equals(favoriteUnselected.getConstantState())) {
                    mMenu.getItem(1).setIcon(favoriteSelected);
                    mMovieFragment.loadFavorites();
                }
                else {
                    mMenu.getItem(1).setIcon(favoriteUnselected);
                    searchMovies();
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
