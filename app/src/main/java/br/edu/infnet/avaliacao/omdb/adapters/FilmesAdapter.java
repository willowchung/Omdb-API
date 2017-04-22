package br.edu.infnet.avaliacao.omdb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.infnet.avaliacao.omdb.entidades.Movie;

/**
 * Created by Willow on 22/04/2017.
 */

public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.FilmeViewHolder> {
    private List<Movie> movies;

    public FilmesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public FilmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /*// create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ...
        ViewHolder vh = new ViewHolder(v);
        return vh;*/

        return null;
    }

    @Override
    public void onBindViewHolder(FilmeViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        //return mDataset.length;
        return 0;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class FilmeViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public FilmeViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}
