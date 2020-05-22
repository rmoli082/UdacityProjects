package com.example.popularmovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.R;
import com.example.popularmovies.models.Videos;

import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {

    private final Context mContext;
    private List<Videos> mTrailers;
    private final TrailerClickListener mClickListener;

    public TrailersAdapter(Context context, TrailerClickListener clickListener) {
        mContext = context;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.trailers_display, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Videos video = mTrailers.get(position);
        String title = video.getName();

        holder.trailerTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        if (mTrailers == null)
            return 0;
        else
            return mTrailers.size();
    }


    public void setTrailers(List<Videos> mTrailers) {
        this.mTrailers = mTrailers;
        notifyDataSetChanged();
    }

    public interface TrailerClickListener {
        void onTrailerClickListener(String itemKey);
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       final TextView trailerTitle;

       TrailerViewHolder(@NonNull View itemView) {
           super(itemView);
           trailerTitle = itemView.findViewById(R.id.trailers_title_tv);
           itemView.setOnClickListener(this);
       }


        @Override
        public void onClick(View v) {
           String itemKey = mTrailers.get(getAdapterPosition()).getKey();
           mClickListener.onTrailerClickListener(itemKey);
        }
    }
}
