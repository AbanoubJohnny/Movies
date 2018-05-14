package abanoub.johnny.development.moviesapp.modules.movie.mvp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import abanoub.johnny.development.moviesapp.BuildConfig;
import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.modules.movie.dagger.DaggerMovieComponent;
import abanoub.johnny.development.moviesapp.modules.movie.dagger.MovieComponent;
import abanoub.johnny.development.moviesapp.modules.movie.dagger.MovieModule;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.contract.MovieContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BaseActivity;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

public class MovieActivity extends BaseActivity<MoviePresenter, MovieViewModel> implements MovieContract.IMovieView {


    @BindView(R.id.movie_image_imageview)
    ImageView movieImageImageview;
    @BindView(R.id.movie_cover_image_imageview)
    ImageView movieCoverImageImageview;
    @BindView(R.id.movie_title_textview)
    TextView movieNameTextview;
    @BindView(R.id.movie_rate_textview)
    TextView movieRateTextview;
    @BindView(R.id.movie_date_textview)
    TextView movieDateextview;
    @BindView(R.id.movie_overview_textview)
    TextView movieOverviewText;
    @BindView(R.id.fav_imageview)
    ImageView favouritebutton;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.container_relativelayout)
    RelativeLayout container_relativelayout;
    MovieDetails movieDetails;
    Movie movie;
    boolean isFaourite = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_moviedetails;
    }

    @Override
    public MovieViewModel setViewModel() {
        return ViewModelProviders.of(this).get(MovieViewModel.class);
    }

    @Override
    protected void resolveDaggerDependency(ApplicationComponent appComponent, MovieViewModel viewModel) {
        MovieComponent movieComponent = DaggerMovieComponent
                .builder()
                .applicationComponent(appComponent)
                .movieModule(new MovieModule(this))
                .build();
        movieComponent.inject(this);
        movieComponent.inject(viewModel);


    }


    @Override
    public void onViewReady(Bundle savedInstanceState, Intent intent) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.containsKey(Constants.MovieDetails)) {
                movieDetails = (MovieDetails) bundle.getSerializable(Constants.MovieDetails);
                getmViewModel().setMovieDetails(movieDetails);
                isFaourite = true;
                getmViewModel().setFavourite(true);
            } else if (bundle.containsKey(Constants.MovieToShow)) {
                movie = (Movie) bundle.getSerializable(Constants.MovieToShow);
            }
        }
        movieDetails = getmViewModel().getMovieDetails();
        if (movieDetails == null) {
            getmViewModel().getMovie(movie).observe(this, new Observer<MovieDetails>() {
                @Override
                public void onChanged(@Nullable MovieDetails movieDetailsResponse) {
                    movieDetails = movieDetailsResponse;
                    bind();
                }
            });
        }
        else {
            bind();
        }
    }

    public void bind() {
        if (movieDateextview != null) {
            container_relativelayout.setVisibility(View.VISIBLE);
            Glide.with(this).load(BuildConfig.Base_Image_URL + movieDetails.getBackdropPath())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.color.off_white).into(movieCoverImageImageview);
            Glide.with(this).load(BuildConfig.Base_Image_URL + movieDetails.getPosterPath())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.color.off_white).into(movieImageImageview);
            movieNameTextview.setText(movieDetails.getTitle());
            movieRateTextview.setText(movieDetails.getVoteAverage() + "");
            movieDateextview.setText(movieDetails.getReleaseDate());
            movieOverviewText.setText(movieDetails.getOverview());

            if (isFaourite) {
                favouritebutton.setImageResource(R.drawable.fav);
                isFaourite = true;
            }
        } else {
            Log.e("Moviedetailsscreen", "movie object is null object");
        }
    }

    @OnClick({R.id.fav_imageview,R.id.back_btn})
    public void onClick(View view){
        if (view.getId()==R.id.fav_imageview) {
            if (isFaourite) {
                getmViewModel().removeMovieFromFavourites(movieDetails);
                isFaourite = false;
                favouritebutton.setImageResource(R.drawable.not_fav);
            } else {
                getmViewModel().addMovieToFavourites(movieDetails);
                isFaourite = true;
                favouritebutton.setImageResource(R.drawable.fav);

            }
        }
        else {
            finishActivity();
        }
    }

}
