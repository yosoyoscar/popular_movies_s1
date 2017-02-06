# popular_movies_s1
Udacity Associate Android Developer Fast Track Project 1

Project Summary

Most of us can relate to kicking back on the couch and enjoying a movie with friends and family. In this project, we’ll build an app to allow users to discover the most popular movies playing.

This app will:

	Upon launch, present the user with an grid arrangement of movie posters.
	
	Allow your user to change sort order via a setting:
		The sort order can be by most popular, or by top rated

	Allow the user to tap on a movie poster and transition to a details screen with additional information such as:
		original title
		movie poster image thumbnail
		A plot synopsis (called overview in the api)
		user rating (called vote_average in the api)
		release date


THEMOVIEDB API KEY: this app needs a https://www.themoviedb.org/ api key, please make sure to write yours in:

 com.eleysos.popularmoviess1.utilities.NetworkUtils.java
 
    private static final String apiKey = "";