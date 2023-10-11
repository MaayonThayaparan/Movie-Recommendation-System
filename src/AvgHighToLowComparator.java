import java.util.*;
/**
 * Write a description of AvgHighToLowComparator here.
 * used to sort rating averages from high to low
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AvgHighToLowComparator implements Comparator<Rating> {
    public int compare(Rating r1, Rating r2) {
        double avg1 = r1.getValue();
        double avg2 = r2.getValue();
        if (avg1 > avg2) {
            return -1;
        }
        if (avg1 < avg2) {
            return 1;
        }
        return 0;
    }

}
