# Movie-Recommendation-System
Allows user to rate movies then recommends movies based on the user ratings. 

Basic Overview: 

This code can be tested at the following URL:
https://www.dukelearntoprogram.com//capstone/recommender.php?id=JanptVoKl9Qb5w

The above website will prompt the user to rate a set of movies. Each time the website is loaded a different set of movies will be prompted for rating. After rating all movies and clicking ‘Submit Ratings’, the user will be recommended movies based on their ratings. 

The contents of this project only include the back-end code for the recommendation system. Duke University hosts the front-end for this code. 

Technical Overview:

When the website is launched, the ‘getItemstoRate()’ method from the ‘RecommendationRunner’ class.
First a MovieDatebase object is initialized using a CSV file of movies which includes movieID, publish year, genre, title, poster (image url link), duration in minutes, country, and director. 
The MovieDatabase object is then filtered. (in the submitted code, this was set to genre of ‘Action’)
Total number of movies to display is controlled by the ‘numMovies’ variable. 
From the filtered list, movies are selected at random (duplicates prevented) and added to an ArrayList which is then outputted to the host website to be displayed on the webpage. 
User will be prompted to rate movies from the output of ‘getItemstoRate()’ on the webpage. After completing rating, they should click ‘Submit Ratings’ which calls the ‘printRecommendationsFor(String webRaterID)’ method. 
‘printRecommendationsFor(String webRaterID)’ created a FourthRatings object and calls method ‘getSimilarRatingsByFilter’ which uses dot product scores of the user ratings compared to all other raters to determine which raters rated movies most similar to give recommendations. It outputs an HTML table with the recommendations which is then displayed on the webpage. Max and min number of recommendations is controlled in the ‘printRecommendationsFor(String webRaterID)’ method. 

Future Optimizations:
Create the front end for this application so it can be launched without Duke University. 
Allow user input to select genre or and filter before movies are displayed for rating. 
Allow user input to control the number of recommendations. 
Use an online database of ratings instead of a static CSV file (ex. Netflix) 
Develop different similarity score algorithms 

Main Classes:

Movie
Class used to store information regarding a single movie. 
Includes id, title, year, genres, director, country, poster, and duration in minutes
Includes getter methods for all variables
Methods
Getters (for all private variables)
toString
equals
Rating
Class used to represent one rating data (movie:rating)


Rater (Interface)
Class used to represent one rater and all their ratings. 
NOTE: PlainRater not used, only EfficientRater used in final code. Clean up required. 

FirstRatings
Class is used to load a csv file containing movies into Java objects,and also load a csv file containing raters and their ratings into Java objects. 


Public Methods:
loadMovies(String filename): 
Returns ArrayList of Movie objects which was loaded from the CSV file of movies.
loadRaters(String filename):
Returns ArrayList of EfficientRater objects which was loaded from CSV file of Raters and their Ratings

SecondRatings
This class loads movies and ratings using the FirstRating class to do calculations focusing on computing averages on movie ratings. 
NOTE: this class is not used in final code. Clean up required. 


Public Methods
getMovieSize()
Returns total number of movies
getRaterSize()
Returns total number of raters
getAverageByID(String id, int minimalRaters)
Returns average rating for specified movie ID given the number of ratings meets the minimal threshold for # of ratings
getAverageRatings(int minimalRaters)
Returns an ArrayList of Rating objects for all movies that meet the specified minimum threshold for # of ratings
getTitle(String id)
Returns title of specified movie ID
getID(String title)
Returns ID of specified movie title


