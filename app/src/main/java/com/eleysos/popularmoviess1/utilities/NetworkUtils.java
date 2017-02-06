package com.eleysos.popularmoviess1.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the weather servers.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String POPULAR_MOVIES_URL = "https://api.themoviedb.org/3/movie/popular";
    private static final String TOP_RATED_MOVIES_URL = "https://api.themoviedb.org/3/movie/top_rated";
    public static final String POSTERS_URL = "http://image.tmdb.org/t/p/w185";

    /* The format we want our API to return */
    private static final String format = "json";
    // TODO: remove api key
    /* API key */
    private static final String apiKey = "";

    final static String QUERY_PARAM = "q";
    final static String APIKEY_PARAM = "api_key";


    /**
     * Builds the URL used to talk to the themoviedb.org server using an order preference
     *
     * @param order The order that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String order) {
        String orderedUrl = POPULAR_MOVIES_URL;
        if (order.equals("top_rated")){
            orderedUrl = TOP_RATED_MOVIES_URL;
        }
        Uri builtUri = Uri.parse(orderedUrl).buildUpon()
                .appendQueryParameter(APIKEY_PARAM, apiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * Builds the URL used to talk to the weather server using latitude and longitude of a
     * location.
     *
     * @param lat The latitude of the location
     * @param lon The longitude of the location
     * @return The Url to use to query the weather server.
     */
    public static URL buildUrl(Double lat, Double lon) {
        /** This will be implemented in a future lesson **/
        return null;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}