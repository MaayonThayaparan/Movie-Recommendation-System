
/**
 * This class loads movies and ratings using FirstRating class to do calculations focusing on computing averages on movie ratings. 
 * 
 * @author Maayon Thayaparan 
 * @version 10.05.2023
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
        
    }
    
    // Returns total number of movies
    public int getMovieSize() {
        return myMovies.size();
    }
    
    // Returns total number of raters
    public int getRaterSize() {
        return myRaters.size();
    }
    
    // Returns average rating for specified movie ID given the number of ratings meets the minimal threshold for # of ratings
    public double getAverageByID(String id, int minimalRaters) {
        double avg = 0.0;
        double sum = 0.0;
        ArrayList<Double> ratings = movieRatings(id);
        if (ratings.size() >= minimalRaters) {
            for (Double d : ratings) {
                sum += d;
            }
            avg = sum / ratings.size();
        }
        return avg; 
    }
    
    // Returns an ArrayList of all ratings for speciifed movie ID
    private ArrayList<Double> movieRatings(String id) { 
        ArrayList<Double> ratings = new ArrayList<Double>();
        for (EfficientRater r : myRaters) {
            if (r.getRating(id) != -1) {
                ratings.add(r.getRating(id));
            }
        }
        return ratings;
        
    }
    
    // Returns an ArrayList of Rating objects for all movies that meet the specified minimum threshold for # of ratings
    public ArrayList<Rating> getAverageRatings(int minimalRaters) { 
        ArrayList<Rating> arr = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            String id = m.getID();
            double avg = getAverageByID(id,minimalRaters);
            if (avg != 0.0) {
                Rating r = new Rating(id,avg);
                arr.add(r);
            }
        }
        return arr;
    }
    
    // Returns title of specified movie ID
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "Movie ID was not found";
    }
    
    // Returns ID of specifried movie title
    public String getID(String title) {
        for (Movie m: myMovies) {
            if (m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}