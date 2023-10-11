import java.util.*;
/**
 * This class is an efficient way to get information about movies.  
 * It stores movie information in a HashMap for fast lookup of movie information given a movie ID. 
 * The class also allows filtering movies based on queries. All methods and fields in the class are static.
 *  
 * @author Maayon Thayaparan 
 * @version 10.05.2023
 */
import org.apache.commons.csv.*;
import edu.duke.FileResource;

public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;

    public static void initialize(String moviefile) {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/" + moviefile);
        }
    }

    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/ratedmoviesfull.csv");
        }
    }	

	// Builds HashMap of movies using csv of movies
    private static void loadMovies(String filename) {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
        }
    }
    
    // Returns boolean to see if movie exists for specified movie ID
    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    // Returns publish year for the specified movie ID
    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }
    
    // Returns genre for the specified movie ID
    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }
    
    // Returns title for the specified movie ID
    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    // Returns movie Object for the specified movie ID
    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

   // Returns movie poster image link for the specified movie ID
    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    // Returns movie duration in minutes for the specified movie ID
    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    // Returns publishing country for the specified movie ID
    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    // Returns director for the specified movie ID
    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    // Returns number of movies
    public static int size() {
        return ourMovies.size();
    }

    // Returns ArrayList of movies that meet the specified filter criteria
    public static ArrayList<String> filterBy(Filter f) {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        
        return list;
    }

}
