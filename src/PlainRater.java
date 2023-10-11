import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Class used to represent one rater and all their ratings. Implements Rater interface. 
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */

import java.util.*;

public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }
	
	// Add a item and rating
    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item,rating));
    }

    // Returns true if Rater has rated specified item
    public boolean hasRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        
        return false;
    }

    // Returns the Rater ID
    public String getID() {
        return myID;
    }

    // Returns the rating for specified item
    public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
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
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    }
    
    // Returns true if rater is equal to another rater
    public boolean equals(Object o) {
        PlainRater other = (PlainRater) o;
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
