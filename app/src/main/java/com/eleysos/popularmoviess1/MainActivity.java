package com.eleysos.popularmoviess1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eleysos.popularmoviess1.utilities.Movie;
import com.eleysos.popularmoviess1.utilities.MoviesJsonUtils;
import com.eleysos.popularmoviess1.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler{

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    String order = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null && savedInstanceState.containsKey("order")) {
            order = savedInstanceState.getString("order");
        }

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);

        /* This TextView is used to display errors and will be hidden if there are no errors */
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 3);
        mRecyclerView.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        //mRecyclerView.setHasFixedSize(true);

        /*
         * The MoviesAdapter is responsible for linking our movies data with the Views that
         * will end up displaying our movies data.
         */
        mMoviesAdapter = new MoviesAdapter(this);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(mMoviesAdapter);

        /*
         * The ProgressBar that will indicate to the user that we are loading data. It will be
         * hidden when no data is loading.
         *
         * Please note: This so called "ProgressBar" isn't a bar by default. It is more of a
         * circle. We didn't make the rules (or the names of Views), we just follow them.
         */
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        /* Once all of our views are setup, we can load the movies data. */
        loadMoviesData(order);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("order", order);
        super.onSaveInstanceState(outState);
    }

    /**
     * This method will get the user's preferences, and then tell some
     * background method to get the movie data in the background.
     */
    private void loadMoviesData(String order) {

        showMoviesDataView();

        //String order = PopularMoviesPreferences.getPreferredOrder(this);
        new FetchMoviesTask().execute(order);
    }

    /**
     * This method is overridden by our MainActivity class in order to handle RecyclerView item
     * clicks.
     *
     * @param movie The movie clicked by the user
     */
    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("movie", movie);
        startActivity(intentToStartDetailActivity);
    }

    /**
     * This method will make the View for the movies data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showMoviesDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the movies data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the movies
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... params) {

            /* If there's no params, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String order = params[0];
            URL weatherRequestUrl = NetworkUtils.buildUrl(order);

            try {
                String jsonMoviesResponse = NetworkUtils
                        .getResponseFromHttpUrl(weatherRequestUrl);

                //Log.d("PopularMovies", "moviedb response: " + jsonMoviesResponse);

                Movie[] simpleJsonMoviesData = MoviesJsonUtils
                        .getSimpleMoviesStringsFromJson(MainActivity.this, jsonMoviesResponse);

                //Log.d("PopularMovies", "parsedMovies: " + simpleJsonMoviesData);

                return simpleJsonMoviesData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] moviesData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (moviesData != null) {
                showMoviesDataView();
                mMoviesAdapter.setMoviesData(moviesData);
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.movies, menu);
        /* Set title stating what movies are shown */
        MenuItem mText = menu.findItem(R.id.action_selected);
        if (order.equals("popular")){
            mText.setTitle(getString(R.string.message_popular));
        }
        else{
            mText.setTitle(getString(R.string.message_top_rated));
        }
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ActionMenuItemView mText = (ActionMenuItemView) findViewById(R.id.action_selected);
        if (id == R.id.action_refresh) {
            mMoviesAdapter.setMoviesData(null);
            loadMoviesData(order);
            return true;
        }

        if (id == R.id.action_popular) {
            order = "popular";
            mMoviesAdapter.setMoviesData(null);
            mText.setTitle(getString(R.string.message_popular));
            loadMoviesData(order);
            return true;
        }

        if (id == R.id.action_top_rated) {
            order = "top_rated";
            mMoviesAdapter.setMoviesData(null);
            mText.setTitle(getString(R.string.message_top_rated));
            loadMoviesData(order);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
