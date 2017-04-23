package br.edu.infnet.avaliacao.omdb.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.edu.infnet.avaliacao.omdb.R;
import br.edu.infnet.avaliacao.omdb.entidades.Movie;
import br.edu.infnet.avaliacao.omdb.fragments.MovieFragment.OnListFragmentInteractionListener;
import br.edu.infnet.avaliacao.omdb.fragments.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> moviesList;
    private final OnListFragmentInteractionListener mListener;

    public MovieRecyclerViewAdapter(List<Movie> items, OnListFragmentInteractionListener listener) {
        moviesList = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movie = moviesList.get(position);
        holder.mTitle.setText(moviesList.get(position).getTitle());
        holder.mDirector.setText(moviesList.get(position).getDirector());
        holder.mYear.setText(moviesList.get(position).getYear());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mDirector;
        public final TextView mYear;
        public Movie movie;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.movie_title);
            mDirector = (TextView) view.findViewById(R.id.movie_director);
            mYear = (TextView) view.findViewById(R.id.movie_year);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
