package abanoub.johnny.development.moviesapp.mvp.models.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import abanoub.johnny.development.moviesapp.BuildConfig;
import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieCardViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "MovieCardVH";
    private Context mContext;
    @BindView(R.id.movie_image)
    ImageView movieImageView;
    @BindView(R.id.movie_name_textview)
    TextView movieNameText;
    @BindView(R.id.movie_rate_textview)
    TextView movieRateText;
    @BindView(R.id.movie_date_textview)
    TextView movieDateText;

    public MovieCardViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mContext = context;
    }

    public void bind(Movie movie){
        if (movie!=null) {
            Glide.with(mContext).load(BuildConfig.Base_Image_URL+movie.getPosterPath()).asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.color.off_white).into(movieImageView);
            movieNameText.setText(movie.getTitle());
            movieRateText.setText(movie.getVoteAverage()+"");
            movieDateText.setText(movie.getReleaseDate());
        }
        else {
            Log.e(TAG,"movie object is null object");
        }
    }
    public void bind(MovieDetails movie){
        if (movie!=null) {
            Glide.with(mContext).load(BuildConfig.Base_Image_URL+movie.getPosterPath())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.color.off_white).into(movieImageView);
            movieNameText.setText(movie.getTitle());
            movieRateText.setText(movie.getVoteAverage()+"");
            movieDateText.setText(movie.getReleaseDate());
        }
        else {
            Log.e(TAG,"movie object is null object");
        }
    }
}
