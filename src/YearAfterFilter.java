/**
 * This class can be used to filter a MovieDatabase object by years after
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
