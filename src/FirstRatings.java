import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Class is used to load csv file containing movies and ratings and process information about them. 
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class FirstRatings {
    
    //-------------------------------------------------------------------------------------------loadMovies-------------------------------------------------------------------------------------------//
   
	// Returns ArrayList of Movie objects which was loaded from CSV file of movies. 
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            Movie mov = getMovie(record);
            if (!movies.contains(mov)) {
                movies.add(mov);
            }  
        }
        return movies; 
    }
    
    // Returns Movie object. Converts a row in CSV file into a Movie object. 
    private Movie getMovie(CSVRecord record) { //make row from CSV into a movie object
        String id = record.get("id");
        String title = record.get("title");
        String year = record.get("year");
        String genre = record.get("genre");
        String director = record.get("director");
        String country = record.get("country");
        String poster = record.get("poster");
        int minutes = Integer.parseInt(record.get("minutes"));
        Movie mov = new Movie(id,title,year,genre,director,country,poster,minutes);
        return mov;
    }
    
    
    // Prints all movies
    private void printMovies(ArrayList<Movie> movies) { // prints all movies in ArrayList
        for (int i=0; i<movies.size(); i++) {
            System.out.println(movies.get(i));   
        }
    }
    
    // Returns number of movies of specified genre
    private int countGenre(ArrayList<Movie> movies, String genre) { 
        int count = 0;
        for (int i=0; i<movies.size(); i++) {   
            if (movies.get(i).getGenres().toLowerCase().indexOf(genre.toLowerCase()) != -1) { 
                count += 1;
            }
        }        
        return count;
    }
    
    // Returns number of movies longer than specified minutes
    private int countMin(ArrayList<Movie> movies, int min) { 
        int count = 0;
        for (int i=0; i<movies.size(); i++) {  
            if (movies.get(i).getMinutes() > min) { 
                count += 1;
            }
        }
        return count;
    }
    
    // Returns a map of each director and how many movies they have directed
    private HashMap<String,Integer> mapDir(ArrayList<Movie> movies) { 
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (int i=0; i<movies.size(); i++) {
            String dirs = movies.get(i).getDirector();
            String[] arr = dirs.split(", ");
            for (int k=0; k<arr.length; k++) {
                String currDir = arr[k];
                if (!map.containsKey(currDir)) {
                    map.put(currDir,1);
                }
                else {
                    map.put(currDir,map.get(currDir)+1);
                }

            }
        }
        return map;
    }
    
    // Returns the maximum number of movies y any director
    private int maxMovies(HashMap<String,Integer> map) { 
        int max = 0;
        for (Integer v : map.values()) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
    
    private ArrayList<String> maxDirectors(HashMap<String, Integer> map, int max) {// creates array list of directors with max number of
        ArrayList<String> dirs = new ArrayList<String>();
        for (String s : map.keySet()) {
            if (map.get(s) == max) {
                dirs.add(s);
            }
        }
        return dirs;
    }
    
    // Function to test loadMovies method. 
    public void testLoadMovies() {
        //ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
       
        //printMovies(movies);
        System.out.println("The number of movies is " + movies.size()); 
        
        /*
        String genre = "Comedy";
        int countGenre = countGenre(movies,genre);
        System.out.println("The number of " + genre + " movies is " + countGenre);
        */
        
        /*
        int min = 150;
        int countMin = countMin(movies,min);
        System.out.println("The number of movies longer than " + min + " is " + countMin);
        */
       
        HashMap<String,Integer> mapDir = mapDir(movies);
        int maxMovies = maxMovies(mapDir);
        ArrayList<String> maxDirectors = new ArrayList<String>(maxDirectors(mapDir, maxMovies));
        System.out.println("The maximum number of movies by any director is " + maxMovies);
        System.out.println("The number of directors with the max number of movies is " + maxDirectors.size());
        System.out.println("The directors with max number of movies are: " + maxDirectors);
        
    }
       
    
    //-------------------------------------------------------------------------------------------loadRatings-------------------------------------------------------------------------------------------//
    
    
    // Returns ArrayList of EfficientRater objects which was loaded from CSV file of Raters and their Ratings
    public ArrayList<EfficientRater> loadRaters(String filename) { 
        ArrayList<EfficientRater> raters = new ArrayList<EfficientRater>();
        filename = "data/" + filename;
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String id = record.get("rater_id");
            EfficientRater curr = new EfficientRater(id);
            String movie = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));

            for (EfficientRater r : raters) { // checks to see if current Rater exists in array list, if exists, add Rating to existing Rater
                if (r.equals(curr)) {
                    r.addRating(movie,rating);
                    break; 
                }
            }
            if (!raters.contains(curr)) { //if Rater does not exist in array list, then add new Rater with Rating
                curr.addRating(movie,rating);
                raters.add(curr);
            }
        }
        return raters;
    }
    
    // Prints all Raters
    private void printRaters(ArrayList<EfficientRater> raters) { //prints the number of raters
        System.out.println("Total number of raters is " + raters.size());
        /*
        for (Rater r : raters) {
            String id = r.getID();
            int num = r.numRatings();
            System.out.println("Rater ID: " + id + " | Number of Ratings: " + num);
            System.out.println(r);
        }
        */
    }
    
    // Returns the number od ratings for specified rater ID
    private int getIDRatings(ArrayList<EfficientRater> raters, String id) { 
        int num = 0;
        for (EfficientRater r : raters) {
            if (r.getID().equals(id)) {
                num = r.numRatings();
            }
        }
        return num;
    }
    
    // Returns HashMap of rater and number of ratings
    private HashMap<String,Integer> mapRaters(ArrayList<EfficientRater> raters) { 
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (int i=0; i<raters.size(); i++) {
            String id = raters.get(i).getID();
            int numRatings = raters.get(i).numRatings();
            if (!map.containsKey(id)) {
                map.put(id,numRatings);
            }
        }
        return map;
    }
    
    
    // Returns the higher number of ratings for any rater
    private int maxRatings(HashMap<String,Integer> map) { 
        int max = 0;
        for (Integer v : map.values()) {
            if (v>max) {
                max = v;
            }
        }
        return max;
    }
    
    // Returns an ArrayList of al raters with highest number of ratings
    private ArrayList<String> maxRaters(HashMap<String,Integer> map, int max) { 
        ArrayList<String> raters = new ArrayList<String>();
        for (String s : map.keySet()) {
            if (map.get(s) == max) {
                raters.add(s);
            }
        }
        return raters;
    }
    
    // Returns a HashMap of movies and the number of ratings it has
    private HashMap<String,Integer> numMovieRatings(ArrayList<EfficientRater> raters) {     
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (EfficientRater r : raters) {
            ArrayList<String> currMovies = r.getItemsRated(); 
            for (String s : currMovies) {
                if (!map.containsKey(s)) {
                    map.put(s,1); 
                }
                else {
                    map.put(s,map.get(s)+1);
                }
            }
        }
        return map;
    }
    
    // Returns the number of ratings for a specified movie ID
    private int getNumMovieRatings(HashMap<String,Integer> movieMap, String id) {
        return movieMap.get(id);
    }
    
    // Returns the total number of movies rated by all raters
    private int totMoviesRated(HashMap<String,Integer> movieMap) {
        return movieMap.size();
    }
    
    // Test method
    public void testLoadRaters() {
        //ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        ArrayList<EfficientRater> raters = loadRaters("data/ratings.csv");
        printRaters(raters);
        
        /*
        String idRater = "193";
        int IDRatings = getIDRatings(raters,idRater);
        System.out.println("The rater ID " + idRater + " has " + IDRatings + " ratings"); // find out number of ratings for specified rater id
        */
        
        /*
        HashMap<String,Integer> raterMap = mapRaters(raters); // print max number of ratings, how many raters have max number of ratings, and who they are
        int maxRatings = maxRatings(raterMap);
        ArrayList<String> maxRaters = maxRaters(raterMap,maxRatings);
        System.out.println("The maxiumum number of ratings by any rater is " + maxRatings);
        System.out.println("There are " + maxRaters.size() + " raters with the max number of ratings");
        System.out.println("The raters with the max number of ratings " + " are " + maxRaters);
        */
        
        HashMap<String,Integer> movieRatingsMap = numMovieRatings(raters); //creates HashMap of movies and number of ratings they have
        String movieID = "1798709";
        int numMovieRatings = getNumMovieRatings(movieRatingsMap,movieID); //determine number of ratings for specified movie
        System.out.println("The movie " + movieID + " has " + numMovieRatings + " ratings");
        System.out.println("The total number of different movies rated by all raters is " + totMoviesRated(movieRatingsMap));
        
        
    }
    
    
    
}
    

