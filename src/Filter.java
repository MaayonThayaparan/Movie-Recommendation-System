/**
 * The interface Filter has only one signature for the method satisfies. 
 * The method satisfies has one String parameter named id representing a movie ID.
 * This method returns true if the movie satisfies the criteria in the method and returns false otherwise.
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public interface Filter {
	public boolean satisfies(String id);
}
