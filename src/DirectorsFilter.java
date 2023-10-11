
/**
 * This class can be used to filter a MovieDatabase object by director
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class DirectorsFilter implements Filter{
    private String myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] arr = myDirectors.split(",");
        String dirs = MovieDatabase.getDirector(id);
        
        for (int i=0; i<arr.length; i++) {
            if (dirs.indexOf(arr[i]) != -1) {
                return true;
            }
        }
        return false;
    }
    
    
    

}
