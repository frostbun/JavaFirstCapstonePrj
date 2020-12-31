import java.util.Comparator;

public class byRatingAccending implements Comparator<Rating> {
    public int compare(Rating o1, Rating o2) {
        return Double.compare(o1.getValue(), o2.getValue());
    }
}