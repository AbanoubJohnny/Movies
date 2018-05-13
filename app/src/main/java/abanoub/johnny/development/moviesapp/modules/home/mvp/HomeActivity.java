package abanoub.johnny.development.moviesapp.modules.home.mvp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.modules.home.dagger.DaggerHomeComponent;
import abanoub.johnny.development.moviesapp.modules.home.dagger.HomeComponent;
import abanoub.johnny.development.moviesapp.modules.home.dagger.HomeModule;
import abanoub.johnny.development.moviesapp.modules.home.mvp.adapters.FavouriteMoviesAdapter;
import abanoub.johnny.development.moviesapp.modules.home.mvp.adapters.MoviesAdapter;
import abanoub.johnny.development.moviesapp.modules.home.mvp.contract.HomeContract;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.MovieActivity;
import abanoub.johnny.development.moviesapp.mvp.bases.BaseActivity;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import butterknife.BindView;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

public class HomeActivity extends BaseActivity<HomePresenter, HomeViewModel> implements HomeContract.IHomeView {


    @BindView(R.id.popular_recycler)
    RecyclerView popularMoviesRecyclerview;
    @BindView(R.id.popular_linearlayout)
    LinearLayout popularMoviesLinearlayout;

    @BindView(R.id.toprated_recycler)
    RecyclerView topratedMoviesRecyclerview;
    @BindView(R.id.toprated_linearlayout)
    LinearLayout topratedMoviesLinearlayout;

    @BindView(R.id.favourites_recycler)
    RecyclerView favouritesMoviesRecyclerview;
    @BindView(R.id.favourites_linearlayout)
    LinearLayout favouritesMoviesLinearlayout;

    MoviesAdapter topRatedAdapter,popularAdapter;
    FavouriteMoviesAdapter favouritesAdapter;
    List<Movie> topratedMovies,popularMovies ;
    List<MovieDetails> myFavouriteMovies ;
    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public HomeViewModel setViewModel() {
        return ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    protected void resolveDaggerDependency(ApplicationComponent appComponent, HomeViewModel viewModel) {
        HomeComponent homeComponent = DaggerHomeComponent
                .builder()
                .applicationComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build();
        homeComponent.inject(this);
        homeComponent.inject(viewModel);


    }
    @Override
    public void onViewReady(Bundle savedInstanceState, Intent intent) {

        if (getmViewModel().getPopularMovies()!=null&&!getmViewModel().getPopularMovies().isEmpty()){
            if (popularMovies==null||popularMovies.isEmpty()) {
                popularMovies = new ArrayList<>();
                popularMovies.addAll(getmViewModel().getPopularMovies());
                setupPopularRecyclerView();
            }
        }
        else {
            getmViewModel().getPopular().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(@Nullable List<Movie> movies) {
                    if (popularMovies==null||popularMovies.isEmpty()) {
                        popularMovies = new ArrayList<>();
                        popularMovies.addAll(movies);
                        setupPopularRecyclerView();
                    }else {
                        int oldCount = popularMovies.size();
                        popularMovies.addAll(movies);
                        popularAdapter.notifyItemRangeChanged(oldCount,popularMovies.size());
                    }
                }
            });
        }
        if (getmViewModel().getTopratedMovies()!=null&&!getmViewModel().getTopratedMovies().isEmpty()){
            if (topratedMovies==null||topratedMovies.isEmpty()) {
                topratedMovies = new ArrayList<>();
                topratedMovies.addAll(getmViewModel().getTopratedMovies());
                setupTopRatedRecyclerView();
            }
        }
        else {
            getmViewModel().getTopRated().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(@Nullable List<Movie> movies) {
                    if (topratedMovies==null||topratedMovies.isEmpty()) {
                        topratedMovies = new ArrayList<>();
                        topratedMovies.addAll(getmViewModel().getTopratedMovies());
                        setupTopRatedRecyclerView();
                    }else {
                        int oldCount = topratedMovies.size();
                        topratedMovies.addAll(movies);
                        topRatedAdapter.notifyItemRangeChanged(oldCount,topratedMovies.size());
                    }
                }
            });
        }
        getmViewModel().getMovies().observe(this, new Observer<List<MovieDetails>>() {
            @Override
            public void onChanged(@Nullable List<MovieDetails> movieDetails) {

                if (movieDetails!=null&& !movieDetails.isEmpty()) {
                    setupFavouritesRecyclerView();
                    if (myFavouriteMovies.isEmpty()) {
                        myFavouriteMovies.addAll(movieDetails);
                        favouritesAdapter.notifyDataSetChanged();
                    }
                    else{
                        myFavouriteMovies.clear();
                        favouritesAdapter.notifyDataSetChanged();
                        myFavouriteMovies.addAll(movieDetails);
                        favouritesAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    myFavouriteMovies.clear();
                    favouritesAdapter.notifyDataSetChanged();
                    favouritesMoviesLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    public void setupTopRatedRecyclerView(){

        if (topratedMovies==null) {
            topratedMovies = new ArrayList<>();
        }

        if (topRatedAdapter==null){
            topRatedAdapter = new MoviesAdapter(topratedMovies,this,movieCardsCallBack);
        }

        if (topratedMoviesRecyclerview.getAdapter()==null) {
            topratedMoviesRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//            topratedMoviesRecyclerview.setItemAnimator(new DefaultItemAnimator());
            topratedMoviesRecyclerview.setAdapter(topRatedAdapter);
        }
    }
    public void setupPopularRecyclerView(){
        if (popularMovies==null) {
            popularMovies = new ArrayList<>();
        }

        if (popularAdapter==null){
            popularAdapter = new MoviesAdapter(popularMovies,this,movieCardsCallBack);
        }

        if (popularMoviesRecyclerview.getAdapter()==null) {
            popularMoviesRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//            popularMoviesRecyclerview.setItemAnimator(new DefaultItemAnimator());
            popularMoviesRecyclerview.setAdapter(popularAdapter);
        }

    }
    public void setupFavouritesRecyclerView(){
        favouritesMoviesLinearlayout.setVisibility(View.VISIBLE);
        if (myFavouriteMovies==null) {
            myFavouriteMovies = new ArrayList<>();
        }

        if (favouritesAdapter==null){
            favouritesAdapter = new FavouriteMoviesAdapter(myFavouriteMovies,this,movieCardsCallBack);
        }

        if (favouritesMoviesRecyclerview.getAdapter()==null) {
            favouritesMoviesRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            favouritesMoviesRecyclerview.setItemAnimator(new DefaultItemAnimator());
            favouritesMoviesRecyclerview.setAdapter(favouritesAdapter);
        }
    }

    MovieCardsCallBack movieCardsCallBack = new MovieCardsCallBack() {
        @Override
        public void openDetails(Movie movie) {
            Bundle args = new Bundle();
            args.putSerializable(Constants.MovieToShow,movie);
            Intent intent = getIntent(MovieActivity.class);
            intent.putExtras(args);
            startActivity(intent);
        }

        @Override
        public void openDetails(MovieDetails movieDetails) {

            Bundle args = new Bundle();
            args.putSerializable(Constants.MovieDetails,movieDetails);
            Intent intent = getIntent(MovieActivity.class);
            intent.putExtras(args);
            startActivity(intent);
        }
    };



}
