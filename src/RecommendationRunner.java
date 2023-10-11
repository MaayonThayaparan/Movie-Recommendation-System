import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 * This class is used to display movies to be rated and also recommend movies for user that rated the movies. 
 * This is the main class used to run the project. 
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class RecommendationRunner implements Recommender{
    
	// Returns a randomly generated list of movies with specified output number and genre. 
    public ArrayList<String> getItemsToRate() { 
        ArrayList<String> output = new ArrayList<String>();
        
        String moviefile = "ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefile);
        GenreFilter gf = new GenreFilter("Action");
        ArrayList<String> moviesAction = MovieDatabase.filterBy(gf);
        int numMovies = 10;
        Random rand = new Random();
        
        for (int i=0; i<numMovies; i++) {
            if (i>moviesAction.size()) {
                break;
            }
            int index = rand.nextInt(moviesAction.size());
            if (!output.contains(moviesAction.get(index))) {
                output.add(moviesAction.get(index));
            }
        } 
        return output;
    }
    
    // Returns a list of recommendations based on the ratings provided by user. 
    public void printRecommendationsFor(String webRaterID) {
        ArrayList<String> output = getItemsToRate();
        FourthRatings fr = new FourthRatings();
        
        GenreFilter gf = new GenreFilter("Action");
        String raterID = webRaterID;
        int numSim = 20;
        int numMin = 3; 
        int maxDisplay = 15;
        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter(raterID,numSim,numMin,gf);
        
        for (int i=0; i<maxDisplay; i++) {
            int rank = i+1;
            if (simRatings.size() == 0) {
                System.out.println("There are no movie recommendations due to lack of ratings in our database");
                break;
            }            
            if (i>simRatings.size()-1) {
                System.out.println("<h1>Action Movie Top " + i + " Recommendations</h1>");
                System.out.println("</table>");
                break;
            }
            if (i==0) {
                System.out.println("<style>");
                System.out.println("table {margin-left: auto; margin-right: auto;}");
                System.out.println("table,th,td {border: 1px solid black;}");
                System.out.println("th,td { padding: 5px}");
                System.out.println("img { width: 30px;}</style>");
                System.out.println("</style>");
                
                
                
                System.out.println("<table class=\"center\"><th></th><th>Movie</th><th>Rank</th><th>Score</th>");
            }

            Rating r = simRatings.get(i);
            String currTitle = MovieDatabase.getTitle(r.getItem());
            double currRating = r.getValue();
            String ratingFormat = String.format("%.02f", currRating);
            String currPoster = MovieDatabase.getPoster(r.getItem());
            int currYear = MovieDatabase.getYear(r.getItem());
            
            
            
            System.out.println("<tr>");
            System.out.println("<td style=\"text-align: center\";><img src=\"" + currPoster + "\" alt=\"\"></td>");    
            System.out.println("<td>" + currTitle + "  (" + currYear + ")" + "</td>");
            System.out.println("<td style=\"text-align: center\";>" + rank + "</td>");
            System.out.println("<td style=\"text-align: center\";>" + ratingFormat + "</td>");
            System.out.println("</tr>");
            
            
            if (i == maxDisplay-1) {
                System.out.println("<h1>Action Movie Top " + maxDisplay + " Recommendations</h1>");
                System.out.println("</table>");
            }

        }
        
        
    }
}
