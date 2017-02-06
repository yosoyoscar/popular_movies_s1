package com.eleysos.popularmoviess1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.eleysos.popularmoviess1.utilities.Movie;
import com.squareup.picasso.Picasso;

import static com.eleysos.popularmoviess1.utilities.NetworkUtils.POSTERS_URL;

public class DetailActivity extends AppCompatActivity {

    private Movie selectedMovie;
    private TextView movieTitle;
    private ImageView moviePoster;
    private TextView movieOverview;
    private TextView movieVoteAverage;
    private TextView movieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieTitle = (TextView) findViewById(R.id.movie_title);
        moviePoster = (ImageView) findViewById(R.id.tv_movie_poster);
        movieOverview = (TextView) findViewById(R.id.movie_overview);
        movieVoteAverage = (TextView) findViewById(R.id.movie_vote_average);
        movieReleaseDate = (TextView) findViewById(R.id.movie_release_date);

        Intent intentThatStartedThisActivity = getIntent();

        // Display the movie selected in MainActivity
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("movie")) {
                Movie selectedMovie = (Movie) intentThatStartedThisActivity.getParcelableExtra("movie");
                movieTitle.setText(selectedMovie.getOriginalTitle());
                //moviePosterPath.setText(selectedMovie.getPosterPath());
                String imageUrl = POSTERS_URL + selectedMovie.getPosterPath();
                Picasso.with(this).load(imageUrl).into(moviePoster);
                movieOverview.setText(selectedMovie.getOverview());
                movieVoteAverage.setText("Vote average: " + String.valueOf(selectedMovie.getVote_average()));
                movieReleaseDate.setText("Release date: " + selectedMovie.getReleaseDate());
            }
        }
    }
}
