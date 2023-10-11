/**
 * This class can be used to select every movie from MovieDatabase. It’s satisfies method always returns true.
 * 
 * @author Maayon Thayaparan
 * @version 10.05.2023
 */
public class TrueFilter implements Filter {
	@Override
	public boolean satisfies(String id) {
		return true;
	}

}
