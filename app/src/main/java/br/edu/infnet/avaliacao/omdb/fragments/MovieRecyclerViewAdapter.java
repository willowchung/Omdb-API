package br.edu.infnet.avaliacao.omdb.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.List;

import br.edu.infnet.avaliacao.omdb.R;
import br.edu.infnet.avaliacao.omdb.entidades.Movie;
import br.edu.infnet.avaliacao.omdb.fragments.MovieFragment.OnListFragmentInteractionListener;
import br.edu.infnet.avaliacao.omdb.fragments.dummy.DummyContent.DummyItem;
import br.edu.infnet.avaliacao.omdb.repository.FavoritesDB;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> moviesList;
    FavoritesDB favoritesDB;
    private final OnListFragmentInteractionListener mListener;
    private String userId;

    public MovieRecyclerViewAdapter(String userId, List<Movie> items, OnListFragmentInteractionListener listener) {
        moviesList = items;
        mListener = listener;
        this.userId = userId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.movie = movie;
        holder.mTitle.setText(movie.getTitle());
        holder.mDirector.setText(movie.getDirector());
        holder.mYear.setText(movie.getYear());

        //Set Image
        holder.mImageView.layout(0, 0, 0, 0); // invalidate the width so that glide wont use that dimension
        Glide.with(holder.mImageView.getContext()).load(movie.getPoster()).into(holder.mImageView);

        setFavorites(holder);

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

    private void setFavorites(final ViewHolder holder) {
        //holder.favorite.setVisibility(View.VISIBLE);
        favoritesDB = FavoritesDB.getInstance(holder.mFavorite.getContext());
        holder.mFavorite.setFavorite(favoritesDB.isFavorite(holder.movie.getImdbID(), userId), false);

        holder.mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialFavoriteButton favoriteButton = (MaterialFavoriteButton) v;
                boolean isFavorite = favoriteButton.isFavorite();
                if (!isFavorite) {
                    favoritesDB.createMovie(holder.movie, userId);
                } else {
                    favoritesDB.deleteMovie(holder.movie.getImdbID(), userId);
                }

                favoriteButton.setFavorite(!isFavorite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTitle;
        public final TextView mDirector;
        public final TextView mYear;
        public final MaterialFavoriteButton mFavorite;
        public Movie movie;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.thumbnail);
            mTitle = (TextView) view.findViewById(R.id.movie_title);
            mDirector = (TextView) view.findViewById(R.id.movie_director);
            mYear = (TextView) view.findViewById(R.id.movie_year);
            mFavorite = (MaterialFavoriteButton) view.findViewById(R.id.movie_favorite);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
