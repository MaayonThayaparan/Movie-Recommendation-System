
/**
 * This class can be used to filter a MovieDatabase object by genre
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter(String genre) {
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).indexOf(myGenre) != -1;
    }

}
