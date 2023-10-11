import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class is used to store information regarding a single movie. 
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
// An immutable passive data object (PDO) to represent item data for movies
public class Movie {
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;

    public Movie (String anID, String aTitle, String aYear, String theGenres) {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    public Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector,
    String aCountry, String aPoster, int theMinutes) {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    // Returns ID associated with this item
    public String getID () {
        return id;
    }

    // Returns title of this item
    public String getTitle () {
        return title;
    }

    // Returns year in which this item was published
    public int getYear () {
        return year;
    }

    // Returns genres associated with this item
    public String getGenres () {
        return genres;
    }

    // Returns country movie was made
    public String getCountry(){
        return country;
    }

    // Returns director of movie
    public String getDirector(){
        return director;
    }

    // Returns poster of movie
    public String getPoster(){
        return poster;
    }
    
    // Returns duration of movie in minutes
    public int getMinutes(){
        return minutes;
    }

    // Returns a string of the item's information
    public String toString () {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }
    
    // Method to compare if movie is equal to another movie (all private variables must match to be equal)
    public boolean equals(Object o) {
        Movie other = (Movie) o;
        if (!this.getID().equals(other.getID())) {
            return false;
        }        
        if (!this.getTitle().equals(other.getTitle())) {
            return false;
        } 
        if (this.getYear() != other.getYear()) {
            return false;
        } 
        if (!this.getGenres().equals(other.getGenres())) {
            return false;
        }         
        if (!this.getDirector().equals(other.getDirector())) {
            return false;
        }         
        if (!this.getPoster().equals(other.getPoster())) {
            return false;
        } 
        if (this.getMinutes() != other.getMinutes()) {
            return false;
        }       
        return true;
    }
    

}
