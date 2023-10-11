import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Used to test the FourthRatings class. 
 * 
 * @author Maayon THayaparan
 * @version 10.05.2023
 */
public class MovieRunnerSimilarRatings {
    
    public FourthRatings moviesInitialize() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "ratings_short.csv";
        String ratersfile = "ratings.csv";
        
        FourthRatings fr = new FourthRatings();
        
        RaterDatabase.initialize(ratersfile);
        System.out.println("read data for " + RaterDatabase.size() + " raters");
      
        MovieDatabase.initialize(moviefile);
        System.out.println("read data for " + MovieDatabase.size() + " movies");  
        
        return fr;
    }
    
    public void printAverageRatings() {
        FourthRatings fr = moviesInitialize();
        
        int min  = 35;
        ArrayList<Rating> avgRatings = fr.getAverageRatings(min); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        System.out.println("The number of movies with " + min + " or more ratings is " + avgRatings.size());
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
               
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            System.out.println(currRating + "\t" + currTitle); 
        }
  
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fr = moviesInitialize();
        
        int min  = 8;
        
        GenreFilter gf = new GenreFilter("Drama");
        YearAfterFilter yf = new YearAfterFilter(1990);
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(yf);
        
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(min,af); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        System.out.println("The number of movies with " + min + " or more ratings is " + avgRatings.size());
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
               
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            
            String currGenres = MovieDatabase.getGenres(r.getItem());
            int currYear = MovieDatabase.getYear(r.getItem());
            System.out.println(currRating + "\t" + currYear + "\t" + currTitle); 
            System.out.println("\t" + currGenres);
        }
                
    }     
    
    public void printSimilarRatings() {
        FourthRatings fr = moviesInitialize();   
        
        String raterID = "71";
        int numSim = 20;
        int numMin = 5;
        ArrayList<Rating> simRatings = fr.getSimilarRatings(raterID,numSim,numMin);
        
        for (Rating r : simRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            
            System.out.println(currTitle + "\t" + currRating);
        }
        
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fr = moviesInitialize();   
        
        String raterID = "964";
        int numSim = 20;
        int numMin = 5;
        GenreFilter gf = new GenreFilter("Mystery");
        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter(raterID,numSim,numMin,gf);
        
        for (Rating r : simRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            String currGenres = MovieDatabase.getGenres(r.getItem());
            
            System.out.println(currTitle + "\t" + currRating);
            System.out.println("\t" + currGenres);
        }
        
    }   
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fr = moviesInitialize();   
        
        String raterID = "120";
        int numSim = 10;
        int numMin = 2;
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter(raterID,numSim,numMin,df);
        
        for (Rating r : simRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            String currDirectors = MovieDatabase.getDirector(r.getItem());
            
            System.out.println(currTitle + "\t" + currRating);
            System.out.println("\t" + currDirectors);
        }
        
    }  
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = moviesInitialize();   
        
        String raterID = "168";
        int numSim = 10;
        int numMin = 3;
        GenreFilter gf = new GenreFilter("Drama");
        MinutesFilter mf = new MinutesFilter(80,160);
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);

        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter(raterID,numSim,numMin,af);
        
        for (Rating r : simRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            String currGenre = MovieDatabase.getGenres(r.getItem());
            int currMinutes = MovieDatabase.getMinutes(r.getItem());
            
            
            System.out.println(currTitle + "\t" + "Time: " + currMinutes + "\t" + currRating);
            System.out.println("\t" + currGenre);
        }
        
    }       

    public void printSimilarRatingsByYearAndMinutes() {
        FourthRatings fr = moviesInitialize();   
        
        String raterID = "314";
        int numSim = 10;
        int numMin = 5;
        YearAfterFilter yf = new YearAfterFilter(1975);
        MinutesFilter mf = new MinutesFilter(70,200);
        AllFilters af = new AllFilters();
        af.addFilter(yf);
        af.addFilter(mf);

        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter(raterID,numSim,numMin,af);
        
        for (Rating r : simRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            int currYear = MovieDatabase.getYear(r.getItem());
            int currMinutes = MovieDatabase.getMinutes(r.getItem());
            
            System.out.println(currTitle + "\t" + currYear + "\t" + "Time: " + currMinutes + "\t" + currRating);
        }
        
    }      
    
}
