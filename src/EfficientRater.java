import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
/**
 * Class used to represent one rater and all their ratings. Implements Rater interface. 
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    // Add a item and rating
    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    // Returns true if Rater has rated specified item
    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)) {
            return true;
        }
        return false;
    }

    // Returns the Rater ID
    public String getID() {
        return myID;
    }

    // Returns the rating for specified item
    public double getRating(String item) {
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }
        
        return -1;
    }

 	// Returns the total number of ratings from user
    public int numRatings() {
        return myRatings.size();
    }

    // Returns the items that have been rated
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String s : myRatings.keySet()) {
            list.add(s);
            
        }
        return list;
    }
    
    // Returns true if rater is equal to another rater
    public boolean equals(Object o) {
        EfficientRater other = (EfficientRater) o;
        if (!this.getID().equals(other.getID())) {
            return false;   
        }
        return true;
    }
 
    // Prints all the ratings
    public String toString () {
        return myRatings.toString();

    }
}
