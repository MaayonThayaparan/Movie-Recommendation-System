
/**
 * This class is an efficient way to get information about raters. 
 * A HashMap named ourRaters that maps a rater ID String to a Rater object that includes all the movie ratings made by this rater. 
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    private static HashMap<String,EfficientRater> ourRaters;
    
    
    // Initializes an empty HashMap if no file
    private static void initialize() {
	if (ourRaters == null) {
	    ourRaters = new HashMap<String,EfficientRater>();
	}
    }

    // Initializes HashMap using filename, calls addRating method. 
    public static void initialize(String filename) {
 	if (ourRaters == null) {
 	    ourRaters= new HashMap<String,EfficientRater>();
 	    addRatings("data/" + filename);
 	}
    }	
 	
    // Obtains rater ID, movie ID, and rating from CSV file and calls the addRaterRating method to add one rater and their movie rating to the HashMap.
    public static void addRatings(String filename) {
        initialize(); 
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id,item,Double.parseDouble(rating));
        } 
    }
    
    // This function can be used to add one rater and their movie rating to the database.  Ensures no duplicate ratings added. 
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        EfficientRater rater =  null;
                if (ourRaters.containsKey(raterID)) {
                    rater = ourRaters.get(raterID); 
                } 
                else { 
                    rater = new EfficientRater(raterID);
                    ourRaters.put(raterID,rater);
                 }
                 rater.addRating(movieID,rating);
    } 
	
    // Returns a Rater that has this ID
    public static Rater getRater(String id) {
    	initialize();
    	
    	return ourRaters.get(id);
    }
    
    // Returns an ArrayList of Raters from the database
    public static ArrayList<Rater> getRaters() {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
    	
    	return list;
    }
 
    // Returns the number of raters in the database
    public static int size() {
	    return ourRaters.size();
    }
    
    
        
}