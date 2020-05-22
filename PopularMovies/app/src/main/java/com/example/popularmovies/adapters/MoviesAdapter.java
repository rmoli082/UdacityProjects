package com.example.popularmovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.models.Movie;
import com.example.popularmovies.R;
import com.example.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>{

    private List<Movie> mMoviesList;
    private final ListItemClickListener mClickListener;

    public interface ListItemClickListener {

        void onListItemClick(int position);
    }

    public MoviesAdapter(ListItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new MoviesAdapterViewHolder(inflater.inflate(R.layout.poster_display, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesAdapterViewHolder holder, final int position) {
        String posterPath = mMoviesList.get(position).getPosterPath();
        Picasso.get().load(NetworkUtils.buildPosterURL(posterPath))
                .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground)
                .into(holder.mMoviePoster);

    }

    @Override
    public int getItemCount() {
        if (mMoviesList == null) {
            return 0;
        } else
            return mMoviesList.size();
    }

    public void setMoviesList(List<Movie> moviesList) {
        mMoviesList = moviesList;
        notifyDataSetChanged();
    }

    public List<Movie> getMoviesList() {
        return mMoviesList;
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView mMoviePoster;

        MoviesAdapterViewHolder(View v) {
            super(v);
            mMoviePoster = v.findViewById(R.id.poster_display_iv);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onListItemClick(getAdapterPosition());
        }
    }

}
