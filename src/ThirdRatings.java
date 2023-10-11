import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;
/**
 * This class loads ratings using FirstRating class to do calculations focusing on computing averages on movie ratings. 
 * Leverages the MovieDatabase object for efficiency. 
 * 
 * @author Maayon Thayaparan 
 * @version 10.05.2023
 */
public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
        
    }
    
    // Returns total number of movies
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
        for (EfficientRater r : myRaters) {
            if (r.getRating(id) != -1) {
                ratings.add(r.getRating(id));
            }
        }
        return ratings;
        
    }
    
    // Returns an ArrayList of Rating objects for all movies that meet the specified minimum threshold for # of ratings
    public ArrayList<Rating> getAverageRatings(int minimalRaters) { 
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> arr = new ArrayList<Rating>();
        for (String id : movies) {
            double avg = getAverageByID(id,minimalRaters);
            if (avg != 0.0) {
                Rating r = new Rating(id,avg);
                arr.add(r);
            }
        }
        return arr;
    }
    
    // Returns an ArrayList of Rating objects for all movies that meet the filter criteria and the minimal threshold for # of ratings
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> arr = new ArrayList<Rating>();
        for (String id : movies) {
            double avg = getAverageByID(id,minimalRaters);
            if (avg != 0.0) {
                Rating r = new Rating(id,avg);
                arr.add(r);
            }
        }
        return arr;        
    }
   

}
