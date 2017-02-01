package com.eleysos.popularmoviess1.utilities;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONException;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class MoviesJsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the movies received.
     * <p/>
     *
     * @param moviesJsonStr JSON response from server
     *
     * @return Array of Strings describing movies
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static String[] getSimpleMoviesStringsFromJson(Context context, String moviesJsonStr)
            throws JSONException {

        /* String array to hold each movie */
        String[] parsedMoviesData = null;

//        {   "adult":false,
//            "backdrop_path":"/wSJPjqp2AZWQ6REaqkMuXsCIs64.jpg",
//            "belongs_to_collection":null,
//            "budget":63000000,
//            "genres":[{"id":18,"name":"Drama"}],
//            "homepage":"http://www.foxmovies.com/movies/fight-club",
//            "id":550,
//            "imdb_id":"tt0137523",
//            "original_language":"en",
//            "original_title":"Fight Club",
//            "overview":"A ticking-time-bomb...",
//            "popularity":6.79377,
//            "poster_path":"/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
//            "production_companies":[{"name":"Regency Enterprises","id":508},{"name":"Fox 2000 Pictures","id":711}],
//            "production_countries":[{"iso_3166_1":"DE","name":"Germany"},{"iso_3166_1":"US","name":"United States of America"}],
//            "release_date":"1999-10-14",
//            "revenue":100853753,
//            "runtime":139,
//            "spoken_languages":[{"iso_639_1":"en","name":"English"}],
//            "status":"Released",
//            "tagline":"How much can you know about yourself if you've never been in a fight?",
//            "title":"Fight Club",
//            "video":false,
//            "vote_average":8.1,
//            "vote_count":6178
//        }

        return parsedMoviesData;
    }

}