package com.eleysos.popularmoviess1.utilities;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public static Movie[] getSimpleMoviesStringsFromJson(Context context, String moviesJsonStr)
            throws JSONException {

        final String MDB_RESULTS = "results";
        final String MDB_ID = "id";
        final String MDB_TITLE = "title";
        final String MDB_ORIGINAL_TITLE = "original_title";
        final String MDB_POSTER_PATH = "poster_path";
        final String MDB_OVERVIEW = "overview";
        final String MDB_VOTE_AVERAGE = "vote_average";
        final String MDB_RELEASE_DATE = "release_date";

        /* String array to hold each movie */
        Movie[] parsedMoviesData = null;

        JSONObject moviesJson = new JSONObject(moviesJsonStr);
        JSONArray moviesArray = moviesJson.getJSONArray(MDB_RESULTS);

        parsedMoviesData = new Movie[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject movie = moviesArray.getJSONObject(i);

            parsedMoviesData[i] = new Movie(
                    movie.getInt(MDB_ID),
                    movie.getString(MDB_TITLE),
                    movie.getString(MDB_ORIGINAL_TITLE),
                    movie.getString(MDB_POSTER_PATH),
                    movie.getString(MDB_OVERVIEW),
                    movie.getDouble(MDB_VOTE_AVERAGE),
                    movie.getString(MDB_RELEASE_DATE)
                    );

        }

        return parsedMoviesData;
    }

}