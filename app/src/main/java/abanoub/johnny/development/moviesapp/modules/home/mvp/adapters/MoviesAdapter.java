package abanoub.johnny.development.moviesapp.modules.home.mvp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.modules.home.mvp.MovieCardsCallBack;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.viewholders.MovieCardViewHolder;

public class MoviesAdapter extends RecyclerView.Adapter<MovieCardViewHolder> {

    private List<Movie> moviesList = new ArrayList<>();
    private Context mContext;
    private MovieCardsCallBack movieCardsCallBack;

    public MoviesAdapter(List<Movie> movies, Context context,MovieCardsCallBack movieCardsCallBack){
        this.moviesList = movies;
        this.mContext = context;
        this.movieCardsCallBack = movieCardsCallBack;
    }

    @NonNull
    @Override
    public MovieCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);
        return new MovieCardViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieCardViewHolder holder, final int listPosition) {
        Movie movie  = moviesList.get(listPosition);
        holder.bind(movie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieCardsCallBack.openDetails(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
