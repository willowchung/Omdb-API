package br.edu.infnet.avaliacao.omdb.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.avaliacao.omdb.R;
import br.edu.infnet.avaliacao.omdb.entidades.Movie;
import br.edu.infnet.avaliacao.omdb.entidades.OmdbAPI;
import cz.msebera.android.httpclient.Header;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MovieFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Movie> movieList = new ArrayList<>();
    private final String omdbAPI = "http://www.omdbapi.com/?type=Movie&s=";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MovieFragment newInstance(int columnCount) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        //this.movieList = (List<Movie>) getArguments().get("movieList");

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MovieRecyclerViewAdapter(movieList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Movie item);
    }

    public void search(String searchText) {


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(omdbAPI + searchText, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //findViewById(R.id.progress_spinner).setVisibility(View.GONE);
                //Toast.makeText(getContext(), "Connection Error" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                getActivity().findViewById(R.id.progress_spinner).setVisibility(View.GONE);
                try {
                    Gson gson = new Gson();
                    OmdbAPI omdbAPI = gson.fromJson(responseString, OmdbAPI.class);
                    List<Movie> movies = omdbAPI.getMovieList();

                    if(movies == null) {
                        movies = new ArrayList<Movie>();
                        //Toast.makeText(getContext(), "No movies found" , Toast.LENGTH_SHORT).show();
                    }

                    /*mAdapter = new FilmesAdapter(movies);
                    mRecyclerView.setAdapter(mAdapter);*/

                } catch (Exception e) {
                    //Toast.makeText(getContext(), "Erro parsing" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
