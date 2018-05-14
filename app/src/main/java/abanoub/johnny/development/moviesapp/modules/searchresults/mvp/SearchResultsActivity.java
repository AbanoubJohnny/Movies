package abanoub.johnny.development.moviesapp.modules.searchresults.mvp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import abanoub.johnny.development.moviesapp.R;
import abanoub.johnny.development.moviesapp.application.dagger.components.ApplicationComponent;
import abanoub.johnny.development.moviesapp.modules.home.mvp.MovieCardsCallBack;
import abanoub.johnny.development.moviesapp.modules.home.mvp.adapters.MoviesAdapter;
import abanoub.johnny.development.moviesapp.modules.movie.mvp.MovieActivity;
import abanoub.johnny.development.moviesapp.modules.searchresults.dagger.DaggerSearchResultsComponent;
import abanoub.johnny.development.moviesapp.modules.searchresults.dagger.SearchResultsComponent;
import abanoub.johnny.development.moviesapp.modules.searchresults.dagger.SearchResultsModule;
import abanoub.johnny.development.moviesapp.modules.searchresults.mvp.contract.SearchResultsContract;
import abanoub.johnny.development.moviesapp.mvp.bases.BaseActivity;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage.Movie;
import abanoub.johnny.development.moviesapp.mvp.models.local.Constants;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Abanoub Johnny on 12/5/2018.
 */

public class SearchResultsActivity extends BaseActivity<SearchResultsPresenter, SearchResultsViewModel> implements SearchResultsContract.ISearchResultsView {

    @BindView(R.id.toolbar_title)
    TextView toolbarTtle;


    @BindView(R.id.search_results_recycler)
    RecyclerView searchResultsMoviesRecyclerview;

    MoviesAdapter mAdapter;
    String searchString;

    List<Movie> resultMovies;

    @Override
    protected int getContentView() {
        return R.layout.activity_searchresults;
    }

    @Override
    public SearchResultsViewModel setViewModel() {
        return ViewModelProviders.of(this).get(SearchResultsViewModel.class);
    }

    @Override
    protected void resolveDaggerDependency(ApplicationComponent appComponent, SearchResultsViewModel viewModel) {
        SearchResultsComponent searchresultsComponent = DaggerSearchResultsComponent
                .builder()
                .applicationComponent(appComponent)
                .searchResultsModule(new SearchResultsModule(this))
                .build();
        searchresultsComponent.inject(this);
        searchresultsComponent.inject(viewModel);


    }

    @Override
    public void onViewReady(Bundle savedInstanceState, Intent intent) {


        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.containsKey(Constants.SearchString)) {
            searchString = bundle.getString(Constants.SearchString);
        }
        if (getmViewModel().getSearchString() == null && searchString != null) {
            getmViewModel().setSearchString(searchString);
        }
        if (getmViewModel().getResultMovies() != null && !getmViewModel().getResultMovies().isEmpty()) {
            if (resultMovies == null || resultMovies.isEmpty()) {
                resultMovies = new ArrayList<>();
                resultMovies.addAll(getmViewModel().getResultMovies());
                setupResultsRecyclerView();
            }
        } else {
            showResults();
        }
        toolbarTtle.setText(searchString);

    }

    private void showResults() {
        getmViewModel().getResults().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (resultMovies == null || resultMovies.isEmpty()) {
                    resultMovies = new ArrayList<>();
                    resultMovies.addAll(movies);
                    setupResultsRecyclerView();
                } else {
                    int oldCount = resultMovies.size();
                    resultMovies.addAll(movies);
                    mAdapter.notifyItemRangeChanged(oldCount, resultMovies.size());
                }
            }
        });
    }

    public void setupResultsRecyclerView() {
        if (resultMovies == null) {
            resultMovies = new ArrayList<>();
        }

        if (mAdapter == null) {
            mAdapter = new MoviesAdapter(resultMovies, this, movieCardsCallBack);
        }

        if (searchResultsMoviesRecyclerview.getAdapter() == null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                searchResultsMoviesRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
            } else {
                searchResultsMoviesRecyclerview.setLayoutManager(new GridLayoutManager(this, 4));
            }
            searchResultsMoviesRecyclerview.setItemAnimator(new DefaultItemAnimator());
            searchResultsMoviesRecyclerview.setAdapter(mAdapter);
        }
        searchResultsMoviesRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    if (getmViewModel().isMore())
                        showResults();
                }

            }
        });

    }

    @OnClick({R.id.back_btn})
    public void backClicked(View view) {
        if (view.getId() == R.id.back_btn)
            finishActivity();
    }


    MovieCardsCallBack movieCardsCallBack = new MovieCardsCallBack() {
        @Override
        public void openDetails(Movie movie) {
            Bundle args = new Bundle();
            args.putSerializable(Constants.MovieToShow, movie);
            Intent intent = getIntent(MovieActivity.class);
            intent.putExtras(args);
            startActivity(intent);
        }

        @Override
        public void openDetails(MovieDetails movieDetails) {

            Bundle args = new Bundle();
            args.putSerializable(Constants.MovieDetails, movieDetails);
            Intent intent = getIntent(MovieActivity.class);
            intent.putExtras(args);
            startActivity(intent);
        }
    };
}
