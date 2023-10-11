import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings() {
        //String moviefile = "data/ratedmovies_short.csv";
        String moviefile = "data/ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        
        SecondRatings sr = new SecondRatings(moviefile,ratersfile);
        System.out.println("There are " + sr.getMovieSize() + " movies and " + 
        sr.getRaterSize() + " raters");
        
        
        ArrayList<Rating> avgRatings = sr.getAverageRatings(12); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
        System.out.println(avgRatings.size());
        
        
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            String currTitle = sr.getTitle(r.getItem());
            System.out.println(currRating + "\t" + currTitle); 
        }
        
        
    }
    
    public void getAverageRatingOneMovie() {
        //String moviefile = "data/ratedmovies_short.csv";
        String moviefile = "data/ratedmoviesfull.csv";
        //String ratersfile = "data/ratings_short.csv";
        String ratersfile = "data/ratings.csv";
        
        SecondRatings sr = new SecondRatings(moviefile,ratersfile);
        String id = sr.getID("Vacation");
        System.out.println(sr.getAverageByID(id,0));
        ArrayList<Rating> avgRatings = sr.getAverageRatings(0);
        for (Rating r : avgRatings) {
            if (r.getItem().equals(id)) {
                System.out.println(r.getValue());
            }
        }
        
        
    }
    

}
