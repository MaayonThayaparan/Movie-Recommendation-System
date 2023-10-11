import java.util.ArrayList;
/**
 * This class can be used to use multiple filters
 *
 *  
 * @author Maayon Thayaparan 
 * @version 10.05.2023
 */

public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        
        return true;
    }

}
