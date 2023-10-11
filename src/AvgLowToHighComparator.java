import java.util.*;
/**
 * Write a description of AvgLowToHighComparator here.
 * Used to sort rating averages from low to high
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AvgLowToHighComparator implements Comparator<Rating> {
    public int compare(Rating r1, Rating r2) {
        double avg1 = r1.getValue();
        double avg2 = r2.getValue();
        return Double.compare(avg1,avg2);
    }

}