MovieDatabase
This class uses FirstRatings class to load movies then builds a HashMap an an efficient way to get information about movies. 
It stores movie information in a HashMap for fast lookup of movie information given a movie ID.
The class also allows filtering movies based on queries. All methods and fields in the class are static.
Public Methods
initialize(String moviefile
Builds HashMap of Movie objects using CSV file of movies. 
containsID(String id)
Returns boolean to see if movie exists for specified movie ID
getYear(String id)
Returns publish year for the specified movie ID
getGenres(String id)
Returns genre for the specified movie ID
String getTitle(String id)
Returns title for the specified movie ID
getMovie(String id)
Returns movie Object for the specified movie ID
getPoster(String id)
Returns movie poster image link for the specified movie ID
getMinutes(String id)
Returns movie duration in minutes for the specified movie ID
getCountry(String id)
Returns publishing country for the specified movie ID
getDirector(String id)
Returns director for the specified movie ID
size()
Returns number of movies
filterBy(Filter f)
Returns ArrayList of movies that meet the specified filter criteria
Filter (Interface)
The interface Filter has only one signature for the method satisfies.
The method satisfies has one String parameter named id representing a movie ID.
This method returns true if the movie satisfies the criteria in the method and returns false otherwise.
Classes that implement this interface:
DirectorsFilter
Filter MovieDatabase object by director
GenreFilter
Filter MovieDatabase object by genre
MinutesFilter
Filter MovieDatabase object by min/max duration in minutes
YearsAfterFilter
Filter MovieDatabase object by movies that are after year specified
TrueFilter
Used to select all movies from the MovieDatabase object, it always returns true.  
AllFilter
Used to input multiple filters at once\

ThirdRatings
This class loads movies and ratings using the FirstRating class to do calculations focusing on computing averages on movie ratings. 
Leverages the MovieDatabase object for filtering, making it more efficient than SecondRatings. 
NOTE: this class is not used in final code. Clean up required. 

RaterDatabase
This class is an efficient way to get information about raters.
A HashMap named ourRaters that maps a rater ID String to a Rater object that includes all the movie ratings made by this rater.
Public Methods
addRatings(String filename)
Obtains rater ID, movie ID, and rating from CSV file and calls the addRaterRating method to add one rater and their movie rating to the HashMap.
addRaterRating(String raterID, String movieID, double rating)
This function can be used to add one rater and their movie rating to the database.  Ensures no duplicate ratings added.
getRater(String id)
Returns a Rater that has this ID
getRaters()
Returns an ArrayList of Raters from the database
size()
Returns the number of raters in the database

FourthRatings
This class loads movies and ratings using the FirstRating class to do calculations focusing on computing averages on movie ratings.
Does not use instance variables, leverages the MovieDatabase and RaterDatabase objects for efficiency. 
Public Methods:
getAverageByID(String id, int minimalRaters)
Returns average rating for specified movie ID given the number of ratings meets the minimal threshold for # of ratings
getAverageRatings(int minimalRaters)
Returns an ArrayList of Rating objects for all movies that meet the specified minimum threshold for # of ratings
getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
Returns an ArrayList of Rating objects for all movies that meet the filter criteria and the minimal threshold for # of ratings
dotProduct(Rater me, Rater r) 
***This is private method, but important to note
 Returns the dot product of ratings from the user and another user for movies they both rated. Converts 0 to 10 score to -5 to -5 scale.\
getSimilarities(String id)
Returns an ArrayList of Raters and their dot product score (only positive) in relation to the user.
This method computes a similarity rating for each rater in the RaterDatabase (except the rater with the ID given by the parameter) to see how similar they are to the Rater whose ID is the parameter to getSimilarities. 
This method returns an ArrayList of type Rating sorted by ratings from highest to lowest rating with the highest rating first and only including those raters who have a positive similarity rating since those with negative values are not similar in any way. 
Note that in each Rating object the item field is a rater’s ID, and the value field is the dot product comparison between that rater and the rater whose ID is the parameter to getSimilarities. 
getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
Return an ArrayList of type Rating, of movies and their weighted average ratings using only the top numSimilarRaters with positive ratings and including only those movies that have at least minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall).
getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter f)
Return an ArrayList of type Rating, of movies and their weighted average ratings using only the top numSimilarRaters with positive ratings and including only those movies that have at least minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall).
MovieDatabase object filtered to match the criteria. 


RecommendationRunner
This is the main code that is called. When a user visits the recommender website, the host website will call the method ‘getItemsToRate()’ to get a list of movies to display on the web page for users to rate. 
When a user submits their ratings, our code will call the method ’printRecommendationsFor’ to get your recommendations based on the user's ratings. The ID given to this method is for a new Rater that we have already added to the RaterDatabase with ratings for the movies returned by the first method.  Whatever is printed from that method will be displayed on the web page: HTML, plain text, or debugging information.

