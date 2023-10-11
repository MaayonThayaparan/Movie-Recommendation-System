import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;
/**
 * This class uses the MovieDatabase and Raterbase objects to do calculations focusing on computing averages on movie ratings.
 * Does not use instance variables, leverages the MovieDatabase and RaterDatabase objects for efficiency. 
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class FourthRatings {
    
	// Returns average rating for specified movie ID given the number of ratings meets the minimal threshold for # of ratings
    public double getAverageByID(String id, int minimalRaters) { 
        double avg = 0.0;
        double sum = 0.0;
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        ArrayList<Double> ratings = new ArrayList<Double>();
        
        for (Rater r : allRaters) {
            if (r.getRating(id) != -1) {
                ratings.add(r.getRating(id));
            }
        }
        
        if (ratings.size() >= minimalRaters) {
            for (Double d : ratings) {
                sum += d;
            }
            avg = sum / ratings.size();
        }
        return avg; 
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
    
    // Returns the dot product of ratings from the user and another user for movies they both rated. Converts 0 to 10 score to -5 to -5 scale. 
    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> meMovies = me.getItemsRated();
        ArrayList<String> rMovies = r.getItemsRated();
        double dp = 0;
        for (String s : meMovies) {
            if (rMovies.contains(s)) {
                double meCurr = me.getRating(s) - 5;
                double rCurr = r.getRating(s) - 5;
                dp += meCurr*rCurr;
            }
        }
        return dp;
    }
    
    /* Returns an ArrayList of Raters and their dot product score (only positive) in relation to the user
     * This method computes a similarity rating for each rater in the RaterDatabase (except the rater with the ID given by the parameter) 
     * to see how similar they are to the Rater whose ID is the parameter to getSimilarities. 
     * This method returns an ArrayList of type Rating sorted by ratings from highest to lowest rating with the highest rating first 
     * and only including those raters who have a positive similarity rating since those with negative values are not similar in any way. 
     * Note that in each Rating object the item field is a rater’s ID, and the value field is the dot product comparison between that 
     * rater and the rater whose ID is the parameter to getSimilarities. 
    */
    private ArrayList<Rating> getSimilarities(String id) { 
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        
        for (Rater r : RaterDatabase.getRaters()) {
            String currID = r.getID();
            if (!currID.equals(id)) {
                double currDP = dotProduct(me,r);
                if (currDP >= 0) {
                    Rating currRating = new Rating(currID,currDP);
                    list.add(currRating);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list; 
    }
    
    /* Return an ArrayList of type Rating, of movies and their weighted average ratings using only the top numSimilarRaters with positive ratings 
     *  and including only those movies that have at least minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall)
     */
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> simRaters = getSimilarities(id); //creates Array List of raterID and similarity score
        ArrayList<Rating> ret = new ArrayList<Rating>(); //creates ArrayList of movieID and weight average rating
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter()); //creates ArrayList of all movieIDs
        for (String movieID : movies) {
            double avg = 0;
            double sum = 0;
            int count = 0;
            for (int i=0; i<numSimilarRaters; i++) { //if current index is greater than number of similar raters, break out of loop
                if (i>simRaters.size()-1) {
                    break;
                }
                Rating r = simRaters.get(i); //gets rater ID and similarity score for current index
                String raterID = r.getItem(); //gets the raterID of current index
                double raterScore = r.getValue(); //gets the similary score of current index
                Rater currRater = RaterDatabase.getRater(raterID); //gets the current Rater with their movie ratings using raterID
                double currRating = currRater.getRating(movieID); //gets the rating for the movie for current rater, will be -1 if there is no rating
                if (currRating != -1) {
                    sum += currRating*raterScore;
                    count += 1; 
                }
                
            }
            if (count >= minimalRaters) {
                avg = sum/count;
                Rating avgScore = new Rating(movieID,avg);
                ret.add(avgScore);
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }

    /* Return an ArrayList of type Rating, of movies and their weighted average ratings using only the top numSimilarRaters with positive ratings 
     *  and including only those movies that have at least minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall).
     *  Movie IDs are filtered to match the criteria. 
     */
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter f) {
        ArrayList<Rating> simRaters = getSimilarities(id); //creates Array List of raterID and similarity score
        ArrayList<Rating> ret = new ArrayList<Rating>(); //creates ArrayList of movieID and weight average rating
        ArrayList<String> movies = MovieDatabase.filterBy(f); //creates ArrayList of all movieIDs using specified filter
        for (String movieID : movies) {
            double avg = 0;
            double sum = 0;
            int count = 0;
            for (int i=0; i<numSimilarRaters; i++) { //if current index is greater than number of similar raters, break out of loop
                if (i>simRaters.size()-1) {
                    break;
                }
                Rating r = simRaters.get(i); //gets rater ID and similarity score for current index
                String raterID = r.getItem(); //gets the raterID of current index
                double raterScore = r.getValue(); //gets the similary score of current index
                Rater currRater = RaterDatabase.getRater(raterID); //gets the current Rater with their movie ratings using raterID
                double currRating = currRater.getRating(movieID); //gets the rating for the movie for current rater, will be -1 if there is no rating
                if (currRating != -1) {
                    sum += currRating*raterScore;
                    count += 1; 
                }
                
            }
            if (count >= minimalRaters) {
                avg = sum/count;
                Rating avgScore = new Rating(movieID,avg);
                ret.add(avgScore);
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }    
    
    
    
}
