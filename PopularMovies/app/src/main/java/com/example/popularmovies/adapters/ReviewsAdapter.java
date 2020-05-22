package com.example.popularmovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmovies.R;
import com.example.popularmovies.models.Reviews;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private final Context mContext;
    private List<Reviews> mReviews;
    private final ReviewClickListener mClickListener;

    public ReviewsAdapter(Context context, ReviewClickListener clickListener) {
        mContext = context;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.reviews_display, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Reviews review = mReviews.get(position);
        String content = review.getContent();
        String author = review.getAuthor();

        holder.reviewContent.setText(content);
        holder.reviewAuthor.setText(author);

    }

    @Override
    public int getItemCount() {
        if (mReviews == null)
            return 0;
        else
            return mReviews.size();
    }

    public void setTrailers(List<Reviews> reviews) {
        this.mReviews = reviews;
        notifyDataSetChanged();
    }

    public interface ReviewClickListener {
        void onReviewClickListener(String reviewURL);
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView reviewContent;
        final TextView reviewAuthor;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewContent = itemView.findViewById(R.id.reviews_content_tv);
            reviewAuthor = itemView.findViewById(R.id.reviews_author_tv);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            String review = mReviews.get(getAdapterPosition()).getUrl();
            mClickListener.onReviewClickListener(review);
        }
    }
}
