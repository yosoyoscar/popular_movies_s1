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

    private static final String MOVIES_URL = "https://api.themoviedb.org/3/movie/popular";

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
        Uri builtUri = Uri.parse(MOVIES_URL).buildUpon()
                .appendQueryParameter(APIKEY_PARAM, apiKey)
                // TODO: set order param
                //.appendQueryParameter(ORDER_PARAM, order)
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