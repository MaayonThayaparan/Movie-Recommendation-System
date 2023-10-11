import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
/**
 * Class used to represent one rater and all their ratings.
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public interface Rater {
    
	// Add a item and rating
    public void addRating(String item, double rating);

    // Returns true if Rater has rated specified item
    public boolean hasRating(String item);

    // Returns the Rater ID
    public String getID();

    // Returns the rating for specified item
    public double getRating(String item);

    // Returns the total number of ratings from user
    public int numRatings();

    // Returns the items that have been rated
    public ArrayList<String> getItemsRated();
    
    // Returns true if rater is equal to another rater
    public boolean equals(Object o);
    
    // Prints all the ratings
    public String toString ();
}
