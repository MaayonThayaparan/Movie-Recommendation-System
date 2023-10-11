import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Used to test Filter classes. 
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class MovieRunnerWithFilters {
    
    public ThirdRatings moviesInitialize() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratersfile = "ratings_short.csv";
        String ratersfile = "ratings.csv";
        
        ThirdRatings tr = new ThirdRatings(ratersfile);
        System.out.println("read data for " + tr.getRaterSize() + " raters");
      
        MovieDatabase.initialize(moviefile);
        System.out.println("read data for " + MovieDatabase.size() + " movies");  
        
        return tr;
    }
    
    public void printAverageRatings() {
        ThirdRatings tr = moviesInitialize();
        
        int min  = 35;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(min); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        System.out.println("The number of movies with " + min + " or more ratings is " + avgRatings.size());
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
               
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            System.out.println(currRating + "\t" + currTitle); 
        }
  
    }
    
    public void printAverageRatingByYear() {
        ThirdRatings tr = moviesInitialize();
        
        int min  = 20;
        YearAfterFilter yearFilter = new YearAfterFilter(2000);
        
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(min,yearFilter); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        System.out.println("The number of movies with " + min + " or more ratings is " + avgRatings.size());
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
               
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            int currYear = MovieDatabase.getYear(r.getItem());            
            System.out.println(currRating + "\t" + currYear + "\t" + currTitle); 
        }
        
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = moviesInitialize();
        
        int min  = 20;
        GenreFilter genreFilter = new GenreFilter("Comedy");
        
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(min,genreFilter); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        System.out.println("The number of movies with " + min + " or more ratings is " + avgRatings.size());
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
               
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            String currGenres = MovieDatabase.getGenres(r.getItem());            
            System.out.println(currRating + "\t" + currTitle); 
            System.out.println("\t" + currGenres);
        }
                
    }

    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = moviesInitialize();
        
        int min  = 5;
        MinutesFilter minutesFilter = new MinutesFilter(105,135);
        
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(min,minutesFilter); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        System.out.println("The number of movies with " + min + " or more ratings is " + avgRatings.size());
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
               
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            int currMinutes = MovieDatabase.getMinutes(r.getItem()); 
            String currTitle = MovieDatabase.getTitle(r.getItem());
                        
            System.out.println(currRating + "\t" + "Time: " + currMinutes + "\t" + currTitle); 
            
        }
                
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = moviesInitialize();
        
        int min  = 4;
        DirectorsFilter directorFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(min,directorFilter); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        System.out.println("The number of movies with " + min + " or more ratings is " + avgRatings.size());
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
               
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            String currDirectors = MovieDatabase.getDirector(r.getItem());
            
            System.out.println(currRating + "\t" + currTitle); 
            System.out.println("\t" + currDirectors);
            
        }
                
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = moviesInitialize();
        
        int min  = 8;
        
        GenreFilter gf = new GenreFilter("Drama");
        YearAfterFilter yf = new YearAfterFilter(1990);
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(yf);
        
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(min,af); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
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

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = moviesInitialize();
        
        int min  = 3;
        
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        MinutesFilter mf = new MinutesFilter(90,180);
        AllFilters af = new AllFilters();
        af.addFilter(df);
        af.addFilter(mf);
        
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(min,af); //creates ArrayList of Ratings that have greater an or equal to set # of ratings
        System.out.println("The number of movies with " + min + " or more ratings is " + avgRatings.size());
        Collections.sort(avgRatings, new AvgLowToHighComparator()); //sorts ArrayList from low to high
               
        for (Rating r : avgRatings) {
            double currRating = r.getValue();
            String currTitle = MovieDatabase.getTitle(r.getItem());
            String currDirectors = MovieDatabase.getDirector(r.getItem());
            int currTime = MovieDatabase.getMinutes(r.getItem());
            System.out.println(currRating + "\t" + "Time: " + currTime + "\t" + currTitle); 
            System.out.println("\t" + currDirectors);
        }
                
    }        
    
   
}
