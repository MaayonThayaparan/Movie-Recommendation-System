
/**
 * This class can be used to filter a MovieDatabase object by duration of minutes
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class MinutesFilter implements Filter {
    private int myMin;
    private int myMax;
    
    public MinutesFilter(int min, int max) {
        myMin = min;
        myMax = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
        return  myMax >= minutes && myMin <= minutes;
        
    }
    

}
